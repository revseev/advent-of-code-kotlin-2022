import GameResult.*

fun main() {
    println(day2task1())
    println(day2task2())
}

fun day2task1(): Int {
    var userScore = 0
    readInputAsSequence("Day02") {
        userScore = sumOf { getScore(RPS.of(it[0]), RPS.of(it[2])) }
    }
    return userScore
}

fun day2task2(): Int {
    var userScore = 0
    readInputAsSequence("Day02") {
        userScore = sumOf { getScore(RPS.of(it[0]), GameResult.of(it[2])) }
    }
    return userScore
}

sealed class GameResult {
    abstract val score: Int

    companion object {
        fun of(char: Char): GameResult = when (char) {
            'X' -> LOOSE
            'Y' -> DRAW
            'Z' -> WIN
            else -> throw IllegalArgumentException("Failed to parse: $char")
        }
    }

    object LOOSE : GameResult() {
        override val score = 0
    }

    object DRAW : GameResult() {
        override val score = 3
    }

    object WIN : GameResult() {
        override val score = 6
    }

}


sealed class RPS {
    abstract val score: Int
    abstract val winAgainst: RPS
    abstract val looseAgainst: RPS

    object ROCK : RPS() {
        override val score = 1
        override val winAgainst = SCISSORS
        override val looseAgainst = PAPER
    }

    object PAPER : RPS() {
        override val score = 2
        override val winAgainst = ROCK
        override val looseAgainst = SCISSORS
    }

    object SCISSORS : RPS() {
        override val score = 3
        override val winAgainst = PAPER
        override val looseAgainst = ROCK
    }

    companion object {
        fun of(char: Char): RPS = when (char) {
            'A', 'X' -> ROCK
            'B', 'Y' -> PAPER
            'C', 'Z' -> SCISSORS
            else -> throw IllegalArgumentException("Failed to parse: $char")
        }
    }
}

private fun RPS.gameAgainst(other: RPS): GameResult = when (other) {
    this -> DRAW
    this.winAgainst -> WIN
    this.looseAgainst -> LOOSE
    else -> throw IllegalArgumentException("Unknown RPS: $other")
}

private fun getRPSforResult(other: RPS, result: GameResult): RPS = when (result) {
    DRAW -> other
    LOOSE -> other.winAgainst
    WIN -> other.looseAgainst
}

fun getScore(other: RPS, my: RPS): Int {
    return my.score + my.gameAgainst(other).score
}


fun getScore(other: RPS, neededResult: GameResult): Int {
    return getRPSforResult(other, neededResult).score + neededResult.score
}