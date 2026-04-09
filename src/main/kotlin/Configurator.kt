package org.iesra

class Configurator(private val params: Map<String, ConfigStatus>, private val configValues: Map<String, Array<String?>> ) {

    fun configure(): Boolean {
        if (params.isEmpty() && configValues.isEmpty()) return false
        val finalConfig = params.map {
        var booleanStatus = false
            var argValues: List<String>? = null
            if (it.value == ConfigStatus.ACTIVE) {
                booleanStatus = true
            }
            if (it.key in configValues.keys) argValues = configValues[it.key]?.filterNotNull()

            mapOf<String, Pair<Boolean, List<String>?>>(it.key to Pair(booleanStatus, argValues))
        }
        return true
    }
}