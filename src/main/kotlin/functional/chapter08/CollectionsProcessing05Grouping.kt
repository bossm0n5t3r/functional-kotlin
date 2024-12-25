package functional.chapter08

object CollectionsProcessing05Grouping {
    object Code00 {
        inline fun <T> Iterable<T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
            val first = ArrayList<T>()
            val second = ArrayList<T>()
            for (element in this) {
                if (predicate(element)) {
                    first.add(element)
                } else {
                    second.add(element)
                }
            }
            return Pair(first, second)
        }
    }

    object Code01 {
        fun main() {
            val nums = listOf(1, 2, 6, 11)
            val partitioned: Pair<List<Int>, List<Int>> =
                nums.partition { it in 2..10 }
            assert(partitioned == listOf(2, 6) to listOf(1, 11))

            val (inRange, notInRange) = partitioned
            assert(inRange == listOf(2, 6))
            assert(notInRange == listOf(1, 11))
        }
    }

    object Code02 {
        fun main() {
            val nums = (1..10).toList()

            val (smaller, bigger) = nums.partition { it <= 5 }
            assert(smaller == listOf(1, 2, 3, 4, 5))
            assert(bigger == listOf(6, 7, 8, 9, 10))

            val (even, odd) = nums.partition { it % 2 == 0 }
            assert(even == listOf(2, 4, 6, 8, 10))
            assert(odd == listOf(1, 3, 5, 7, 9))

            data class Student(
                val name: String,
                val passing: Boolean,
            )

            val students =
                listOf(
                    Student("Alex", true),
                    Student("Ben", false),
                )
            val (passing, failed) = students.partition { it.passing }
            assert(passing == listOf(Student("Alex", true)))
            assert(failed == listOf(Student("Ben", false)))
        }
    }

    object Code03 {
        inline fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
            val destination = LinkedHashMap<K, MutableList<T>>()
            for (element in this) {
                val key = keySelector(element)
                val list =
                    destination.getOrPut(key) {
                        ArrayList<T>()
                    }
                list.add(element)
            }
            return destination
        }
    }

    object Code04 {
        fun main() {
            val names = listOf("Marcin", "Maja", "Cookie")

            val byCapital = names.groupBy { it.first() }
            assert(byCapital == mapOf('M' to listOf("Marcin", "Maja"), 'C' to listOf("Cookie")))

            val byLength = names.groupBy { it.length }
            assert(byLength == mapOf(6 to listOf("Marcin", "Cookie"), 4 to listOf("Maja")))
        }
    }

    object Code05 {
        data class Player(
            val name: String,
            val team: String,
        )

        fun main() {
            val players =
                listOf(
                    Player("Alex", "A"),
                    Player("Ben", "B"),
                    Player("Cal", "A"),
                )
            val grouped = players.groupBy { it.team }
            assert(
                grouped ==
                    mapOf(
                        "A" to listOf(Player("Alex", "A"), Player("Cal", "A")),
                        "B" to listOf(Player("Ben", "B")),
                    ),
            )
            assert(grouped.flatMap { it.value }.sortedBy { it.name } == players.sortedBy { it.name })
        }
    }
}
