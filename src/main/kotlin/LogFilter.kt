package org.iesra

import java.time.LocalDate

class LogFilter(val logs: List<Log>, val fromDate: LocalDate?, val toDate: LocalDate?) {
    fun filter(): List<Log> {
        if (fromDate == null) {
            return logs.filter { log -> log.dateTime <= toDate }
        } else if (toDate == null) {
            return logs.filter { log -> log.dateTime >= fromDate }
        } else {
            return logs.filter { log -> log.dateTime >= fromDate && log.dateTime <= toDate }
        }
    }
}