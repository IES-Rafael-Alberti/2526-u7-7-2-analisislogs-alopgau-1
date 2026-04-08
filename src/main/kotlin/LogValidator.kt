package org.iesra

class LogValidator() {
    fun validate(logs: List<String>): Pair<List<String>, List<String>> {

        val (valid,nonvalid) = logs.partition {log -> bracketsExist(log) && insideBracketsChecker(log) && afterBracketsChecker(log)}
        return Pair(valid,nonvalid)
    }
    fun bracketsExist(log:String) =
        log.substringBefore(']', "").isNotEmpty() && log.substringAfter(']',"").isNotEmpty()
    fun insideBracketsChecker(log: String): Boolean {
        val info = log.substringBefore(']').substringAfter('[')
        val regex = "([0-9]{4})-(0[1-9]|1[0-2])-([012][0-9]|3[01]) ([01][0-9]|2[0-3]):[0-5][0-9]".toRegex()
        return regex.matches(info)
    }
    fun afterBracketsChecker(log: String): Boolean {
        val info = log.substringAfter(']').split(" ")
        return info.drop(1).joinToString(" ").isNotEmpty() && checkLevel(info[0])
    }
    fun checkLevel(level: String): Boolean {
        try {
            LogLevel.valueOf(level)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }

}