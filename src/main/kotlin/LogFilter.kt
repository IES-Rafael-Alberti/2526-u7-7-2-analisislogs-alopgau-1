package org.iesra

import java.time.LocalDateTime


class LogFilter(val logs: List<Log>, val fromDate: LocalDateTime?, val toDate: LocalDateTime?, val levels: List<LogLevel>) {
    fun filterByDate(): List<Log> {
        if (fromDate == null) {
            return logs.filter { log -> log.dateTime <= toDate }
        } else if (toDate == null) {
            return logs.filter { log -> log.dateTime >= fromDate }
        } else {
            return logs.filter { log -> log.dateTime >= fromDate && log.dateTime <= toDate }
        }
    }

    fun filterByLevel() = logs.filter { it.level in levels }
}