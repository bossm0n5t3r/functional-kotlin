package functional.chapter09

import java.math.BigInteger

object Sequences {
    private fun <T> assert(
        expect: String,
        block: (sb: StringBuilder) -> T,
    ): T {
        val sb = StringBuilder()
        return block(sb).also { assert(sb.toString() == expect) { sb.toString() } }
    }

    object Code00 {
        interface Iterable<out T> {
            operator fun iterator(): Iterator<T>
        }

        interface Sequence<out T> {
            operator fun iterator(): Iterator<T>
        }
    }

    object Code01 {
        fun main() {
            assert("f1 f2 f3 ") { sb ->
                val seq = sequenceOf(1, 2, 3)
                val filtered =
                    seq.filter {
                        sb.append("f$it ")
                        it % 2 == 1
                    }

                assert(filtered.toString().contains("FilteringSequence@")) { filtered.toString() }

                filtered.toList() // terminal operation
            }

            val listFiltered =
                assert("f1 f2 f3 ") { sb ->
                    listOf(1, 2, 3).filter {
                        sb.append("f$it ")
                        it % 2 == 1
                    }
                }
            assert(listFiltered == listOf(1, 3))
        }
    }

    object Code02 {
        fun main() {
            assert("F1, F2, F3, M1, M3, E2, E6, ") { sb ->
                listOf(1, 2, 3)
                    .filter {
                        sb.append("F$it, ")
                        it % 2 == 1
                    }.map {
                        sb.append("M$it, ")
                        it * 2
                    }.forEach { sb.append("E$it, ") }
            }
        }
    }

    object Code03 {
        fun main() {
            assert("F1, M1, E2, F2, F3, M3, E6, ") { sb ->
                sequenceOf(1, 2, 3)
                    .filter {
                        sb.append("F$it, ")
                        it % 2 == 1
                    }.map {
                        sb.append("M$it, ")
                        it * 2
                    }.forEach { sb.append("E$it, ") }
            }
        }
    }

    object Code04 {
        fun main() {
            assert("F1, M1, E2, F2, F3, M3, E6, ") { sb ->
                for (e in listOf(1, 2, 3)) {
                    sb.append("F$e, ")
                    if (e % 2 == 1) {
                        sb.append("M$e, ")
                        val mapped = e * 2
                        sb.append("E$mapped, ")
                    }
                }
            }
        }
    }

    object Code05 {
        fun main() {
            assert("M1 M2 M3 M4 M5 M6 M7 M8 M9 M10 F1 F4 ") { sb ->
                (1..10)
                    .asIterable()
                    .map {
                        sb.append("M$it ")
                        it * it
                    }.find {
                        sb.append("F$it ")
                        it > 3
                    }
            }

            assert("M1 F1 M2 F4 ") { sb ->
                (1..10)
                    .asSequence()
                    .map {
                        sb.append("M$it ")
                        it * it
                    }.find {
                        sb.append("F$it ")
                        it > 3
                    }
            }
        }
    }

    object Code06 {
        fun main() {
            assert("F1, M1, F2, F3, M3, ") { sb ->
                (1..10)
                    .asSequence()
                    .filter {
                        sb.append("F$it, ")
                        it % 2 == 1
                    }.map {
                        sb.append("M$it, ")
                        it * 2
                    }.find { it > 5 }
            }

            assert("F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, M1, M3, M5, M7, M9, ") { sb ->
                (1..10)
                    .filter {
                        sb.append("F$it, ")
                        it % 2 == 1
                    }.map {
                        sb.append("M$it, ")
                        it * 2
                    }.find { it > 5 }
            }
        }
    }

    object Code07 {
        fun main() {
            val s =
                (1..6)
                    .asSequence()
                    .filter {
                        print("F$it, ")
                        it % 2 == 1
                    }.map {
                        print("M$it, ")
                        it * 2
                    }

            s.find { it > 3 } // F1, M1, F2, F3, M3,
            println()
            s.find { it > 3 } // F1, M1, F2, F3, M3,
            println()
            s.find { it > 3 } // F1, M1, F2, F3, M3,
            println()

            val l =
                (1..6)
                    .filter {
                        print("F$it, ")
                        it % 2 == 1
                    }.map {
                        print("M$it, ")
                        it * 2
                    }
            // F1, F2, F3, F4, F5, F6, M1, M3, M5,

            l.find { it > 3 } // prints nothing
            l.find { it > 3 } // prints nothing
            l.find { it > 3 } // prints nothing
        }
    }

    object Code08 {
        fun main() {
            assert("2, 4, 6, 8, 10, 12, 14, 16, 18, 20, ") { sb ->
                generateSequence(1) { it + 1 }
                    .map { it * 2 }
                    .take(10)
                    .forEach { sb.append("$it, ") }
            }
        }
    }

    object Code09 {
        private val fibonacci: Sequence<BigInteger> =
            sequence {
                var current = 1.toBigInteger()
                var prev = 0.toBigInteger()
                yield(prev)
                while (true) {
                    yield(current)
                    val temp = prev
                    prev = current
                    current += temp
                }
            }

        fun main() {
            assert(fibonacci.take(10).toList() == listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34).map { it.toBigInteger() })
        }
    }
}
