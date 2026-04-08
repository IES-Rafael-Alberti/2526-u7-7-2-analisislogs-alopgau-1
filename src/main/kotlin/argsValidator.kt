package org.iesra

class argsValidator(val args: Array<String>) {
    fun validate()
    private fun minLength() = args.size >= 3
    private fun flagChecker() =
}