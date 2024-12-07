import java.util.concurrent.atomic.AtomicInteger

private fun parseInput(fileName: String): WordSearch =
    Utils.readLines(fileName)
        .map { it.windowed(1, 1).map(String::single) }
        .let { WordSearch(it.size, it) }

fun main() {

    // part 1
    run {
        val grid = parseInput("day4.txt")
        val ans = grid.part1FindWordCount("XMAS")
        println(ans)
    }

    // part 2
    run {
        val grid = parseInput("day4.txt")
        val ans = grid.part2FindCrossCount("MAS")
        println(ans)
    }
}

private data class WordSearch(
    val count: Int,
    val grid: List<List<Char>>
) {

    enum class Direction(val hort: Int, val vert: Int) {
        UP(0, -1),
        UP_RIGHT(1, -1),
        UP_LEFT(-1, -1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        DOWN(0, 1),
        DOWN_RIGHT(1, 1),
        DOWN_LEFT(-1, 1)
    }

    fun Pair<Int, Int>.checkMatch(word: String, direction: Direction): Boolean =
        word.foldIndexed(true) { wordIdx, acc, compare ->
            val (rowIdx, colIdx) = this
            acc && compare == grid
                .getOrNull(rowIdx + wordIdx * direction.vert)
                ?.getOrNull(colIdx + wordIdx * direction.hort)
        }

    fun part1FindWordCount(word: String): Int =
        AtomicInteger().apply {
            for (rowIdx in 0..count) {
                for (colIdx in 0..count) {
                    with (rowIdx to colIdx) {
                        listOf(
                            checkMatch(word, Direction.UP),
                            checkMatch(word, Direction.UP_RIGHT),
                            checkMatch(word, Direction.RIGHT),
                            checkMatch(word, Direction.DOWN_RIGHT),
                            checkMatch(word, Direction.DOWN),
                            checkMatch(word, Direction.DOWN_LEFT),
                            checkMatch(word, Direction.LEFT),
                            checkMatch(word, Direction.UP_LEFT)
                        )
                            .forEach { if (it) this@apply.incrementAndGet() }
                    }
                }
            }
        }.toInt()

    fun part2FindCrossCount(word: String): Int =
        AtomicInteger().apply {
            for (rowIdx in 0..count) {
                for (colIdx in 0..count) {
                    val startingDownRightCoordinate = rowIdx to colIdx
                    val startingUpRightCoordinate = rowIdx + word.length - 1 to colIdx

                    val downRightMatch = with (startingDownRightCoordinate) {
                        checkMatch(word, Direction.DOWN_RIGHT) || checkMatch(word.reversed(), Direction.DOWN_RIGHT)
                    }
                    val upRightMatch = with (startingUpRightCoordinate) {
                        checkMatch(word, Direction.UP_RIGHT) || checkMatch(word.reversed(), Direction.UP_RIGHT)
                    }

                    if (downRightMatch && upRightMatch) this.incrementAndGet()
                }
            }
        }.toInt()
}