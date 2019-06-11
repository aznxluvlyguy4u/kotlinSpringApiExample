package com.oceanpremium.api.core.usecase

import com.oceanpremium.api.core.exception.throwable.BadRequestException
import com.oceanpremium.api.core.exception.throwable.ServerErrorException
import com.oceanpremium.api.core.model.OrderDto
import com.oceanpremium.api.core.util.ObjectMapperConfig
import com.sun.mail.smtp.SMTPAddressFailedException
import io.sentry.Sentry
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import java.io.IOException
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

interface SendEmailUseCase {
    fun execute(order: OrderDto)
}

class SendEmailUseCaseImpl(
    @Autowired private val templateEngine: SpringTemplateEngine
) : SendEmailUseCase {

    internal class EmailDto(
        var from: String,
        var to: List<String>,
        var bcc: List<String>,
        var subject: String
    )

    internal class EmailServiceConfig {
        var host: String? = null
        var userName: String? = null
        var password: String? = null
        var sender: String? = null
        var backOffice: String? = null

        companion object {
            private const val HOST_KEY = "emailer_smtp_host"
            private const val USERNAME_KEY = "emailer_username"
            private const val PASSWORD_KEY = "emailer_password"
            private const val SENDER_KEY = "emailer_sender"
            private const val BACKOFFICE_KEY = "emailer_backOffice"
        }

        init {
            getEmailConfig()
        }

        @Throws(Exception::class)
        private fun getEmailConfig(): EmailServiceConfig {
            backOffice = System.getenv(BACKOFFICE_KEY) ?: null ?: throw Exception("Env var: $BACKOFFICE_KEY not set")
            sender = System.getenv(SENDER_KEY) ?: null ?: throw Exception("Env var: $SENDER_KEY not set")
            host = System.getenv(HOST_KEY) ?: null ?: throw Exception("Env var: $HOST_KEY not set")
            userName = System.getenv(USERNAME_KEY) ?: null ?: throw Exception("Env var: $USERNAME_KEY not set")
            password = System.getenv(PASSWORD_KEY) ?: null ?: throw Exception("Env var: $PASSWORD_KEY not set")

            return this
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val context = Context()
        private val emailServiceConfig = EmailServiceConfig()
        private var session: Session? = null
        private const val CLIENT_EMAIL_ORDER_TEMPLATE = "client_email_order_template"
    }

    @Throws(MessagingException::class, IOException::class)
    override fun execute(order: OrderDto) {
        setupMailClient()
        sendEmail(order)
    }

    private fun setupMailClient() {
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = emailServiceConfig.host
        props["mail.smtp.port"] = "587"

        session = Session.getInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(emailServiceConfig.userName, emailServiceConfig.password)
            }
        })
    }

    private fun sendEmail(order: OrderDto) {
        val email = EmailDto(
            from = emailServiceConfig.sender!!,
            to = listOf(order.contactDetails.emailAddress),
            bcc = listOf(emailServiceConfig.backOffice!!),
            subject = "[${DateTime.now()}] - Rental order of ${order.contactDetails.fullName} ${order.contactDetails.phoneNumber}"
        )

        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(email.from))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.to.first()))
            message.sender = InternetAddress(email.from)
            message.subject = email.subject

            val orderMap = ObjectMapperConfig.serializeToMap(order)
            context.setVariables(orderMap)
            val html = templateEngine.process(CLIENT_EMAIL_ORDER_TEMPLATE, context)
            message.setContent(html, "text/html")

            Transport.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()

            Sentry.capture(e)

            logger.error("Failed to send email: ${e.message}")
            throw ServerErrorException("Failed to send email to address: ${order.contactDetails.emailAddress}: ${e.message}")
        } catch (e: SMTPAddressFailedException) {
            e.printStackTrace()

            Sentry.capture(e)

            logger.error("Failed to send email: ${e.message}")
            throw BadRequestException("E-mail address: ${order.contactDetails.emailAddress} is not valid, please check the address or try with a different one")
        }
    }
}
