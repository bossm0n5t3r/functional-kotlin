package functional.chapter08

object CollectionsProcessing03DropTake {
    object Code00 {
        fun main() {
            val chars = ('a'..'z').toList()

            assert(chars.take(10) == listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'))
            assert(chars.takeLast(10) == listOf('q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'))
            assert(chars.drop(10) == listOf('k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'))
            assert(chars.dropLast(10) == listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'))
        }
    }

    object Code01 {
        fun main() {
            val c = ('a'..'z').toList()

            assert(c.take(10) == c.dropLast(c.size - 10))
            assert(c.takeLast(10) == c.drop(c.size - 10))
            assert(c.drop(10) == c.takeLast(c.size - 10))
            assert(c.dropLast(10) == c.take(c.size - 10))
        }
    }

    object Code02 {
        fun main() {
            val c = ('a'..'z').toList()

            val n = 10
            val s = c.size
            assert(c.take(n) == c.subList(0, n))
            assert(c.takeLast(n) == c.subList(s - n, s))
            assert(c.drop(n) == c.subList(n, s))
            assert(c.dropLast(n) == c.subList(0, s - n))
        }
    }

    object Code03 {
        fun main() {
            val letters = listOf("a", "b", "c")
            assert(letters.take(100) == listOf("a", "b", "c"))
            assert(letters.takeLast(100) == listOf("a", "b", "c"))
            assert(letters.drop(100).isEmpty())
            assert(letters.dropLast(100).isEmpty())
            letters.subList(0, 4)
        }
    }
}
