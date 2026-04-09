package org.iesra

fun main(args: Array<String>) {
val argsInfo = argsValidator(args).returnConfig()
val configurator = Configurator(argsInfo?.first ?: emptyMap() , argsInfo?.second ?: emptyMap())
val
}