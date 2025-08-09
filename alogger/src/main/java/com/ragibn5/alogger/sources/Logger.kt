package com.ragibn5.alogger.sources

import com.ragibn5.alogger.models.LogData

/**
 * Base interface for a [Logger].
 */
fun interface Logger {
    fun log(log: LogData)
}