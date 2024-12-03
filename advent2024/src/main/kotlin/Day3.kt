fun part1SearchMul(blob: String) =
    "mul\\(\\d+,\\d+\\)"
        .toRegex()
        .findAll(blob)
        .fold(0) { acc, matchResult ->
            val (a, b) = matchResult.value
                .substringAfter("(").dropLast(1)
                .split(",")
                .map { it.toInt() }
            acc + a * b
        }

fun part2DoDont(blob: String): Int {
    var multOn = true
    return "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)"
        .toRegex()
        .findAll(blob)
        .fold(0) { acc, matchResult ->
            when (val value = matchResult.value) {
                "do()" -> run {
                    multOn = true
                    acc + 0
                }
                "don't()" -> run {
                    multOn = false
                    acc + 0
                }
                else -> {
                    val (a, b) = value
                        .substringAfter("(").dropLast(1)
                        .split(",")
                        .map { it.toInt() }
                    acc + if (multOn) a * b else 0
                }
            }
        }
}
