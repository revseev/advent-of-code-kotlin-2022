fun main() {
    println(task1())

    println(task2())
}

private fun task1(): Long {
    var max = 0L
    readInputAsSequence("Day01") {
        max = maxOf { elfCalories ->
            elfCalories.sumOf { calorie -> calorie.toLong() }
        }
    }
    return max
}

private fun task2(): Long {
    var largestThreeInTotal = 0L
    readInputAsSequence("Day01") {
        largestThreeInTotal = map { elfCalories ->
            elfCalories.sumOf { calorie -> calorie.toLong() }
        }.sortedDescending()
            .take(3).sum()
    }
    return largestThreeInTotal
}
