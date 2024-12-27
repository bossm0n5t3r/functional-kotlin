package functional.chapter11

object ScopeFunctions {
    object Code00 {
        inline fun <T, R> T.let(block: (T) -> R): R = block(this)
    }

    object Code01 {
        fun main() {
            assert(listOf("a", "b", "c").map { it.uppercase() } == listOf("A", "B", "C"))
            assert("a".let { it.uppercase() } == "A")
        }
    }

    object Code02 {
        class UserCreationRequest(
            val id: String,
            val name: String,
            val surname: String,
        )

        class UserDto(
            val userId: String,
            val firstName: String,
            val lastName: String,
        )

        fun UserCreationRequest.toUserDto() =
            UserDto(
                userId = this.id,
                firstName = this.name,
                lastName = this.surname,
            )
    }

    object Code03 {
        class User(
            val name: String,
        )

        private var user: User? = null

        fun showUserNameIfPresent() {
            // will not work, because cannot smart-cast a property
            // if (user != null) {
            //     println(user.name)
            // }

            // works
            // val u = user
            // if (u != null) {
            //     println(u.name)
            // }

            // perfect
            user?.let { println(it.name) }
        }
    }

    object Code04 {
        inline fun <T> T.also(block: (T) -> Unit): T {
            block(this)
            return this
        }
    }

    object Code05 {
        inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? = if (predicate(this)) this else null

        inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T? = if (!predicate(this)) this else null
    }

    object Code06 {
        inline fun <T> T.apply(block: T.() -> Unit): T {
            block()
            return this
        }
    }

    object Code07 {
        class Node(
            val name: String,
        ) {
            fun makeChild(childName: String) =
                create("$name.$childName")
                    .apply { print("Created $name") }

            private fun create(name: String): Node? = Node(name)
        }

        fun main() {
            val node = Node("parent")
            node.makeChild("child") // Created parent
        }
    }

    object Code08 {
        class Node(
            val name: String,
        ) {
            fun makeChild(childName: String) =
                create("$name.$childName")
                    .also { print("Created ${it?.name}") }

            private fun create(name: String): Node? = Node(name)
        }

        fun main() {
            val node = Node("parent")
            node.makeChild("child") // Created parent.child
        }
    }

    object Code09 {
        inline fun <T, R> with(
            receiver: T,
            block: T.() -> R,
        ): R = receiver.block()
    }

    object Code10 {
        inline fun <R> run(block: () -> R): R = block()

        inline fun <T, R> T.run(block: T.() -> R): R = block()
    }
}
