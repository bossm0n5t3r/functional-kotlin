package functional.chapter08

import java.math.BigDecimal

@Suppress("UNUSED")
object CollectionsProcessing01Fold {
    object Code00 {
        inline fun <T, R> Iterable<T>.fold(
            initial: R,
            operation: (acc: R, T) -> R,
        ): R {
            var accumulator = initial
            for (element in this) {
                accumulator = operation(accumulator, element)
            }
            return accumulator
        }
    }

    object Code01 {
        fun main() {
            val numbers = listOf(1, 2, 3, 4)

            val sum = numbers.fold(0) { acc, i -> acc + i }
            assert(sum == 10)

            val joinedString = numbers.fold("") { acc, i -> acc + i }
            assert(joinedString == "1234")

            val product = numbers.fold(1) { acc, i -> acc * i }
            assert(product == 24)
        }
    }

    object Code02 {
        inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> =
            fold(emptyList()) { acc, e ->
                if (predicate(e)) acc + e else acc
            }

        inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> = fold(emptyList()) { acc, e -> acc + transform(e) }

        inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R> = fold(emptyList()) { acc, e -> acc + transform(e) }
    }

    object Code03 {
        fun main() {
            val numbers = listOf(1, 2, 3, 4, 5)
            assert(numbers.sum() == 15)
            assert(numbers.joinToString(separator = "") == "12345")
        }
    }

    object Code04 {
        fun Iterable<Int>.product(): Int = fold(1) { acc, i -> acc * i }
    }

    object Code05 {
        fun main() {
            val numbers = listOf(1, 2, 3, 4)

            assert(numbers.fold(0) { acc, i -> acc + i } == 10)
            assert(numbers.scan(0) { acc, i -> acc + i } == listOf(0, 1, 3, 6, 10))
            assert(numbers.runningFold(0) { acc, i -> acc + i } == listOf(0, 1, 3, 6, 10))

            assert(numbers.fold("") { acc, i -> acc + i } == "1234")
            assert(numbers.scan("") { acc, i -> acc + i } == listOf("", "1", "12", "123", "1234"))
            assert(numbers.runningFold("") { acc, i -> acc + i } == listOf("", "1", "12", "123", "1234"))

            assert(numbers.fold(1) { acc, i -> acc * i } == 24)
            assert(numbers.scan(1) { acc, i -> acc * i } == listOf(1, 1, 2, 6, 24))
            assert(numbers.runningFold(1) { acc, i -> acc * i } == listOf(1, 1, 2, 6, 24))
        }
    }

    object Code06 {
        inline fun <S, T : S> Iterable<T>.reduce(operation: (acc: S, T) -> S): S {
            val iterator = this.iterator()
            if (!iterator.hasNext()) {
                throw UnsupportedOperationException(
                    "Empty collection can't be reduced.",
                )
            }
            var accumulator: S = iterator.next()
            while (iterator.hasNext()) {
                accumulator = operation(accumulator, iterator.next())
            }
            return accumulator
        }
    }

    object Code07 {
        fun main() {
            val numbers = listOf(1, 2, 3, 4, 5)
            assert(numbers.fold(0) { acc, i -> acc + i } == 15)
            assert(numbers.reduce { acc, i -> acc + i } == 15)

            assert(numbers.fold("") { acc, i -> acc + i } == "12345")
            // Here `reduce` cannot be used instead of `fold`

            assert(numbers.fold(1) { acc, i -> acc * i } == 120)
            assert(numbers.reduce { acc, i -> acc * i } == 120)
        }
    }

    object Code08 {
        fun Iterable<Int>.sum(): Int {
            var sum = 0
            for (element in this) {
                sum += element
            }
            return sum
        }

        inline fun <T> Iterable<T>.sumOf(selector: (T) -> Int): Int {
            var sum = 0
            for (element in this) {
                sum += selector(element)
            }
            return sum
        }
    }

    object Code09 {
        fun main() {
            val numbers = listOf(1, 6, 2, 4, 7, 1)
            assert(numbers.sum() == 21)

            val doubles = listOf(0.1, 0.6, 0.2, 0.4, 0.7)
            assert(doubles.sum() == 1.9999999999999998)
            // It is not 2, due to limited JVM double representation

            val bytes = listOf<Byte>(1, 4, 2, 4, 5)
            assert(bytes.sum() == 16)
        }
    }

    object Code10 {
        data class Player(
            val name: String,
            val points: Int,
            val money: BigDecimal,
        )

        fun main() {
            val players =
                listOf(
                    Player("Jake", 234, BigDecimal("2.30")),
                    Player("Megan", 567, BigDecimal("1.50")),
                    Player("Beth", 123, BigDecimal("0.00")),
                )

            assert(players.map { it.points }.sum() == 924)
            assert(players.sumOf { it.points } == 924)

            // Works for `BigDecimal` as well
            assert(players.sumOf { it.money } == BigDecimal("3.80"))
        }
    }
}
