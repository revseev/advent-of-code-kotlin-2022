fun main() {
    day05task1()
    day05task2()

}

fun day05task1() {
    var first = true
    var stacks: List<ArrayDeque<Char>>? = null
    readInputAsSequenceGrouped("Day05") {
        forEach { lines ->
            if (first) {
                stacks = parseStacks(lines.toList())
                first = false
            } else {
                lines.forEach {
                    val (times, from, to) = parseCommand(it)
                    stacks!!.move(times, from, to)
                }
                first = true
            }
        }
    }
    println(stacks!!.mapNotNull { it.lastOrNull() }.joinToString(""))
}

fun day05task2() {
    var first = true
    var stacks: List<ArrayDeque<Char>>? = null
    readInputAsSequenceGrouped("Day05") {
        forEach { lines ->
            if (first) {
                stacks = parseStacks(lines.toList())
                first = false
            } else {
                lines.forEach {
                    val (times, from, to) = parseCommand(it)
                    stacks!!.move2(times, from, to)
                }
                first = true
            }
        }
    }
    println(stacks!!.mapNotNull { it.lastOrNull() }.joinToString(""))
}

private fun parseCommand(it: String): Triple<Int, Int, Int> {
    val nums = it.split(Regex("\\D+"))
    return Triple(nums[1].toInt(), nums[2].toInt() - 1, nums[3].toInt() - 1)
}

private fun List<ArrayDeque<Char>>.move(times: Int, from: Int, to: Int) {
    for (i in 1..times) {
        get(to).addLast(get(from).removeLast())
    }
}

private fun List<ArrayDeque<Char>>.move2(times: Int, from: Int, to: Int) {
    val source = get(from)
    val dest = get(to)
    for (i in source.size - times until source.size) {
        dest.addLast(source[i])
    }
    repeat(times) {
        source.removeLast()
    }
}

private fun parseStacks(stacksLines: List<String>): List<ArrayDeque<Char>> {
    if (stacksLines.size < 2) throw IllegalArgumentException("Invalid input, expected size > 1")

    val columns = getColumnsCount(stacksLines.first())
    val stacks = List(columns) {
        ArrayDeque<Char>(stacksLines.size - 1)
    }
    for (column in 0 until columns) {
        val stack = stacks[column]
        for (c in stacksLines.size - 2 downTo 0) {
            val line = stacksLines[c]
            val char = line.getCharForColumn(column) ?: break
            stack.addLast(char)
        }
    }
    return stacks
}

private fun getColumnsCount(line: String): Int = (line.length + 1) / 4

private fun String.getCharForColumn(column: Int): Char? {
    val char = get(getIndexOfColumn(column))
    return if (char.isLetter()) char else null
}

private fun getIndexOfColumn(column: Int): Int = 4 * column + 1
