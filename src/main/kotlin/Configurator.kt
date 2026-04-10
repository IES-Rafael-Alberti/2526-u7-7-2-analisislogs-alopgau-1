package org.iesra

class Configurator(private val params: Map<String, ConfigStatus>, private val configValues: Map<String, Array<String?>> ) {

    fun configure(): MutableMap<String, Pair<Boolean, List<String?>>> {
        val configuration = mutableMapOf<String, Pair<Boolean, List<String?>>>()
        params.entries.forEach {
        var booleanStatus = false
            var argValues: List<String?> = emptyList()
            if (it.value == ConfigStatus.ACTIVE) {
                booleanStatus = true
            }
            if (it.key in configValues.keys)
                argValues = configValues[it.key]?.filterNotNull() ?: emptyList()
                if (argValues.isEmpty()) argValues = listOf<String?>(null)
            configuration.put(it.key, Pair(booleanStatus,argValues))

        }
        return configuration
        }
    }
