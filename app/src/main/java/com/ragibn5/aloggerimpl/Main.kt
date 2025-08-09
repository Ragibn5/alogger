package com.ragibn5.aloggerimpl

import com.ragibn5.alogger.constants.LogLevel
import com.ragibn5.alogger.extensions.debug
import com.ragibn5.alogger.extensions.error
import com.ragibn5.alogger.extensions.info
import com.ragibn5.alogger.extensions.warning
import com.ragibn5.alogger.models.LogData
import com.ragibn5.alogger.sources.ConsoleLogger

class Main {
    fun main() {
        // Using pre-built console logger implementation.
        val logger = ConsoleLogger()

        // With `log(LogData)` method.
        logger.log(
            LogData(
                tag = "EXAMPLE",
                type = LogLevel.DEBUG,
                message = "Example log",
            )
        )

        // With `debug`, `info`, `warning` and `error` extension methods.
        logger.debug(tag = "EXAMPLE", message = "Example log")
        logger.info(tag = "EXAMPLE", message = "Example log")
        logger.warning(tag = "EXAMPLE", message = "Example log")
        logger.error(
            tag = "EXAMPLE",
            message = "Example log",
            throwable = Exception("Example exception")
        )
    }
}