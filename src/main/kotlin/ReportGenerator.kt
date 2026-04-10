package org.iesra
import java.time.LocalDateTime

class ReportGenerator(private val reportMode: Boolean, val logs: List<Log>) {
    fun generate(
        fileName: String,
        fromDate: LocalDateTime?,
        toDate: LocalDateTime?,
        levelsIncluded: List<LogLevel>,
        processedLines: Int,
        validLines: Int,
        invalidLines: Int,
        levelsCounted: Map<LogLevel, Int>,
        firstDate: LocalDateTime,
        lastDate: LocalDateTime

    )  = buildString {
        appendLine("INFORME DE LOGS")
        appendLine("===============")
        appendLine("Fichero analizado: ${fileName}")
        appendLine("Rango aplicado: ${fromDate} -> ${toDate}")
        appendLine("Niveles incluidos: $levelsIncluded")
        appendLine()
        appendLine("Resumen:")
        appendLine("- Líneas procesadas: $processedLines")
        appendLine("- Líneas válidas: $validLines")
        appendLine("- Líneas inválidas: $invalidLines")
        appendLine()
        appendLine("Conteo por nivel")
        appendLine("- INFO: ${levelsCounted[LogLevel.INFO]}")
        appendLine("- WARNING: ${levelsCounted[LogLevel.WARNING]}")
        appendLine("- ERROR: ${levelsCounted[LogLevel.ERROR]}")
        appendLine()
        appendLine("Periodo detectado en los logs:")
        appendLine("- Primera entrada: ${firstDate}")
        appendLine("- Última entrada: ${lastDate}")
        if (reportMode) {
            appendLine("Entradas encontradas:")
            logs.forEach { println(it) }
        }
    }
}