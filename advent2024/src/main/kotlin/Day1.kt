import kotlin.math.abs

private fun parseInput(fileName: String) =
    Utils.readLines(fileName)
        .map {
            val (first, second) = it.split("   ")
            first.toInt() to second.toInt()
        }
        .unzip()

fun main() {

    // part 1
    run {
        val (listA, listB) = parseInput("day1.txt")
        val ans = part1TotalDistance(listA, listB)
        println(ans)
    }

    // part 2
    run {
        val (listA, listB) = parseInput("day1.txt")
        val ans = part2SimilarityScore(listA, listB)
        println(ans)
    }
}

private fun part1TotalDistance(listA: List<Int>, listB: List<Int>): Int =
    listA.sorted()
        .zip(listB.sorted())
        .sumOf { (eleA, eleB) -> abs(eleA - eleB) }

private fun part2SimilarityScore(listA: List<Int>, listB: List<Int>) =
    listA.sumOf { eleA ->
        val count = listB.count { eleB -> eleA == eleB }
        eleA * count
    }
