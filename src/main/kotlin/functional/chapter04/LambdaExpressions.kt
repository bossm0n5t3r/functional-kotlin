package functional.chapter04

object LambdaExpressions {
    object Code00 {
        fun main() {
            val f: () -> Unit = {}
            f()
            f.invoke()
        }
    }

    object Code01 {
        fun main() {
            {
                println("AAA")
            }
        }
    }

    object Code02 {
        fun produce() = { 42 }
    }

    object Code03 {
        fun produceFun() = { 42 }

        fun produceNum() = 42
    }

    object Code04 {
        val printTimes: (text: String, times: Int) -> Unit = { text: String, times: Int ->
            for (i in 1..times) {
                print(text)
            }
        }
    }

    object Code05 {
        data class User(
            val name: String,
            val surname: String,
        )

        data class Element(
            val id: Int,
            val type: String,
        )

        fun setOnClickListener(listener: (User, Element) -> Unit) {}
    }

    object Code06 {
        inline fun <R> run(block: () -> R): R = block()

        inline fun repeat(
            times: Int,
            block: (Int) -> Unit,
        ) {
            for (i in 0 until times) {
                block(i)
            }
        }
    }

    object Code07 {
        fun sum(
            a: Int,
            b: Int,
        ) = (a..b).fold(0) { acc, i -> acc + i }

        fun product(
            a: Int,
            b: Int,
        ) = (a..b).fold(1) { acc, i -> acc * i }
    }

    object Code08 {
        fun call(
            before: () -> Unit = {},
            after: () -> Unit = {},
        ) {
            before()
            print("A")
            after()
        }
    }

    object Code09 {
        val f = {
            10
            20
            30
        }
    }

    object Code10 {
        fun main() {
            val magicSquare =
                listOf(
                    listOf(2, 7, 6),
                    listOf(9, 5, 1),
                    listOf(4, 3, 8),
                )
            magicSquare.forEach line@{ line ->
                var sum = 0
                line.forEach { elem ->
                    sum += elem
                    if (sum == 15) {
                        return@line
                    }
                }
                print("Line $line not correct")
            }
        }
    }

    object Code11 {
        fun main() {
            val cheer: () -> Unit = {
                println("Hello")
            }
            cheer.invoke() // Hello
            cheer() // Hello

            val printNumber: (Int) -> Unit = { i: Int ->
                println(i)
            }
            printNumber.invoke(10) // 10
            printNumber(20) // 20

            val log: (String, String) -> Unit =
                { ctx: String, message: String ->
                    println("[$ctx] $message")
                }
            log.invoke("UserService", "Name changed")
            // [UserService] Name changed
            log("UserService", "Surname changed")
            // [UserService] Surname changed

            data class User(
                val id: Int,
            )

            val makeAdmin: () -> User = { User(id = 0) }
            println(makeAdmin()) // User(id=0)

            val add: (String, String) -> String =
                { s1: String, s2: String -> s1 + s2 }
            println(add.invoke("A", "B")) // AB
            println(add("C", "D")) // CD

            data class Name(
                val name: String,
            )

            val toName: (String) -> Name =
                { name: String -> Name(name) }
            val name: Name = toName("Cookie")
            println(name) // Name(name=Cookie)
        }
    }

    object Code12 {
        fun makeCounter(): () -> Int {
            var i = 0
            return { i++ }
        }
    }

    object Code13 {
        // lambda expression
        val processorWithLambdaExpression = label@{ data: String ->
            if (data.isEmpty()) {
                return@label null
            }
            data.uppercase()
        }

        // anonymous function
        val processorWithAnonymousFunction = fun(data: String): String? {
            if (data.isEmpty()) {
                return null
            }
            return data.uppercase()
        }
    }
}
