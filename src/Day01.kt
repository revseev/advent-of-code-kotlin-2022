fun main() {
    println(day1task1())

    println(day1task2())
}

private fun day1task1(): Long {
    var max = 0L
    readInputAsSequenceGrouped("Day01") {
        max = maxOf { elfCalories ->
            elfCalories.sumOf { calorie -> calorie.toLong() }
        }
    }
    return max
}

fun day1task2(): Long {
    var largestThreeInTotal = 0L
    readInputAsSequenceGrouped("Day01") {
        largestThreeInTotal = map { elfCalories ->
            elfCalories.sumOf { calorie -> calorie.toLong() }
        }.sortedDescending()
            .take(3).sum()
    }
    return largestThreeInTotal
}
