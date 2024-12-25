package functional.chapter08

object CollectionsProcessing00Basic {
    object Code00 {
        inline fun <T> Iterable<T>.forEach(action: (T) -> Unit) {
            for (element in this) action(element)
        }

        inline fun <T, C : Iterable<T>> C.onEach(action: (T) -> Unit): C {
            for (element in this) action(element)
            return this
        }
    }

    object Code01 {
        inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> {
            val destination = ArrayList<T>()
            for (element in this) {
                if (predicate(element)) {
                    destination.add(element)
                }
            }
            return destination
        }
    }

    object Code02 {
        fun main(): List<Int> {
            val old = listOf(1, 2, 6, 11)
            return old.filter { it in 2..10 }
        }
    }

    object Code03 {
        fun main(): List<Int> {
            val old = listOf(1, 2, 6, 11)
            return old.filterNot { it in 2..10 }
        }
    }

    object Code04 {
        inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
            val size = if (this is Collection<*>) this.size else 10
            val destination = ArrayList<R>(size)
            for (element in this) {
                destination.add(transform(element))
            }
            return destination
        }
    }

    object Code05 {
        fun main(): List<Int> {
            val old = listOf(1, 2, 3, 4)
            return old.map { it * it }
        }
    }

    object Code06 {
        fun main(): List<Int> {
            val names: List<String> = listOf("Alex", "Bob", "Carol")
            return names.map { it.length }
        }
    }

    object Code07 {
        inline fun <T, R> Iterable<T>.mapNotNull(transform: (T) -> R): List<R> {
            val size = if (this is Collection<*>) this.size else 10
            val destination = ArrayList<R>(size)
            for (element in this) {
                val result = transform(element)
                if (result != null) destination.add(result)
            }
            return destination
        }
    }

    object Code08 {
        fun main() {
            val old = listOf("1", "A", "2", "3", "B", "4")
            assert(old.mapNotNull { it.toIntOrNull() } == listOf(1, 2, 3, 4))

            val numbers = listOf(-1, 2, -3, 4)
            assert(numbers.mapNotNull { prod(it) } == listOf(2, 24))
            assert(numbers.mapNotNull { if (it > 0) it else null } == listOf(2, 4))
        }

        private fun prod(num: Int): Int? {
            if (num <= 0) return null
            // Can be simplified with fold, that we will learn later
            var res = 1
            for (i in 1..num) {
                res *= i
            }
            return res
        }
    }

    object Code09 {
        inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
            val size = if (this is Collection<*>) this.size else 10
            val destination = ArrayList<R>(size)
            for (element in this) {
                destination.addAll(transform(element))
            }
            return destination
        }
    }

    object Code10 {
        fun main(): List<Int> {
            val old = listOf(1, 2, 3)
            return old.flatMap { listOf(it, it + 10) }
        }
    }

    object Code11 {
        fun main() {
            val names = listOf("Ann", "Bob", "Cale")
            val chars1: List<Char> = names.flatMap { it.toList() }
            assert(chars1 == listOf('A', 'n', 'n', 'B', 'o', 'b', 'C', 'a', 'l', 'e'))

            val mapRes: List<List<Char>> = names.map { it.toList() }
            assert(mapRes == listOf(listOf('A', 'n', 'n'), listOf('B', 'o', 'b'), listOf('C', 'a', 'l', 'e')))

            val chars2 = mapRes.flatten()
            assert(chars2 == listOf('A', 'n', 'n', 'B', 'o', 'b', 'C', 'a', 'l', 'e'))
            assert(chars1 == chars2)
        }
    }
}
