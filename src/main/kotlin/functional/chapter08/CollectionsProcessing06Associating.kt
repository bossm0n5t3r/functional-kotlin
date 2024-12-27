package functional.chapter08

object CollectionsProcessing06Associating {
    object Code00 {
        fun main() {
            val names = listOf("Alex", "Ben", "Cal")
            assert(names.associate { it.first() to it.drop(1) } == mapOf('A' to "lex", 'B' to "en", 'C' to "al"))
            assert(names.associateWith { it.length } == mapOf("Alex" to 4, "Ben" to 3, "Cal" to 3))
            assert(names.associateBy { it.first() } == mapOf('A' to "Alex", 'B' to "Ben", 'C' to "Cal"))
        }
    }

    object Code01 {
        fun main() {
            val names = listOf("Alex", "Aaron", "Ada")
            assert(names.associateBy { it.first() } == mapOf('A' to "Ada"))
            assert(names.groupBy { it.first() } == mapOf('A' to listOf("Alex", "Aaron", "Ada")))
        }
    }

    object Code02 {
        fun main() {
            val names = listOf("Alex", "Ben", "Cal")
            val aW = names.associateWith { it.length }
            assert(aW.keys.toList() == names)
            val aB = names.associateBy { it.first() }
            assert(aB.values.toList() == names)
        }
    }

    object Code03 {
        fun <T> Iterable<T>.distinct(): List<T> = this.toMutableSet().toList()

        inline fun <T, K> Iterable<T>.distinctBy(selector: (T) -> K): List<T> {
            val set = HashSet<K>()
            val list = ArrayList<T>()
            for (e in this) {
                val key = selector(e)
                if (set.add(key)) {
                    list.add(e)
                }
            }
            return list
        }
    }

    object Code04 {
        fun main() {
            val list: List<Int> = listOf(1, 2, 4, 2, 3, 1)
            val set: Set<Int> = list.toSet()
            assert(set == setOf(1, 2, 3, 4))
        }
    }

    object Code05 {
        fun main() {
            val numbers = listOf(1, 2, 4, 2, 3, 1)
            assert(numbers == listOf(1, 2, 4, 2, 3, 1))
            assert(numbers.distinct() == listOf(1, 2, 4, 3))

            val names = listOf("Marta", "Maciek", "Marta", "Daniel")
            assert(names == listOf("Marta", "Maciek", "Marta", "Daniel"))
            assert(names.distinct() == listOf("Marta", "Maciek", "Daniel"))
        }
    }

    object Code06 {
        fun main() {
            val names = listOf("Marta", "Maciek", "Marta", "Daniel")
            assert(names == listOf("Marta", "Maciek", "Marta", "Daniel"))
            assert(names.distinctBy { it[0] } == listOf("Marta", "Daniel"))
            assert(names.distinctBy { it.length } == listOf("Marta", "Maciek"))
        }
    }

    object Code07 {
        fun main() {
            val names = listOf("Marta", "Maciek", "Daniel")
            assert(names == listOf("Marta", "Maciek", "Daniel"))
            assert(names.distinctBy { it.length } == listOf("Marta", "Maciek"))
            assert(names.associateBy { it.length }.values.sortedBy { it.length } == listOf("Marta", "Daniel"))
        }
    }

    object Code08 {
        data class Person(
            val id: Int,
            val name: String,
        ) {
            override fun toString(): String = "$id: $name"
        }

        fun main() {
            val people =
                listOf(
                    Person(0, "Alex"),
                    Person(1, "Ben"),
                    Person(1, "Carl"),
                    Person(2, "Ben"),
                    Person(0, "Alex"),
                )
            assert(
                people.distinct() ==
                    listOf(
                        Person(0, "Alex"),
                        Person(1, "Ben"),
                        Person(1, "Carl"),
                        Person(2, "Ben"),
                    ),
            )
            assert(
                people.distinctBy { it.id } ==
                    listOf(
                        Person(0, "Alex"),
                        Person(1, "Ben"),
                        Person(2, "Ben"),
                    ),
            )
            assert(
                people.distinctBy { it.name } ==
                    listOf(
                        Person(0, "Alex"),
                        Person(1, "Ben"),
                        Person(1, "Carl"),
                    ),
            )
        }
    }
}
