package functional.chapter03

object AnonymousFunctions {
    object Code0 {
        fun add1(
            a: Int,
            b: Int,
        ) = a + b

        val add2 = fun(
            a: Int,
            b: Int,
        ): Int = a + b
    }

    object Code1 {
        val add2 = fun(
            a: Int,
            b: Int,
        ) = a + b
    }

    object Code2 {
        data class User(
            val id: Int,
        )

        data class Name(
            val name: String,
        )
    }
}
