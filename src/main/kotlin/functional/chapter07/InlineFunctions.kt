package functional.chapter07

object InlineFunctions {
    object Code00 {
        private inline fun repeat(
            times: Int,
            action: (Int) -> Unit,
        ) {
            for (index in 0 until times) {
                action(index)
            }
        }

        fun main() {
            repeat(10) {
                print(it)
            }
        }
    }

    object Code01 {
        fun main() {
            for (index in 0 until 10) {
                print(index)
            }
        }
    }

    object Code02 {
        fun main() {
            repeat(7) {
                print("Na")
            }
            println(" Batman")
        }
    }

    object Code03 {
        fun main() {
            for (i in 0 until 10) {
                if (i == 4) return // Returns from main
                print(i)
            }
        }
    }

    object Code04 {
        fun main() {
            repeat(10) { index ->
                if (index == 4) return // Returns from main
                print(index)
            }
        }
    }

    object Code05 {
        fun main() {
            for (index in 0 until 10) {
                if (index == 4) return // Returns from main
                print(index)
            }
        }
    }

    object Code06 {
        fun main() {
            (0 until 19).forEach { index ->
                if (index == 4) return // Returns from main
                print(index)
            }
        }
    }

    object Code07 {
        inline fun requestNewToken(
            hasToken: Boolean,
            crossinline onRefresh: () -> Unit,
            noinline onGenerate: () -> Unit,
        ) {
            if (hasToken) {
                httpCall("get-token", onGenerate) // We must use
                // noinline to pass function as an argument to a
                // function that is not inlined
            } else {
                httpCall("refresh-token") {
                    onRefresh() // We must use crossinline to
                    // inline function in a context where
                    // non-local return is not allowed
                    onGenerate()
                }
            }
        }

        fun httpCall(
            url: String,
            callback: () -> Unit,
        ) {
            // ...
        }
    }

    object Code08 {
        private inline fun <reified T> printTypeName() {
            print(T::class.simpleName)
        }

        fun main() {
            printTypeName<Int>() // Int
            printTypeName<Char>() // Char
            printTypeName<String>() // String
        }
    }

    object Code09 {
        fun main() {
            print(Int::class.simpleName) // Int
            print(Char::class.simpleName) // Char
            print(String::class.simpleName) // String
        }
    }

    object Code10 {
        class Worker

        class Manager

        private val employees: List<Any> = listOf(Worker(), Manager(), Worker())

        val workers: List<Worker> = employees.filterIsInstance<Worker>()
    }

    object Code11 {
        class User(
            val name: String,
            val surname: String,
        ) {
            inline val fullName: String get() = "$name $surname"
        }

        fun main() {
            val user = User("A", "B")
            println(user.fullName) // A B
            // during compilation changes to
            println("${user.name} ${user.surname}")
        }
    }
}
