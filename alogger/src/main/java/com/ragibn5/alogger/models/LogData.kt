package com.ragibn5.alogger.models

import com.ragibn5.alogger.constants.LogLevel

/**
 * Data class representing a log entry.
 *
 * @property tag The tag associated with the log entry.
 * @property stamp The timestamp of when the log entry was created.
 * @property type The log level of the entry (See [LogLevel]).
 * @property message The main log message.
 * @property extras Additional key-value pairs of data to be logged.
 * Defaults to an empty map.
 * @property throwable An optional throwable (e.g., an exception) associated with the log entry.
 * Defaults to null.
 */
data class LogData(
    val tag: String,
    val stamp: Long,
    val type: LogLevel,
    val message: String,
    val extras: Map<String, Any> = emptyMap(),
    val throwable: Throwable? = null
)