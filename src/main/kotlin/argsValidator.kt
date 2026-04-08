package org.iesra

class argsValidator(val args: Array<String>) {
val configParams = mapOf<String, ConfigStatus>(
    "fromDate" to ConfigStatus.INACTIVE,
    "toDate" to ConfigStatus.INACTIVE,
    "ignore" to ConfigStatus.INACTIVE,
    "levelFilter" to ConfigStatus.INACTIVE,
    "showStats" to ConfigStatus.INACTIVE,
    "FileOutputChosen" to ConfigStatus.INACTIVE,
    "showHelp" to ConfigStatus.INACTIVE
)
fun validateConfig() = minLength() && basicStructureChecker()
    private fun minLength() = args.size >= 3
    private fun basicStructureChecker() = args[0] == "logtool" && args[1] == "-i"
    private fun optionChecker(): Boolean {
        if (args.size > 3) {
            var counter = 0
            args.forEach {
             flag ->
            when {
              flag == "-f" || flag =="--from" -> {
                  if ((!validateDate(counter))) configParams["fromDate"] = ConfigStatus.ERROR else configParams["fromDate"] =
                      ConfigStatus.ACTIVE
              }
              flag == "-t" || flag == "--to" -> {
                  if (!validateDate(counter)) configParams["toDate"] = ConfigStatus.ERROR
              }

            }
                counter++
            }

    }
}
    private fun validateDate(counter: Int): Boolean {
        if (counter < args.size-1) {
            val regex = "([0-9]{4})-(0[1-9]|1[0-2])-([012][0-9]|3[01]) ([01][0-9]|2[0-3]):[0-5][0-9]".toRegex()
            return regex.matches(args[counter+1])
        }
    }
}
