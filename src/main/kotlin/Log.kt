package org.iesra
import java.time.LocalDateTime

class Log(val dateTime: LocalDateTime, val level: LogLevel, val message: String) {
}