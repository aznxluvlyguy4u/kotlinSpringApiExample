package com.oceanpremium.api.core.messenger

import com.palantir.roboslack.api.MessageRequest
import com.palantir.roboslack.webhook.SlackWebHookService
import com.palantir.roboslack.webhook.api.model.WebHookToken
import com.palantir.roboslack.webhook.api.model.response.ResponseCode
import org.slf4j.LoggerFactory
import java.lang.Exception

class SlackConfig(val platformMonitoringWebHook: String,val salesMonitoringWebHook: String)

object Slogger {
    private const val LOGGER_NAME = "Ocean Premium - API logger"
    private const val ERROR_MESSAGE = "Failed to send message to Slack!"
    private val logger = LoggerFactory.getLogger(this::class.java)
    private const val PLATFORM_MONITORING_WEBHOOK_= "slack_webhook_jvt"
    private const val SALES_MONITORING_WEBHOOK_ = "slack_webhook_op"

    @Throws(Exception::class)
    fun send(messageBody: String? = null,
             messageRequest: MessageRequest? = null,
             inDebugMode: Boolean = false,
             slackConfig: SlackConfig = getSlackConfig(),
             salesLog: Boolean = false) {

        if (inDebugMode) {
            logger.debug("Running in debug mode, therefore not sending message to Slack")

            return
        }

        val token = when {
            salesLog ->  WebHookToken.fromString(slackConfig.salesMonitoringWebHook)
            else -> WebHookToken.fromString(slackConfig.platformMonitoringWebHook)
        }

        val messageRequestData = when (messageRequest) {
            null -> {
                when (messageBody) {
                    null -> throw Exception("Message CONTENT not set, therefore not sending message")
                    else -> MessageRequest.builder()
                        .username(LOGGER_NAME)
                        .text(messageBody)
                        .build()
                }
            }
            else -> {
                when {
                    messageRequest.text().isEmpty() ->
                        throw Exception("Message BODY on Message Request not set, therefore not sending message")
                    else ->
                        messageRequest
                }
            }
        }

        val response= SlackWebHookService.with(token).sendMessage(messageRequestData)

        if (response != ResponseCode.OK) {
            logger.error(ERROR_MESSAGE)

            return
        }
    }

    private fun getSlackConfig(): SlackConfig {
        return SlackConfig(
            System.getenv(PLATFORM_MONITORING_WEBHOOK_) ?: null
            ?: throw Exception("Env var: $PLATFORM_MONITORING_WEBHOOK_ not set"),
            System.getenv(SALES_MONITORING_WEBHOOK_) ?: null
            ?: throw Exception("Env var: $SALES_MONITORING_WEBHOOK_ not set")
        )
    }
}