object Utils {

    fun readLines(fileName: String): List<String> =
        requireNotNull(this::class.java.getResourceAsStream(fileName)) { "Input not found." }
            .reader()
            .readLines()
}