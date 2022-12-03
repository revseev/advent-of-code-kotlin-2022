import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String): List<String> = File("src", "$name.txt").readLines()

inline fun readInputAsSequence(name: String, block: Sequence<String>.() -> Unit) =
    File("src", "$name.txt").useLines {
        it.block()
    }

inline fun readInputAsSequenceGrouped(name: String, block: Sequence<Sequence<String>>.() -> Unit) =
    File("src", "$name.txt").useLines {
        it.groupedAsSequencesBy { line -> line.isNotBlank() }
            .block()
    }

fun <T> Sequence<T>.groupedAsSequencesBy(predicate: (T) -> Boolean): Sequence<Sequence<T>> = sequence {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val groupIterator = iterator.groupIterator(predicate)
        yield(groupIterator.asSequence())
    }
}

fun <T> Iterator<T>.groupIterator(predicate: (T) -> Boolean): Iterator<T> = object : Iterator<T> {
    var curr: T? = null
    val iterator = this@groupIterator

    override fun next(): T {
        return curr ?: throw NoSuchElementException()
    }

    override fun hasNext(): Boolean {
        if (!iterator.hasNext()) {
            return false
        }
        curr = iterator.next()
        return curr?.let { predicate(it) } == true
    }
}

fun IntArray.clear() {
    for (i in indices) {
        this[i] = 0
    }
}


/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
