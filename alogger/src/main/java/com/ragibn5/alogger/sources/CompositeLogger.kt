package com.ragibn5.alogger.sources

import com.ragibn5.alogger.models.LogData

/**
 * A [Logger] that logs to multiple [Logger]s.
 *
 * @param loggers The list of [Logger]s to log to.
 */
open class CompositeLogger(
    private val loggers: List<Logger> = listOf(ConsoleLogger()),
) : Logger {
    override fun log(log: LogData) = loggers.forEach { it.log(log) }
}