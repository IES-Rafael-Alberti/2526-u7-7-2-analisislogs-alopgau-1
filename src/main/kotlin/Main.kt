package org.iesra

fun main(args: Array<String>) {
val argsInfo = argsValidator(args).returnResults()
val configuration = Configurator(argsInfo?.first ?: emptyMap() , argsInfo?.second ?: emptyMap()).configure()
val logValidator = LogValidator(configuration?.get("ignore")?.first)
}