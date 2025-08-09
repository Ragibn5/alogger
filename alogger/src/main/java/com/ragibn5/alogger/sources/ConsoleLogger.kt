package com.ragibn5.alogger.sources

import android.util.Log
import com.ragibn5.alogger.constants.LogLevel
import com.ragibn5.alogger.models.LogData

/**
 * A [Logger] implementation that logs messages to the Android console (Logcat).
 *
 * This logger allows for custom formatting of the log message body.
 *
 * @property bodyBuilder An optional lambda function that takes the message ([LogData.message])
 * and extras ([LogData.extras]) and returns a formatted string for the log body.
 * If not provided, a default formatting is used.
 */
class ConsoleLogger(
    private val bodyBuilder: ((message: String, extras: Map<String, Any>) -> String)? = null,
) : Logger {
    override fun log(log: LogData) {
        val entries = log.extras.entries
        val separator = if (entries.isNotEmpty()) "\n" else ""
        val body = bodyBuilder?.let { it(log.message, log.extras) }
            ?: String.format(
                "%s%s",
                log.message,
                separator,
                entries.joinToString("\n") { entry ->
                    String.format(
                        "%s: %s",
                        entry.key,
                        entry.value
                    )
                },
            )

        when (log.type) {
            LogLevel.DEBUG -> Log.d(log.tag, body, log.throwable)
            LogLevel.INFO -> Log.i(log.tag, body, log.throwable)
            LogLevel.WARNING -> Log.w(log.tag, body, log.throwable)
            LogLevel.ERROR -> Log.e(log.tag, body, log.throwable)
        }
    }
}