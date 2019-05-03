@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.oceanpremium.api.core.messenger

import org.slf4j.LoggerFactory
import java.lang.Exception
import com.github.seratch.jslack.Slack
import com.github.seratch.jslack.api.webhook.Payload

class SlackConfig(val platformMonitoringWebHook: String,val salesMonitoringWebHook: String)

object Slogger {
    private var slackLoggerName: String? = null
    private const val ERROR_MESSAGE = "Failed to send message to Slack"
    private val logger = LoggerFactory.getLogger(this::class.java)
    private const val PLATFORM_MONITORING_WEBHOOK_= "slack_webhook_jvt"
    private const val SALES_MONITORING_WEBHOOK_ = "slack_webhook_op"

    @Throws(Exception::class)
    fun send(messageBody: String? = null,
             payload: Payload? = null,
             inDebugMode: Boolean = false,
             slackConfig: SlackConfig = getSlackConfig(),
             salesLog: Boolean = false) {

        if (inDebugMode) {
            logger.debug("Running in debug mode, therefore not sending message to Slack")

            return
        }

        val slack = Slack.getInstance()


        val webHookUrl = when {
            salesLog ->  {
                slackLoggerName = "Ocean Premium - Sales Monitor Logger"
                slackConfig.salesMonitoringWebHook
            }
            else -> {
                slackLoggerName = "Ocean Premium - API logger"
                slackConfig.platformMonitoringWebHook
            }
        }

        val payloadData = when (payload) {
            null -> {
                when (messageBody) {
                    null -> throw Exception("Message CONTENT not set, therefore not sending message")
                    else -> Payload.builder()
                        .username(slackLoggerName)
                        .text(messageBody)
                        .build()
                }
            } else -> {
                payload
            }
        }

        val response = slack.send(webHookUrl, payloadData)

        if(response.code != 200) {
            logger.warn("$ERROR_MESSAGE: ${response.message}")
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