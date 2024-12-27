package functional.chapter08

import kotlin.random.Random

object CollectionsProcessing07Sorting {
    object Code00 {
        fun main() {
            assert(listOf(4, 1, 3, 2).sorted() == listOf(1, 2, 3, 4))

            assert(listOf('b', 'A', 'a', ' ', 'B').sorted() == listOf(' ', 'A', 'B', 'a', 'b'))

            assert(listOf("Bab", "AAZ", "Bza", "A").sorted() == listOf("A", "AAZ", "Bab", "Bza"))

            assert(listOf(true, false, true).sorted() == listOf(false, true, true))
        }
    }

    object Code01 {
        fun main() {
            assert(listOf(4, 1, 3, 2).reversed() == listOf(2, 3, 1, 4))
            assert(listOf('C', 'B', 'F', 'A', 'D', 'E').reversed() == listOf('E', 'D', 'A', 'F', 'B', 'C'))
        }
    }

    object Code02 {
        fun main() {
            assert(listOf(4, 1, 3, 2).sortedDescending() == listOf(4, 3, 2, 1))
            assert(listOf(4, 1, 3, 2).sorted().reversed() == listOf(4, 3, 2, 1))
            assert(listOf('b', 'A', 'a', ' ', 'B').sortedDescending() == listOf('b', 'a', 'B', 'A', ' '))
            assert(listOf("Bab", "AAZ", "Bza", "A").sortedDescending() == listOf("Bza", "Bab", "AAZ", "A"))
            assert(listOf(true, false, true).sortedDescending() == listOf(true, true, false))
        }
    }

    object Code03 {
        fun main() {
            val names = listOf("Alex", "Bob", "Celine")

            // Sort by name length
            assert(names.sortedBy { it.length } == listOf("Bob", "Alex", "Celine"))

            // Sort by last letter
            assert(names.sortedBy { it.last() } == listOf("Bob", "Celine", "Alex"))
        }
    }

    object Code04 {
        fun main() {
            val names = listOf("Ben", "Bob", "Bass", "Alex")
            val sorted = names.sortedBy { it.first() }
            assert(sorted == listOf("Alex", "Ben", "Bob", "Bass"))
        }
    }

    object Code05 {
        fun main() {
            val names = listOf("Alex", "Bob", "Celine")

            // Sort by name length
            assert(names.sortedByDescending { it.length } == listOf("Celine", "Alex", "Bob"))

            // Sort by last letter
            assert(names.sortedByDescending { it.last() } == listOf("Alex", "Celine", "Bob"))
        }
    }

    object Code06 {
        fun interface Comparator<T> {
            fun compare(
                a: T,
                b: T,
            ): Int
        }
    }

    object Code07 {
        data class FullName(
            val name: String,
            val surname: String,
        ) {
            override fun toString(): String = "$name $surname"
        }

        fun main() {
            val names =
                listOf(
                    FullName("B", "B"),
                    FullName("B", "A"),
                    FullName("A", "A"),
                    FullName("A", "B"),
                )

            assert(
                names.sortedBy { it.name } ==
                    listOf(
                        FullName("A", "A"),
                        FullName("A", "B"),
                        FullName("B", "B"),
                        FullName("B", "A"),
                    ),
            ) { names.sortedBy { it.name } }
            assert(
                names.sortedBy { it.surname } ==
                    listOf(
                        FullName("B", "A"),
                        FullName("A", "A"),
                        FullName("B", "B"),
                        FullName("A", "B"),
                    ),
            )
            assert(
                names.sortedWith(compareBy({ it.surname }, { it.name })) ==
                    listOf(
                        FullName("A", "A"),
                        FullName("B", "A"),
                        FullName("A", "B"),
                        FullName("B", "B"),
                    ),
            )
            assert(
                names.sortedWith(compareBy({ it.name }, { it.surname })) ==
                    listOf(
                        FullName("A", "A"),
                        FullName("A", "B"),
                        FullName("B", "A"),
                        FullName("B", "B"),
                    ),
            )
        }
    }

    object Code08 {
        fun main() {
            val list = listOf(4, 2, 3, 1)
            val sortedRes = list.sorted()
            // list.sort() is illegal
            assert(list == listOf(4, 2, 3, 1))
            assert(sortedRes == listOf(1, 2, 3, 4))

            val mutableList = mutableListOf(4, 2, 3, 1)
            val sortRes = mutableList.sort()
            assert(mutableList == listOf(1, 2, 3, 4))
            assert(sortRes == Unit)
        }
    }

    object Code09 {
        fun main() {
            val numbers = listOf(1, 6, 2, 4, 7, 1)
            assert(numbers.maxOrNull() == 7)
            assert(numbers.minOrNull() == 1)
        }
    }

    object Code10 {
        data class Player(
            val name: String,
            val score: Int,
        )

        fun main() {
            val players =
                listOf(
                    Player("Jake", 234),
                    Player("Megan", 567),
                    Player("Beth", 123),
                )

            assert(players.maxByOrNull { it.score } == players[1])
            assert(players.minByOrNull { it.score } == players[2])
        }
    }

    object Code11 {
        data class FullName(
            val name: String,
            val surname: String,
        )

        fun main() {
            val names =
                listOf(
                    FullName("B", "B"),
                    FullName("B", "A"),
                    FullName("A", "A"),
                    FullName("A", "B"),
                )

            assert(names.maxWithOrNull(compareBy({ it.surname }, { it.name })) == FullName("B", "B"))
            assert(names.minWithOrNull(compareBy({ it.surname }, { it.name })) == FullName("A", "A"))
        }
    }

    object Code12 {
        fun main() {
            val players =
                listOf(
                    Code10.Player("Jake", 234),
                    Code10.Player("Megan", 567),
                    Code10.Player("Beth", 123),
                )

            assert(players.map { it.score }.maxOrNull() == 567)
            assert(players.maxByOrNull { it.score }?.score == 567)
            assert(players.maxOfOrNull { it.score } == 567)
            assert(players.maxOf { it.score } == 567)

            assert(players.map { it.score }.minOrNull() == 123)
            assert(players.minByOrNull { it.score }?.score == 123)
            assert(players.minOfOrNull { it.score } == 123)
            assert(players.minOf { it.score } == 123)
        }
    }

    object Code13 {
        fun main() {
            val range = (1..100)
            val list = range.toList()

            // `random` requires a collection
            println(list.random()) // random number from 1 to 100
            println(list.randomOrNull())
            // random number from 1 to 100

            println(list.random(Random(123))) // 7
            println(list.randomOrNull(Random(123))) // 7

            println(range.shuffled())
            // List with numbers in a random order
        }
    }

    object Code14 {
        data class Character(
            val name: String,
            val surname: String,
        )

        fun main() {
            val characters =
                listOf(
                    Character("Tamara", "Kurczak"),
                    Character("Earl", "Gey"),
                    Character("Ryba", "Luna"),
                    Character("Cookie", "DePies"),
                )
            println(characters.random())
            // A random character,
            // like Character(name=Ryba, surname=Luna)
            println(characters.shuffled())
            // List with characters in a random order
        }
    }
}
