package org.iesra

class Configurator(private val params: Map<String, ConfigStatus>, private val configValues: Map<String, Array<String?>> ) {

    fun configure(): MutableMap<String, Pair<Boolean, List<String>?>>? {
        if (params.isEmpty() || configValues.isEmpty())
        val configuration = mutableMapOf<String, Pair<Boolean, List<String>?>>()
        params.entries.forEach {
        var booleanStatus = false
            var argValues: List<String>? = null
            if (it.value == ConfigStatus.ACTIVE) {
                booleanStatus = true
            }
            if (it.key in configValues.keys) argValues = configValues[it.key]?.filterNotNull()
            configuration.put(it.key, Pair(booleanStatus,argValues))
        }
        return configuration
        }
    }
