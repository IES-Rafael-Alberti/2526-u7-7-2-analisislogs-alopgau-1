package org.iesra

class LogProcessor {
    fun process(logs: Array<String>) {
    logs.map {
        log ->
        val bracketsInfo = log.substringBefore("]").split(" ")
        val afterBracketsInfo = log.substringAfter("]").split(" ")
        val day = bracketsInfo[0]
        val date = bracketsInfo[1]
        val status = afterBracketsInfo[0]
        val message = afterBracketsInfo.joinToString( " ")
        log(day,date,status,message)
    }
    }
}