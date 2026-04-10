package org.iesra

class LogStats(val invalidLines: List<String>, val logs: List<Log>) {
    fun countAll() = invalidLines.size + logs.size
    fun countValid () = logs.size
    fun countInvalid() = invalidLines.size
    fun countByLevel() = logs.groupBy { it.level }.mapValues {it.value.size}
    fun lastDate() = logs.maxBy { it.dateTime }
    fun firstDate() = logs.minBy { it.dateTime }
    fun levelsIncluded(levelsCounted: Map<LogLevel, Int>) = levelsCounted.filter { (key, value) -> value != 0 }.keys.toList()

}