package com.ragibn5.alogger.sources

import android.annotation.SuppressLint
import android.util.Log
import com.ragibn5.alogger.models.LogData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Logs messages to files within a specified directory.
 *
 * This logger creates a new log file for each day or uses a custom naming scheme
 * provided by `fileNameBuilder`. Logs are written asynchronously using Kotlin Coroutines
 * to avoid blocking the main thread.
 *
 * @param parentDirectory The directory where log files will be stored.
 * If the directory  does not exist, it will be created.
 * @param logStringBuilder A lambda function that formats a [LogData]
 * object into a String to be written to the log file.
 * @param dispatcher The [CoroutineDispatcher] to be used for writing logs.
 * Defaults to [Dispatchers.IO], which is suitable for I/O operations.
 * @param fileNameBuilder A lambda function that generates a file name based on a timestamp.
 * Defaults to a function that creates filenames in the format "LOG-dd-MM-yyyy.txt",
 * where dd, MM, and yyyy represent the day, month, and year respectively.
 * For example, for a log entry on January 23, 2024, the file name would be "LOG-23-01-2024.txt".
 */
class FileLogger(
    private val parentDirectory: File,
    private val logStringBuilder: (log: LogData) -> String,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val fileNameBuilder: (timestamp: Long) -> String = {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
        "LOG-${dateFormat.format(Date(it))}.txt"
    }
) : Logger {
    private val syncScope = CoroutineScope(dispatcher)
    private val syncChannel = Channel<LogData>(Channel.UNLIMITED)

    init {
        check(parentDirectory.exists() || parentDirectory.mkdirs()) {
            "Could not find/create parent directory."
        }

        syncScope.launch {
            for (logData in syncChannel) {
                runCatching {
                    buildOutputStream(logData).use { bos ->
                        bos.write(logStringBuilder(logData).toByteArray())
                        bos.flush()
                    }
                }.onFailure {
                    Log.e(TAG, "Failed to write log in file", it)
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun log(log: LogData) {
        syncChannel.trySend(log)
    }

    fun recycle() {
        syncChannel.close()
        syncScope.cancel()
    }

    @Throws(IOException::class)
    private fun buildOutputStream(log: LogData): BufferedOutputStream {
        return BufferedOutputStream(FileOutputStream(getLogFile(log), true))
    }

    @Throws(IOException::class)
    private fun getLogFile(log: LogData): File {
        return File(getLogDirectory(), fileNameBuilder(log.stamp))
    }

    @Throws(IOException::class)
    private fun getLogDirectory(): File {
        if (!(parentDirectory.exists() || parentDirectory.mkdirs())) {
            throw IOException("Could not create the log file directory.")
        }

        return parentDirectory
    }

    companion object {
        private const val TAG = "FileLogger"
    }
}