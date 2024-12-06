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

    fun String.checkDirection(direction: Direction, startingCoordinate: Pair<Int, Int>): Boolean =
        this.mapIndexed { wordIdx, compare ->
            val (rowIdx, colIdx) = startingCoordinate
            compare == grid
                .getOrNull(rowIdx + wordIdx * direction.vert)
                ?.getOrNull(colIdx + wordIdx * direction.hort)
        }.all { it }

    fun part1FindWordCount(word: String): Int =
        List(count) { rowIdx ->
            List(count) { colIdx ->
                listOf(
                    word.checkDirection(Direction.UP, rowIdx to colIdx),
                    word.checkDirection(Direction.UP_RIGHT, rowIdx to colIdx),
                    word.checkDirection(Direction.RIGHT, rowIdx to colIdx),
                    word.checkDirection(Direction.DOWN_RIGHT, rowIdx to colIdx),
                    word.checkDirection(Direction.DOWN, rowIdx to colIdx),
                    word.checkDirection(Direction.DOWN_LEFT, rowIdx to colIdx),
                    word.checkDirection(Direction.LEFT, rowIdx to colIdx),
                    word.checkDirection(Direction.UP_LEFT, rowIdx to colIdx)
                )
                    .sumOf { if (it) 1 as Int else 0 }
            }
        }
            .sumOf { it.sumOf { it } }

    fun part2FindCrossCount(word: String) =
        List(count) { rowIdx ->
            List(count) { colIdx ->
                val downRight = word.checkDirection(Direction.DOWN_RIGHT, rowIdx to colIdx)
                        || word.reversed().checkDirection(Direction.DOWN_RIGHT, rowIdx to colIdx)
                val upRight = word.checkDirection(Direction.UP_RIGHT, rowIdx + word.length - 1 to colIdx)
                        || word.reversed().checkDirection(Direction.UP_RIGHT, rowIdx + word.length - 1 to colIdx)
                if (downRight && upRight) 1 as Int else 0 }
        }
            .sumOf { it.sumOf { it } }
}