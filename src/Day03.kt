fun main() {
    println(day3task1())
    println(day3task2())
}

fun day3task1(): Int {
    var sum = 0
    readInputAsSequence("Day03") {
        sum = sumOf { findCommonCharCode(it) }
    }
    return sum
}

fun day3task2(): Int {
    var sum = 0
    readInputAsSequence("Day03") {
        sum = chunked(3) {
            findCommonCharCode(it[0], it[1], it[2])
        }.sum()
    }
    return sum
}

private val CHAR_FREQUENCY_MAP = IntArray(53)

fun findCommonCharCode(a: String, b: String, c: String, frequencyBuffer: IntArray = CHAR_FREQUENCY_MAP): Int {
    frequencyBuffer.clear()

    for (i in a) {
        val code = i.getCharCode()
        frequencyBuffer[code] = 1
    }

    for (j in b) {
        val code = j.getCharCode()
        if (frequencyBuffer[code] > 0) {
            frequencyBuffer[code] = 2
        }
    }

    for (k in c) {
        val code = k.getCharCode()
        if (frequencyBuffer[code] == 2) {
            return code
        }
    }
    return 0
}

fun findCommonCharCode(string: String, frequencyBuffer: IntArray = CHAR_FREQUENCY_MAP): Int {
    frequencyBuffer.clear()
    val length = string.length

    for (l in 0 until length / 2) {
        val code = string[l].getCharCode()
        frequencyBuffer[code] = 1
    }
    for (r in length / 2..length) {
        val code = string[r].getCharCode()
        if (frequencyBuffer[code] > 0) {
            return code
        }
    }
    return 0
}

fun Char.getCharCode(): Int = if (isUpperCase()) {
    (code - 'A'.code + 27)
} else {
    (code - 'a'.code + 1)
}
