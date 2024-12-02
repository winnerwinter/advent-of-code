import kotlin.math.abs

fun part1TotalDistance(listA: List<Int>, listB: List<Int>): Int =
    listA.sorted()
        .zip(listB.sorted())
        .sumOf { (eleA, eleB) -> abs(eleA - eleB) }

fun part2SimilarityScore(listA: List<Int>, listB: List<Int>) =
    listA.sumOf { eleA ->
        val count = listB.count { eleB -> eleA == eleB }
        eleA * count
    }