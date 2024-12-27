package functional.chapter08

object CollectionsProcessing09Etc {
    object Code00 {
        fun main() {
            val names = listOf("Maja", "Norbert", "Ola")
            assert(names.joinToString() == "Maja, Norbert, Ola")
            assert(names.joinToString { it.uppercase() } == "MAJA, NORBERT, OLA")
            assert(names.joinToString(separator = "; ") == "Maja; Norbert; Ola")
            assert(names.joinToString(limit = 2) == "Maja, Norbert, ...")
            assert(names.joinToString(limit = 2, truncated = "etc.") == "Maja, Norbert, etc.")
            assert(names.joinToString(prefix = "{names=[", postfix = "]}") == "{names=[Maja, Norbert, Ola]}")
        }
    }

    object Code01 {
        data class User(
            val id: Int,
            val name: String,
        )

        fun main() {
            val names: Map<Int, String> = mapOf(0 to "Alex", 1 to "Ben")
            assert(names == mapOf(0 to "Alex", 1 to "Ben"))

            val users: List<User> = names.map { User(it.key, it.value) }
            assert(users == listOf(User(0, "Alex"), User(1, "Ben")))

            val usersById: Map<Int, User> = users.associateBy { it.id }
            assert(usersById == mapOf(0 to User(0, "Alex"), 1 to User(1, "Ben")))

            val namesById: Map<Int, String> = usersById.mapValues { it.value.name }
            assert(namesById == mapOf(0 to "Alex", 1 to "Ben"))

            val usersByName: Map<String, User> = usersById.mapKeys { it.value.name }
            assert(usersByName == mapOf("Alex" to User(0, "Alex"), "Ben" to User(1, "Ben")))
        }
    }
}
