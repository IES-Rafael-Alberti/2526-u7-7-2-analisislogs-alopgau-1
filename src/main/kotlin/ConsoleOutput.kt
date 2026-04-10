package org.iesra

class ConsoleOutput(): OutputMethod {
    override fun output(report: String) = println(report)
    fun help() = println("Uso:\n" +
            "  logtool -i <fichero> [opciones]\n" +
            "\n" +
            "Descripción:\n" +
            "  Procesa un fichero de logs con formato:\n" +
            "    [YYYY-MM-DD HH:MM:SS] NIVEL Mensaje\n" +
            "\n" +
            "Opciones:\n" +
            "  -i, --input <fichero>        Fichero de entrada (obligatorio)\n" +
            "  -f, --from <fechaHora>       Fecha/hora inicial inclusive\n" +
            "                               Formato: \"YYYY-MM-DD HH:MM:SS\"\n" +
            "  -t, --to <fechaHora>         Fecha/hora final inclusive\n" +
            "                               Formato: \"YYYY-MM-DD HH:MM:SS\"\n" +
            "  -l, --level <niveles>        Filtra niveles: INFO, WARNING, ERROR\n" +
            "                               Puede indicarse una lista separada por comas\n" +
            "  -s, --stats                  Muestra solo estadísticas\n" +
            "  -r, --report                 Genera informe completo\n" +
            "  -o, --output <fichero>       Guarda la salida en un fichero\n" +
            "  -p, --stdout                 Muestra la salida por consola\n" +
            "      --ignore-invalid         Ignora líneas inválidas y continúa\n" +
            "  -h, --help                   Muestra esta ayuda\n" +
            "\n" +
            "Ejemplos:\n" +
            "  logtool -i app.log -p --report\n" +
            "  logtool -i app.log -f \"2025-01-15 00:00:00\" -t \"2025-01-15 23:59:59\" -p --stats\n" +
            "  logtool -i app.log -l ERROR,WARNING -o informe.txt --report")
    }
