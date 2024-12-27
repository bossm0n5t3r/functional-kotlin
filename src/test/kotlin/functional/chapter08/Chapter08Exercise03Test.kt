package functional.chapter08

import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.test.Test

class Chapter08Exercise03Test {
    @Test
    fun test() {
        val entries =
            List(200_000) {
                Chapter08Exercise03.PrimeAccessEntry(
                    userId = it.toString(),
                    allowList = Random.nextBoolean(),
                    denyList = Random.nextBoolean(),
                )
            }.shuffled()
        val accessList = Chapter08Exercise03.PrimeAccessList(entries)

        val repo: Chapter08Exercise03.PrimeAccessRepository
        measureTimeMillis {
            repo = Chapter08Exercise03.PrimeAccessRepository(accessList)
        }.also { println("Class creation took $it ms") }

        measureTimeMillis {
            for (userId in 1L..10_000L) {
                repo.isOnAllowList(userId.toString())
            }
        }.also { println("Operation took $it ms") }

        measureTimeMillis {
            for (userId in 1L..10_000L) {
                repo.isOnDenyList(userId.toString())
            }
        }.also { println("Operation took $it ms") }
    }
}
