package org.iesra

class Configurator(private val params: Map<String, ConfigStatus>, private val configValues: Map<String, Array<String?>> ) {
    fun configure() {
        val activeValues = configValues.values.filterNotNull()
        params.entries.forEach { (param, status) ->
            if (param in configValues.keys)
        }
    }
}