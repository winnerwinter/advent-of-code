package day3

import org.junit.jupiter.api.Test
import part1SearchMul
import part2DoDont

class Day3Test {

    private fun parseInput(fileName: String): String =
        Utils.readLines(fileName).joinToString("")

    @Test
    fun part1() {
        val string = parseInput("day3.txt")
        val ans = part1SearchMul(string)
        println(ans)
    }

    @Test
    fun part2() {
        val string = parseInput("day3.txt")
        val ans = part2DoDont(string)
        println(ans)
    }

}