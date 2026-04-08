package org.iesra

class LogStats(invalidLines: List<String>, logs: List<Log>) {
    fun countAll(invalidLines: List<String>, logs: List<Log>) = invalidLines.size + logs.size
    fun countValid(logs: List<Log>) = logs.size
    fun countInvalid(invalidLines: List<String>) = invalidLines.size
    fun countByLevel(logs: List<Log>) = logs.groupBy { it.level }.mapValues {it.value.size}
    fun lastDate(logs: List<Log>) = logs.maxBy { it.dateTime }
    fun firstDate(logs: List<Log>) = logs.minBy { it.dateTime }
    fun levelsIncluded(levelsCounted: Map<LogLevel, Int>) = levelsCounted.filter { (key, value) -> value != 0 }.keys.toList()

}