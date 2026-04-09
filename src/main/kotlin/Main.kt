package org.iesra

fun main(args: Array<String>) {
val argsInfo = argsValidator(args).returnResults()
val configuration = Configurator(argsInfo.first,argsInfo.second).configure()
val fileManager = FileManager()
val logValidator = LogValidator(configuration.get("ignore")?.first ?: false, fileManager.process(args[2]) )
val processor = LogProcessor()
processor.process()
}
