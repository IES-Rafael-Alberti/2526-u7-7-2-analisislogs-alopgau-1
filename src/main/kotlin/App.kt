package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class App(val args: Array<String>) {
    val argsInfo = argsValidator(args).returnResults()
    val configuration = Configurator(argsInfo.first, argsInfo.second).configure()
    val fManager = FileManager()
    val logV = LogValidator(configuration.get("ignore")?.first ?: false, fManager.process(args[2]))
    val logs = LogProcessor().process(logV.scan().first)
    val stats = LogStats(logV.scan().second, logs)
    val logF = LogFilter(
        logs,
        parseDate(configuration.get("fromDate")?.second[0]),
        parseDate(configuration.get("toDate")?.second[0])
    )
    val outputMethod : OutputMethod? = null
    val infoGenerator : InfoGenerator? = null
    private fun parseDate(date: String?): LocalDateTime? {
        if (date != null) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss")
            val dateTime = LocalDateTime.parse(date, formatter)
            return dateTime
        }
        return null
    }
}