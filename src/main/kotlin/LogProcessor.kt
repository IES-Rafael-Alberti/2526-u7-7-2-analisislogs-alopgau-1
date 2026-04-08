package org.iesra
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogProcessor {
    fun process(logs: Array<String>) {
    logs.map {
        log ->
        val bracketsInfo = log.substringBefore("]").substringAfter("[")
        val afterBracketsInfo = log.substringAfter("]").split(" ")
        val level = LogLevel.valueOf(afterBracketsInfo[0])
        val message = afterBracketsInfo.drop(1).joinToString( " ")
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss")
        val dateTime = LocalDate.parse(bracketsInfo,formatter)
        Log(dateTime,level,message)
    }
    }
}