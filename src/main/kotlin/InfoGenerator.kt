package org.iesra

import java.time.LocalDateTime

interface InfoGenerator {
    fun generateInfo(fileName: String,
                     fromDate: String,
                     toDate: String,
                     levelsIncluded: List<LogLevel>,
                     processedLines: Int,
                     validLines: Int,
                     invalidLines: Int,
                     levelsCounted: Map<LogLevel, Int>,
                     firstDate: LocalDateTime,
                     lastDate: LocalDateTime): String
}