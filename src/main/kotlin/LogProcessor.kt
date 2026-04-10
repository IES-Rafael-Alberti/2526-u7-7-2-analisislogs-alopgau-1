package org.iesra
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogProcessor {
    fun process(logs: List<String>): List<Log> {
    val logList = logs.map {
        log ->
        val bracketsInfo = log.substringBefore("]").substringAfter("[")
        val afterBracketsInfo = log.substringAfter("]").trim().split(" ")
        val level = LogLevel.valueOf(afterBracketsInfo[0])
        val message = afterBracketsInfo.drop(1).joinToString( " ")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(bracketsInfo,formatter)
        Log(dateTime,level,message)
    }
        return logList
    }
}