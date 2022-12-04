fun main() {
    day04task1()
    day04task2()
}

fun day04task1() {
    readInputAsSequence("Day04") {
        println(filter {
            val split = it.split(",", "-").map(String::toInt)
            val set1 = IntRange(split[0], split[1]).toSet()
            val set2 = IntRange(split[2], split[3]).toSet()
            set1.intersect(set2).size == minOf(set1.size, set2.size)
        }.count())
    }
}

fun day04task2() {
    readInputAsSequence("Day04") {
        println(filter {
            val split = it.split(",", "-").map(String::toInt)
            val range1 = IntRange(split[0], split[1]).toSet()
            val range2 = IntRange(split[2], split[3]).toSet()
            range1.intersect(range2).isNotEmpty()
        }.count())
    }
}