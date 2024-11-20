package functional.chapter05

object Chapter05Exercise {
    class FunctionsClassic {
        fun add(
            num1: Int,
            num2: Int,
        ): Int = num1 + num2

        fun printNum(num: Int) {
            print(num)
        }

        fun triple(num: Int): Int = num * 3

        fun produceName(name: String): Name = Name(name)

        fun longestOf(
            str1: String,
            str2: String,
            str3: String,
        ): String = maxOf(str1, str2, str3, compareBy { it.length })
    }

    data class Name(
        val name: String,
    )

    class FunctionReference {
        val add: (Int, Int) -> Int = Int::plus

        // TODO: Implement printNum, triple and produceName properties using function references
        //  to functions from the Kotlin stdlib or from the Name class
        //  See add property for example

        val printNum: (Int) -> Unit = ::print
        val triple: (Int) -> Int = 3::times
        val produceName: (String) -> Name = ::Name
        val longestOf: (String, String, String) -> String = ::maxOf
    }

    class FunctionMemberReference {
        val add: (Int, Int) -> Int = this::add

        // TODO: Implement printNum, triple, produceName and longestOf properties using function references
        //  to functions from the current class
        //  See add property for example

        val printNum: (Int) -> Unit = this::printNum
        val triple: (Int) -> Int = this::triple
        val produceName: (String) -> Name = this::produceName
        val longestOf: (String, String, String) -> String = this::longestOf

        private fun add(
            num1: Int,
            num2: Int,
        ): Int = num1 + num2

        private fun printNum(num: Int) {
            print(num)
        }

        private fun triple(num: Int): Int = num * 3

        private fun produceName(name: String): Name = Name(name)

        private fun longestOf(
            str1: String,
            str2: String,
            str3: String,
        ): String = maxOf(str1, str2, str3, compareBy { it.length })
    }

    class BoundedFunctionReference {
        private val classic = FunctionsClassic()

        val add: (Int, Int) -> Int = classic::add

        // TODO: Implement printNum, triple, produceName and longestOf properties using function references
        //  to functions from the `classic` object
        //  See add property for example

        val printNum: (Int) -> Unit = classic::printNum
        val triple: (Int) -> Int = classic::triple
        val produceName: (String) -> Name = classic::produceName
        val longestOf: (String, String, String) -> String = classic::longestOf
    }
}
