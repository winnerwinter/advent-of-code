import kotlin.math.abs

typealias Report = List<Int>

fun part1IsReportSafe(report: Report): Boolean {
    val windows = report.windowed(size = 2, step = 1)
    val saneOrder = windows.map { (first, second) -> first.compareTo(second) }.distinct().singleOrNull().let { it != null }
    val inSafeRange = windows.all { (first, second) -> first isSafeRangeWith second }
    return saneOrder && inSafeRange
}

infix fun Int.isSafeRangeWith(other: Int): Boolean =
    abs(this - other) in 1..3

