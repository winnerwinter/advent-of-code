import org.junit.jupiter.api.Test

class Day1Test {

    private fun parseInput(fileName: String) =
        Utils.readLines(fileName)
            .map {
                val (first, second) = it.split("   ")
                first.toInt() to second.toInt()
            }
            .unzip()

    @Test
    fun part1() {
        val (listA, listB) = parseInput("day1.txt")
        val ans = part1TotalDistance(listA, listB)
        println(ans)
    }

    @Test
    fun part2() {
        val (listA, listB) = parseInput("day1.txt")
        val ans = part2SimilarityScore(listA, listB)
        println(ans)
    }
}