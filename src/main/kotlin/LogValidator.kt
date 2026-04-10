package org.iesra

class LogValidator(private val ignoreMode: Boolean, private val logs: List<String>) {
     fun scan(): Pair<List<String>, List<String>> {
        if (!ignoreMode) {
         val (valid,nonvalid) = logs.partition {log -> insideBracketsChecker(log) && afterBracketsChecker(log)}
        return Pair(valid,nonvalid)
        } else {
            var i = 0
            val valid = mutableListOf<String>()
            while (insideBracketsChecker(logs[i]) && afterBracketsChecker(logs[i])) {
                valid.add(logs[i])
                i++
            }
            return Pair(valid,emptyList())

        }


        }

    }
    private fun insideBracketsChecker(log: String): Boolean {
        val info = log.substringBefore(']').substringAfter('[')
        val regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$".toRegex()
        return regex.matches(info)
    }
    private fun afterBracketsChecker(log: String): Boolean {
        val info = log.substringAfter(']').trim().split(" ")
        return checkLevel(info[0])
    }
     private fun checkLevel(level: String): Boolean {
        try {
            LogLevel.valueOf(level)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }


