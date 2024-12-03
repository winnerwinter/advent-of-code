import org.junit.jupiter.api.Test

class Day2Test {

    private fun parseInput(fileName: String): List<Report> =
        Utils.readLines(fileName)
            .map { it.split(" ").map(String::toInt) }

    @Test
    fun part1() {
        val reports = parseInput("day2.txt")
        val ans = reports.sumOf { if (part1IsReportSafe(it)) 1 else 0L }
        println(ans)
    }

    @Test
    fun part2() {
        val reports = parseInput("day2.txt")
        val ans = reports.sumOf { report -> if (part2WithDampen(report)) 1 else 0L }
        println(ans)
    }
}