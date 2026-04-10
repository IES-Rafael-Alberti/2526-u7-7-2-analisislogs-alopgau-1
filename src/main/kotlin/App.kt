package org.iesra

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.File

class App(val args: Array<String>) {
        private var reportMode = false
        private val argsInfo = argsValidator(args).returnResults()
    private var outputMethod : OutputMethod? = null
    private val configuration = Configurator(argsInfo.first, argsInfo.second).configure()
        private var HelpMode = configuration.get("showHelp")?.first ?: false
        private val fManager = FileManager()
        private val logV = LogValidator(configuration.get("ignore")?.first ?: false, fManager.process(args[2]))
        private var logs = LogProcessor().process(logV.scan().first)
        private val logF = LogFilter(
            logs,
            parseDate(configuration.get("fromDate")?.second[0]),
            parseDate(configuration.get("toDate")?.second[0]),
            parseLevels(configuration.get("levelFilter")?.second ?: emptyList()))


    fun execute() {
        setReportMode()
        loadOutputMethod()
        if (configuration.get("toDate")?.first ?: false || configuration.get("fromDate")?.first ?: false) logs = logF.filterByDate()
        if (configuration.get("levelFilter")?.first ?: false) logs = logF.filterByLevel()
        val stats = LogStats(logV.scan().second,logs)
        val info = ReportGenerator(reportMode,logs).generate(File(args[2]).name,logF.fromDate,logF.toDate,stats.levelsIncluded(stats.countByLevel()),stats.countAll(),stats.countValid(),stats.countInvalid(),stats.countByLevel(),stats.firstDate().dateTime,stats.lastDate().dateTime)
        outputMethod?.output(info)
        if (HelpMode) ConsoleOutput().help()


    }

    private fun parseDate(date: String?): LocalDateTime? {
        if (date != null) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val dateTime = LocalDateTime.parse(date, formatter)
            return dateTime
        }
        return null
    }
    private fun parseLevels(levels: List<String?>) = levels.filterNotNull().map { LogLevel.valueOf(it) }
    private fun loadOutputMethod() {
    if (configuration.get("fileOutput")?.first ?: false) outputMethod = FileOutput(File(configuration.get("fileOutput")?.second[0])) else outputMethod = ConsoleOutput()
    }
    private fun setReportMode() {
         if (configuration.get("showStats")?.first ?: false) reportMode = false else reportMode = true
    }
}