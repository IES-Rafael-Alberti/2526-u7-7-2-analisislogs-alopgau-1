package org.iesra
import java.io.File
class argsValidator(private val args: Array<String>) {
private val configParams = mutableMapOf<String,ConfigStatus>(
    "fromDate" to ConfigStatus.INACTIVE,
    "toDate" to ConfigStatus.INACTIVE,
    "ignore" to ConfigStatus.INACTIVE,
    "levelFilter" to ConfigStatus.INACTIVE,
    "showStats" to ConfigStatus.INACTIVE,
    "showReport" to ConfigStatus.INACTIVE,
    "fileOutput" to ConfigStatus.INACTIVE,
    "consoleOutput" to ConfigStatus.INACTIVE,
    "showHelp" to ConfigStatus.INACTIVE,
)
    private val configValues = mutableMapOf<String, Array<String?>>(
        "fromDate" to arrayOf(null),
        "toDate" to arrayOf(null),
        "levelFilter" to arrayOf(null,null,null),
        "fileOutput" to arrayOf(null)

    )
    fun returnResults(): Pair<MutableMap<String, ConfigStatus>, MutableMap<String, Array<String?>>> =
        if (validate()) return Pair(configParams,configValues) else throw IllegalArgumentException("Se introdujo algún argumento inválido")
    private fun validate(): Boolean {
        if (minLength() && basicStructureChecker()) {
            optionChecker()
            return configParams.values.all { it != ConfigStatus.ERROR } && !(configParams["showStats"] == ConfigStatus.ACTIVE && configParams["showReport"] == ConfigStatus.ACTIVE) && !(configParams["consoleOutput"] == ConfigStatus.ACTIVE && configParams["fileOutput"] == ConfigStatus.ACTIVE) && !(configParams["consoleOutput"] == ConfigStatus.INACTIVE && configParams["fileOutput"] == ConfigStatus.INACTIVE)
        } else {
            return false
        }
    }
    private fun minLength() = args.size >= 3
    private fun basicStructureChecker() = args[0] == "logtool" && args[1] == "-i" && validatePath(1,args.toList())
    private fun optionChecker() {
        if (args.size > 3) {
            var idx = 0
            val argsRemaining = args.drop(3)
            argsRemaining.forEach {

             arg ->
            when {
              arg == "-f" || arg =="--from" -> {
                  if ((!validateDate(idx, "fromDate",argsRemaining))) configParams["fromDate"] = ConfigStatus.ERROR else configParams["fromDate"] =
                      ConfigStatus.ACTIVE
              }
              arg == "-t" || arg == "--to" -> {
                  if (!validateDate(idx, "toDate",argsRemaining)) configParams["toDate"] = ConfigStatus.ERROR else configParams["toDate"] =
                      ConfigStatus.ACTIVE
              }
                arg == "-l" || arg == "--level" -> {
                if (!validateLevels(idx,argsRemaining)) configParams["levelFilter"] = ConfigStatus.ERROR else configParams["levelFilter"] =
                    ConfigStatus.ACTIVE
                }
                arg == "-s" || arg == "--stats" -> configParams["showStats"] = ConfigStatus.ACTIVE

                arg == "-r" || arg == "--report" -> configParams["showReport"] = ConfigStatus.ACTIVE

                arg == "-o" || arg == "--output" -> {
                    if (!validatePath(idx,argsRemaining)) configParams["fileOutput"] = ConfigStatus.ERROR else configParams["fileOutput"] =
                        ConfigStatus.ACTIVE
                }
                arg == "-p" || arg == "--stdout" -> configParams["consoleOutput"] = ConfigStatus.ACTIVE
                arg == "--ignore-invalid" -> {
                configParams["ignore"] = ConfigStatus.ACTIVE
                }
                arg == "-h" || arg == "--help" -> {
                    configParams["showHelp"] = ConfigStatus.ACTIVE
                }
            }
                idx++
            }
            }

    }
    private fun validatePath(idx: Int,argsRemaining: List<String> ): Boolean {
        if (idx < argsRemaining.size-1) {
        if (File(argsRemaining[idx+1]).exists() && File(argsRemaining[idx+1]).canWrite()) {
            configValues["fileOutput"]?.set(0,argsRemaining[idx+1])
            return true
        } else {
            return false
        }

        }
            return false
    }
    private fun validateDate(idx: Int, typeDate: String, argsRemaining: List<String>): Boolean {
        if (idx < argsRemaining.size-2) {
            val regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$".toRegex()
            val datetime = listOf(argsRemaining[idx+1], argsRemaining[idx+2]).joinToString(" ")
            if (regex.matches(datetime)) {
                configValues[typeDate]?.set(0, datetime)
                return true
            }
        } else {
            return false
        }
        return false

    }
    private fun validateLevels(idx: Int, argsRemaining: List<String>): Boolean {
        if ("," in argsRemaining[idx+1] && idx < argsRemaining.size-1) {
        val levels = argsRemaining[idx+1].split(",")
        if (levels.all { level -> checkLevel(level) }) {
            levels.forEachIndexed { index, level ->
                configValues["levelFilter"]?.set(index,level)
                return true
            }
        }
        } else {
            return false
        }
        return false
    }
    private fun checkLevel(level: String): Boolean {
        try {
            LogLevel.valueOf(level)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }
}

