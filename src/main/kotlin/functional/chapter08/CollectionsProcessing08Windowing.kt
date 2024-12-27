package functional.chapter08

object CollectionsProcessing08Windowing {
    object Code00 {
        fun main() {
            val nums = 1..4
            val chars = 'A'..'F'
            assert(nums.zip(chars) == listOf(1 to 'A', 2 to 'B', 3 to 'C', 4 to 'D'))

            val winner = listOf("Ashley", "Barbara", "Cyprian", "David")
            val prizes = listOf(5000, 3000, 1000)
            val zipped = winner.zip(prizes)
            assert(zipped == listOf("Ashley" to 5000, "Barbara" to 3000, "Cyprian" to 1000))
            assert(
                zipped.map { (person, price) -> "$person won $price" } == listOf("Ashley won 5000", "Barbara won 3000", "Cyprian won 1000"),
            )
        }
    }

    object Code01 {
        fun main() {
            // zip can be used with infix notation
            val zipped = (1..4) zip ('a'..'d')
            assert(zipped == listOf(1 to 'a', 2 to 'b', 3 to 'c', 4 to 'd'))
            val (numbers, letters) = zipped.unzip()
            assert(numbers == listOf(1, 2, 3, 4))
            assert(letters == listOf('a', 'b', 'c', 'd'))
        }
    }

    object Code02 {
        fun main() {
            assert((1..4).zipWithNext() == listOf(1 to 2, 2 to 3, 3 to 4))

            val person = listOf("Ashley", "Barbara", "Cyprian")
            assert(person.zipWithNext() == listOf("Ashley" to "Barbara", "Barbara" to "Cyprian"))
        }
    }

    object Code03 {
        fun main() {
            val person = listOf("A", "B", "C", "D", "E")
            assert(person.zipWithNext { prev, next -> "$prev$next" } == listOf("AB", "BC", "CD", "DE"))
        }
    }

    object Code04 {
        fun main() {
            val person = listOf("Ashley", "Barbara", "Cyprian", "David")
            assert(person.windowed(size = 1, step = 1) == listOf(listOf("Ashley"), listOf("Barbara"), listOf("Cyprian"), listOf("David")))
            // so similar to map { listOf(it) }

            assert(
                person.windowed(size = 2, step = 1) ==
                    listOf(listOf("Ashley", "Barbara"), listOf("Barbara", "Cyprian"), listOf("Cyprian", "David")),
            )
            // so similar to zipWithNext().map { it.toList() }

            assert(person.windowed(size = 1, step = 2) == listOf(listOf("Ashley"), listOf("Cyprian")))

            assert(person.windowed(size = 2, step = 2) == listOf(listOf("Ashley", "Barbara"), listOf("Cyprian", "David")))

            assert(
                person.windowed(
                    size = 3,
                    step = 1,
                ) == listOf(listOf("Ashley", "Barbara", "Cyprian"), listOf("Barbara", "Cyprian", "David")),
            )

            assert(person.windowed(size = 3, step = 2) == listOf(listOf("Ashley", "Barbara", "Cyprian")))

            assert(
                person.windowed(
                    size = 3,
                    step = 1,
                    partialWindows = true,
                ) ==
                    listOf(
                        listOf("Ashley", "Barbara", "Cyprian"),
                        listOf("Barbara", "Cyprian", "David"),
                        listOf("Cyprian", "David"),
                        listOf("David"),
                    ),
            )

            assert(
                person.windowed(
                    size = 3,
                    step = 2,
                    partialWindows = true,
                ) ==
                    listOf(
                        listOf("Ashley", "Barbara", "Cyprian"),
                        listOf("Cyprian", "David"),
                    ),
            )
        }
    }

    object Code05 {
        fun <T> Iterable<T>.chunked(size: Int): List<List<T>> = windowed(size, size, partialWindows = true)
    }

    object Code06 {
        fun main() {
            val person = listOf("Ashley", "Barbara", "Cyprian", "David")
            assert(person.chunked(1) == listOf(listOf("Ashley"), listOf("Barbara"), listOf("Cyprian"), listOf("David")))

            assert(person.chunked(2) == listOf(listOf("Ashley", "Barbara"), listOf("Cyprian", "David")))

            assert(person.chunked(3) == listOf(listOf("Ashley", "Barbara", "Cyprian"), listOf("David")))

            assert(person.chunked(4) == listOf(listOf("Ashley", "Barbara", "Cyprian", "David")))
        }
    }
}
