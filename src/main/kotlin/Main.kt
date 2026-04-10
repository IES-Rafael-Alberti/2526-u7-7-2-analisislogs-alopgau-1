package org.iesra

fun main(args: Array<String>) {
val argsInfo = argsValidator(args).returnResults()
val configuration = Configurator(argsInfo.first,argsInfo.second).configure()
val fileManager = FileManager()
val logValidator = LogValidator(configuration.get("ignore")?.first ?: false, fileManager.process(args[2]) )
val scannedLogs = logValidator.scan()
val processor = LogProcessor()
val logs = processor.process(scannedLogs.first)
if (configuration.get("showStats")?.first ?: false) {
    val generator = LogStats(scannedLogs.second,logs)
} else {
    val generator = ReportGenerator()
}
}
