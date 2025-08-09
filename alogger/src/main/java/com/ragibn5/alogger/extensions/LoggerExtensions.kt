package com.ragibn5.alogger.extensions

import com.ragibn5.alogger.constants.LogLevel
import com.ragibn5.alogger.models.LogData
import com.ragibn5.alogger.sources.Logger

fun Logger.debug(
    tag: String,
    message: String,
    throwable: Throwable? = null,
    stamp: Long = System.currentTimeMillis(),
    extras: Map<String, Any> = emptyMap(),
) = log(LogData(tag, LogLevel.DEBUG, message, stamp, extras, throwable))

fun Logger.info(
    tag: String,
    message: String,
    throwable: Throwable? = null,
    stamp: Long = System.currentTimeMillis(),
    extras: Map<String, Any> = emptyMap(),
) = log(LogData(tag, LogLevel.INFO, message, stamp, extras, throwable))

fun Logger.warning(
    tag: String,
    message: String,
    throwable: Throwable? = null,
    stamp: Long = System.currentTimeMillis(),
    extras: Map<String, Any> = emptyMap(),
) = log(LogData(tag, LogLevel.WARNING, message, stamp, extras, throwable))

fun Logger.error(
    tag: String,
    message: String,
    throwable: Throwable? = null,
    stamp: Long = System.currentTimeMillis(),
    extras: Map<String, Any> = emptyMap(),
) = log(LogData(tag, LogLevel.ERROR, message, stamp, extras, throwable))