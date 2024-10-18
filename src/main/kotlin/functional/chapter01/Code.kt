package functional.chapter01

object Code {
    object Example0 {
        fun sum(
            a: Int,
            b: Int,
        ): Int {
            var sum = 0
            for (i in a..b) {
                sum += i
            }
            return sum
        }

        fun product(
            a: Int,
            b: Int,
        ): Int {
            var product = 1
            for (i in a..b) {
                product *= i
            }
            return product
        }
    }

    object Example1 {
        fun sum(
            a: Int,
            b: Int,
        ) = fold(a, b, 0, { acc, i -> acc + i })

        fun product(
            a: Int,
            b: Int,
        ) = fold(a, b, 1, { acc, i -> acc * i })

        private fun fold(
            a: Int,
            b: Int,
            initial: Int,
            operation: (Int, Int) -> Int,
        ): Int {
            var acc = initial
            for (i in a..b) {
                acc = operation(acc, i)
            }
            return acc
        }
    }

    object Example2 {
        fun sum(
            a: Int,
            b: Int,
        ) = (a..b).fold(0) { acc, i -> acc + i }

        fun product(
            a: Int,
            b: Int,
        ) = (a..b).fold(1) { acc, i -> acc * i }
    }

    object Example3 {
        fun sum(
            a: Int,
            b: Int,
        ) = (a..b).fold(0, Int::plus)

        fun product(
            a: Int,
            b: Int,
        ) = (a..b).fold(1, Int::times)
    }

    object Example4 {
        fun sum(
            a: Int,
            b: Int,
        ) = (a..b).sum()

        fun product(
            a: Int,
            b: Int,
        ) = (a..b).fold(1, Int::times)
    }
}
