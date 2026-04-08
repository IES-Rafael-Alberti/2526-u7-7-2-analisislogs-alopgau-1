package org.iesra

import java.io.File

class FileOutput(val file: File): OutputMethod {
    override fun output(report: String) = file.writeText(report)

}