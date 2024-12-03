import kotlin.math.abs

typealias Report = List<Int>

fun part1IsReportSafe(report: Report): Boolean {
    val windows = report.windowed(size = 2, step = 1)
    val saneOrder = windows.map { (first, second) -> first.compareTo(second) }.distinct().singleOrNull().let { it != null }
    val inSafeRange = windows.all { (first, second) -> first isSafeRangeWith second }
    return saneOrder && inSafeRange
}

fun part2WithDampen(report: Report): Boolean {
    val subReports = List(report.size) { index ->
        when (index) {
            0 -> report.subList(1, report.size)
            report.size - 1 -> report.subList(0, report.size - 1)
            else -> report.subList(0, index) + report.subList(index + 1, report.size)
        }
    }

    return part1IsReportSafe(report) || subReports.any { part1IsReportSafe(it) }
}

infix fun Int.isSafeRangeWith(other: Int): Boolean =
    abs(this - other) in 1..3

