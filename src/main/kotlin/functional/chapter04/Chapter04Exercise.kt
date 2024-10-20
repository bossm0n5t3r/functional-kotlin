package functional.chapter04

object Chapter04Exercise {
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

    class AnonymousFunctionalTypeSpecified {
        val add: (Int, Int) -> Int = fun(
            num1,
            num2,
        ) = num1 + num2

        val printNum: (Int) -> Unit = fun(num) {
            print(num)
        }
        val triple: (Int) -> Int = fun(num) = num * 3
        val produceName: (String) -> Name = fun(name) = Name(name)
        val longestOf: (String, String, String) -> String = fun(
            str1,
            str2,
            str3,
        ) = maxOf(str1, str2, str3, compareBy { it.length })
    }

    class AnonymousFunctionalTypeInferred {
        val add = fun(
            num1: Int,
            num2: Int,
        ) = num1 + num2

        val printNum = fun(num: Int) {
            print(num)
        }
        val triple = fun(num: Int) = num * 3
        val produceName = fun(name: String) = Name(name)
        val longestOf = fun(
            str1: String,
            str2: String,
            str3: String,
        ) = maxOf(str1, str2, str3, compareBy { it.length })
    }

    class LambdaFunctionalTypeSpecified {
        val add: (Int, Int) -> Int = { num1, num2 -> num1 + num2 }

        val printNum: (Int) -> Unit = { num -> print(num) }
        val triple: (Int) -> Int = { num -> num * 3 }
        val produceName: (String) -> Name = { name -> Name(name) }
        val longestOf: (String, String, String) -> String = { str1, str2, str3 -> maxOf(str1, str2, str3, compareBy { it.length }) }
    }

    class LambdaFunctionalTypeInferred {
        val add = { num1: Int, num2: Int -> num1 + num2 }

        val printNum = { num: Int -> print(num) }
        val triple = { num: Int -> num * 3 }
        val produceName = { name: String -> Name(name) }
        val longestOf = { str1: String, str2: String, str3: String -> maxOf(str1, str2, str3, compareBy { it.length }) }
    }

    class LambdaUsingImplicitParameter {
        val add: (Int, Int) -> Int = { num1, num2 -> num1 + num2 }

        val printNum: (Int) -> Unit = { print(it) }
        val triple: (Int) -> Int = { it * 3 }
        val produceName: (String) -> Name = { Name(it) }
        val longestOf: (String, String, String) -> String = { str1, str2, str3 -> maxOf(str1, str2, str3, compareBy { it.length }) }
    }
}
