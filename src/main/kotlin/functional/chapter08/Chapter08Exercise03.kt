package functional.chapter08

/**
 * effective/collections/PrimeAccess.kt
 */
object Chapter08Exercise03 {
    class PrimeAccessRepository(
        private val primeAccessList: PrimeAccessList,
    ) {
        private val userIdToAccess = primeAccessList.entries.associateBy { it.userId }

        fun isOnAllowList(userId: String): Boolean = userIdToAccess[userId]?.allowList ?: false

        fun isOnDenyList(userId: String): Boolean = userIdToAccess[userId]?.denyList ?: false
    }

    class PrimeAccessList(
        val entries: List<PrimeAccessEntry>,
    )

    class PrimeAccessEntry(
        val userId: String,
        val allowList: Boolean,
        val denyList: Boolean,
    )
}
