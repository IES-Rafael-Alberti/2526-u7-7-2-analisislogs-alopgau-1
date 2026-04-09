package org.iesra

class LogValidator(private val ignore: Boolean, private val logs: List<String>) {

    fun validateAll(): Pair<List<String>, List<String>> {
        val (valid,nonvalid) = logs.partition {log -> bracketsExist(log) && insideBracketsChecker(log) && afterBracketsChecker(log)}
        return Pair(valid,nonvalid)

        }
    }
    private fun bracketsExist(log:String) =
        log.substringBefore(']', "").isNotEmpty() && log.substringAfter(']',"").isNotEmpty()
    private fun insideBracketsChecker(log: String): Boolean {
        val info = log.substringBefore(']').substringAfter('[')
        val regex = "([0-9]{4})-(0[1-9]|1[0-2])-([012][0-9]|3[01]) ([01][0-9]|2[0-3]):[0-5][0-9]".toRegex()
        return regex.matches(info)
    }
    private fun afterBracketsChecker(log: String): Boolean {
        val info = log.substringAfter(']').split(" ")
        return info.drop(1).joinToString(" ").isNotEmpty() && checkLevel(info[0])
    }
     private fun checkLevel(level: String): Boolean {
        try {
            LogLevel.valueOf(level)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }

