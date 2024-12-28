package functional.chapter12

import functional.chapter12.Chapter12Exercise00.AddPetRequest
import functional.chapter12.Chapter12Exercise00.FakeDatabase
import functional.chapter12.Chapter12Exercise00.FakeLogger
import functional.chapter12.Chapter12Exercise00.Pet
import functional.chapter12.Chapter12Exercise00.PetStore
import org.junit.jupiter.api.AfterEach
import kotlin.test.Test
import kotlin.test.assertEquals

class Chapter12Exercise00Test {
    private val database = FakeDatabase()
    private val petStore = PetStore(database)
    private val logger = FakeLogger()

    @AfterEach
    fun tearDown() {
        database.clear()
        logger.clear()
    }

    @Test
    fun `should add pet`() {
        val pet =
            with(logger) {
                petStore.addPet(AddPetRequest("Fluffy"))
            }
        val expected = Pet(0, "Fluffy")
        assertEquals(expected, pet)
        assertEquals(expected, database.getPets().single())
    }

    @Test
    fun `should return null when database failing`() {
        database.startFailing()
        val pet =
            with(logger) {
                petStore.addPet(AddPetRequest("Fluffy"))
            }
        assertEquals(null, pet)
        assertEquals(emptyList<Pet>(), database.getPets())
    }

    @Test
    fun `should return null when conflict`() {
        database.addPet(AddPetRequest("Fluffy"))
        val pet =
            with(logger) {
                petStore.addPet(AddPetRequest("Fluffy"))
            }
        assertEquals(null, pet)
    }

    @Test
    fun `should log info when added pet`() {
        with(logger) {
            petStore.addPet(AddPetRequest("Fluffy"))
        }
        assertEquals(
            listOf(
                FakeLogger.Level.INFO to "Adding pet with name Fluffy",
                FakeLogger.Level.INFO to "Added pet with id 0",
            ),
            logger.getMessages(),
        )
    }

    @Test
    fun `should log warning when adding conflict`() {
        database.addPet(AddPetRequest("Fluffy"))
        with(logger) {
            petStore.addPet(AddPetRequest("Fluffy"))
        }
        assertEquals(
            listOf(
                FakeLogger.Level.INFO to "Adding pet with name Fluffy",
                FakeLogger.Level.WARNING to "There already is pet named Fluffy",
            ),
            logger.getMessages(),
        )
    }

    @Test
    fun `should log error when database error`() {
        database.startFailing()
        with(logger) {
            petStore.addPet(AddPetRequest("Fluffy"))
        }
        assertEquals(
            listOf(
                FakeLogger.Level.INFO to "Adding pet with name Fluffy",
                FakeLogger.Level.ERROR to "Failed to add pet with name Fluffy",
            ),
            logger.getMessages(),
        )
    }
}
