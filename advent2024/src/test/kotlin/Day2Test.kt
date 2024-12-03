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
        val ans = reports.sumOf { report ->
            val subReports = List(report.size) { index ->
                when (index) {
                    0 -> report.subList(1, report.size)
                    report.size - 1 -> report.subList(0, report.size - 1)
                    else -> report.subList(0, index) + report.subList(index + 1, report.size)
                }
            }
            if (part1IsReportSafe(report) || subReports.any { part1IsReportSafe(it) }) 1 else 0L
        }
        println(ans)
    }
}