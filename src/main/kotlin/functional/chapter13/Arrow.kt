package functional.chapter13

object Arrow {
    object Code00 {
        fun String.isPrefixOf(s: String) = s.startsWith(this)
    }

    object Code01 {
        val fibonacci =
            DeepRecursiveFunction<Int, Int> { x ->
                when {
                    x < 0 -> 0
                    x == 1 -> 1
                    else -> callRecursive(x - 1) + callRecursive(x - 2)
                }
            }
    }

    object Code02 {
        fun envOrNull(name: String): String? = runCatching { System.getenv(name) }.getOrNull()
    }

    object Code03 {
        fun add(
            a: String,
            b: String,
        ): Int? {
            val x = a.toIntOrNull() ?: return null
            val y = b.toIntOrNull() ?: return null
            return x + y
        }
    }

    object Code04 {
        fun envOrNull(name: String): Result<String?> =
            runCatching {
                System.getenv(name)
            }
    }

    object Code05 {
        sealed class Either<out E, out A> {
            data class Left<E>(
                val value: E,
            ) : Either<E, Nothing>()

            data class Right<A>(
                val value: A,
            ) : Either<Nothing, A>()
        }
    }

    object Code06 {
        sealed interface ConfigError

        data class SystemError(
            val underlying: Throwable,
        )

        data object PortNotAvailable : ConfigError

        data class InvalidPort(
            val port: String,
        ) : ConfigError
    }

    object Code07 {
        data class Address(
            val zipcode: String,
            val country: String,
        )

        data class Person(
            val name: String,
            val age: Int,
            val address: Address,
        )
    }
}
