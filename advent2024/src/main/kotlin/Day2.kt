import kotlin.math.abs

private typealias Report = List<Int>

private fun parseInput(fileName: String): List<Report> =
    Utils.readLines(fileName)
        .map { it.split(" ").map(String::toInt) }

fun main() {

    // part 1
    run {
        val reports = parseInput("day2.txt")
        val ans = reports.sumOf { if (part1IsReportSafe(it)) 1 else 0L }
        println(ans)
    }

    // part 2
    run {
        val reports = parseInput("day2.txt")
        val ans = reports.sumOf { report -> if (part2WithDampen(report)) 1 else 0L }
        println(ans)
    }
}

private infix fun Int.isSafeRangeWith(other: Int): Boolean =
    abs(this - other) in 1..3

private fun part1IsReportSafe(report: Report): Boolean {
    val windows = report.windowed(size = 2, step = 1)
    val saneOrder = windows.map { (first, second) -> first.compareTo(second) }.distinct().singleOrNull().let { it != null }
    val inSafeRange = windows.all { (first, second) -> first isSafeRangeWith second }
    return saneOrder && inSafeRange
}

private fun part2WithDampen(report: Report): Boolean {
    val subReports = List(report.size) { index ->
        report.subList(0, index) + report.subList(index + 1, report.size)
    }

    return part1IsReportSafe(report) || subReports.any { part1IsReportSafe(it) }
}
