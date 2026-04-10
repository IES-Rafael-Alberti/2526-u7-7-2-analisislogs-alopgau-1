package org.iesra
import java.io.File
class FileManager() {
    fun process(path: String): List<String> {
        if (pathIsValid(path)) {
            val f = File(path)
            return read(f)
        }
        return emptyList()
    }
    private fun read(file: File) = file.readLines()
    private fun pathIsValid(path: String): Boolean {
        val f = File(path)
    return f.exists() && f.isFile && f.canWrite()
    }

}