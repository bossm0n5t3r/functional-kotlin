package functional.chapter08

object CollectionsProcessing04Find {
    object Code00 {
        fun main() {
            val c = ('a'..'z').toList()

            assert(c.first() == 'a')
            assert(c.last() == 'z')
            assert(c.get(3) == 'd')
            assert(c[3] == 'd')
            val (c1, c2, c3) = c
            assert(c1 == 'a')
            assert(c2 == 'b')
            assert(c3 == 'c')
        }
    }

    object Code01 {
        fun main() {
            val c = listOf<Char>()

            assert(c.firstOrNull() == null)
            assert(c.lastOrNull() == null)
            assert(c.getOrNull(3) == null)
        }
    }

    object Code02 {
        fun main() {
            val names = listOf("Cookie", "Figa")

            assert(names.find { it.first() == 'A' } == null)
            assert(names.firstOrNull { it.first() == 'A' } == null)
            assert(names.find { it.first() == 'C' } == "Cookie")
            assert(names.firstOrNull { it.first() == 'C' } == "Cookie")
            assert(listOf(1, 2, 6, 11).find { it in 2..10 } == 2)
        }
    }

    object Code03 {
        fun main() {
            val names = listOf("C1", "C2")

            assert(names.find { it.first() == 'C' } == "C1")
            assert(names.firstOrNull { it.first() == 'C' } == "C1")
            assert(names.findLast { it.first() == 'C' } == "C2")
            assert(names.lastOrNull { it.first() == 'C' } == "C2")
        }
    }

    object Code04 {
        fun main() {
            val range = (1..100 step 3)
            assert(range.count() == 34)
        }
    }

    object Code05 {
        fun main() {
            val range = (1..100 step 3)
            assert(range.count { it % 5 == 0 } == 7)
        }
    }

    object Code06 {
        data class Person(
            val name: String,
            val age: Int,
            val male: Boolean,
        )

        fun main() {
            val people =
                listOf(
                    Person("Alice", 31, false),
                    Person("Bob", 29, true),
                    Person("Carol", 31, true),
                )

            fun isAdult(p: Person) = p.age > 18

            fun isChild(p: Person) = p.age < 18

            fun isMale(p: Person) = p.male

            fun isFemale(p: Person) = !p.male

            // Is there an adult?
            assert(people.any(::isAdult)) // true
            // Are they all adults?
            assert(people.all(::isAdult)) // true
            // Is none of them an adult?
            assert(people.none(::isAdult).not()) // false

            // Is there any child?
            assert(people.any(::isChild).not()) // false
            // Are they all children?
            assert(people.all(::isChild).not()) // false
            // Are none of them children?
            assert(people.none(::isChild)) // true

            // Are there any males?
            assert(people.any { isMale(it) }) // true
            // Are they all males?
            assert(people.all { isMale(it) }.not()) // false
            // Is none of them a male?
            assert(people.none { isMale(it) }.not()) // false

            // Are there any females?
            assert(people.any { isFemale(it) }) // true
            // Are they all females?
            assert(people.all { isFemale(it) }.not()) // false
            // Is none of them a female?
            assert(people.none { isFemale(it) }.not()) // false
        }
    }

    object Code07 {
        fun main() {
            val emptyList = emptyList<String>()
            assert(emptyList.any { error("Ignored") }.not()) // false
            assert(emptyList.all { error("Ignored") }) // true
            assert(emptyList.none { error("Ignored") }) // true
        }
    }
}
