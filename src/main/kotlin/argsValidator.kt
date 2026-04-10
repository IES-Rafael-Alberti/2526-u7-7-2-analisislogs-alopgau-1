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
            return configParams.values.any { it == ConfigStatus.ERROR }
        } else {
            return false
        }
    }
    private fun minLength() = args.size >= 3
    private fun basicStructureChecker() = args[0] == "logtool" && args[1] == "-i" && validatePath(2)
    private fun optionChecker() {
        if (args.size > 3) {
            var idx = 0
            args.drop(3).forEach {
             arg ->
            when {
              arg == "-f" || arg =="--from" -> {
                  if ((!validateDate(idx, "fromDate"))) configParams["fromDate"] = ConfigStatus.ERROR else configParams["fromDate"] =
                      ConfigStatus.ACTIVE
              }
              arg == "-t" || arg == "--to" -> {
                  if (!validateDate(idx, "toDate")) configParams["toDate"] = ConfigStatus.ERROR else configParams["toDate"] =
                      ConfigStatus.ACTIVE
              }
                arg == "-l" || arg == "--level" -> {
                if (!validateLevels(idx)) configParams["levelFilter"] = ConfigStatus.ERROR else configParams["levelFilter"] =
                    ConfigStatus.ACTIVE
                }
                arg == "-s" || arg == "--stats" -> configParams["showStats"] = ConfigStatus.ACTIVE

                arg == "-r" || arg == "--report" -> configParams["showReport"] = ConfigStatus.ACTIVE

                arg == "-o" || arg == "--output" -> {
                    if (!validatePath(idx)) configParams["fileOutput"] = ConfigStatus.ERROR else configParams["fileOutput"] =
                        ConfigStatus.ACTIVE
                }
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
    private fun validatePath(idx: Int): Boolean {
        if (idx < args.size-1) {
        if (File(args[idx+1]).exists() && File(args[idx+1]).canWrite()) {
            configValues["fileOutput"]?.set(0,args[idx+1])
            return true
        } else {
            return false
        }

        }
            return false
    }
    private fun validateDate(idx: Int, typeDate: String): Boolean {
        if (idx < args.size-1) {
            val regex = "([0-9]{4})-(0[1-9]|1[0-2])-([012][0-9]|3[01]) ([01][0-9]|2[0-3]):[0-5][0-9]".toRegex()
            if (regex.matches(args[idx+1])) {
                configValues[typeDate]?.set(0, args[idx+1])
                return true
            }
        } else {
            return false
        }
        return false

    }
    private fun validateLevels(idx: Int): Boolean {
        if ("," in args && idx < args.size-1) {
        val levels = args[idx+1].split(",")
        if (levels.all { level -> checkLevel(level) }) {
            levels.forEachIndexed { index, level ->
                configValues["filterValues"]?.set(index,level)
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
