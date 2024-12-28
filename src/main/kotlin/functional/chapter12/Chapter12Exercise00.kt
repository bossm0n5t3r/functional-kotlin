package functional.chapter12

import kotlin.random.Random

object Chapter12Exercise00 {
    class PetStore(
        private val database: Database,
    ) {
        context(Logger)

        fun addPet(addPetRequest: AddPetRequest): Pet? {
            logInfo("Adding pet with name ${addPetRequest.name}")
            return try {
                database
                    .addPet(addPetRequest)
                    .also { logInfo("Added pet with id ${it.id}") }
            } catch (e: InsertionConflictException) {
                logWarning("There already is pet named ${addPetRequest.name}")
                null
            } catch (e: Exception) {
                logError("Failed to add pet with name ${addPetRequest.name}")
                null
            }
        }
    }

    data class AddPetRequest(
        val name: String,
    )

    data class Pet(
        val id: Int,
        val name: String,
    )

    class InsertionConflictException : Exception()

    interface Database {
        fun addPet(addPetRequest: AddPetRequest): Pet
    }

    interface Logger {
        fun logInfo(message: String)

        fun logWarning(message: String)

        fun logError(message: String)
    }

    fun main(): Unit =
        with(ConsoleLogger()) {
            val database = RandomDatabase()
            val petStore = PetStore(database)
            petStore.addPet(AddPetRequest("Fluffy"))
            // [INFO] - Adding pet with name Fluffy
            // [INFO] - Added pet with id -81731626
            // or
            // [WARNING] - There already is pet named Fluffy
            // or
            // [ERROR] - Failed to add pet with name Fluffy
        }

    class RandomDatabase : Database {
        override fun addPet(addPetRequest: AddPetRequest): Pet =
            when {
                Random.nextBoolean() ->
                    Pet(1234, addPetRequest.name)
                Random.nextBoolean() ->
                    throw InsertionConflictException()
                else -> throw Exception()
            }
    }

    class ConsoleLogger : Logger {
        override fun logInfo(message: String) {
            println("[INFO] - $message")
        }

        override fun logWarning(message: String) {
            println("[WARNING] - $message")
        }

        override fun logError(message: String) {
            println("[ERROR] - $message")
        }
    }

    class FakeLogger : Logger {
        private val messages = mutableListOf<Pair<Level, String>>()

        fun clear() {
            messages.clear()
        }

        fun getMessages(): List<Pair<Level, String>> = messages.toList()

        override fun logInfo(message: String) {
            messages.add(Level.INFO to message)
        }

        override fun logWarning(message: String) {
            messages.add(Level.WARNING to message)
        }

        override fun logError(message: String) {
            messages.add(Level.ERROR to message)
        }

        enum class Level {
            INFO,
            WARNING,
            ERROR,
        }
    }

    class FakeDatabase : Database {
        private val pets = mutableListOf<Pet>()
        private var shouldFail = false

        fun startFailing() {
            shouldFail = true
        }

        fun clear() {
            pets.clear()
            shouldFail = false
        }

        override fun addPet(addPetRequest: AddPetRequest): Pet {
            if (pets.any { it.name == addPetRequest.name }) throw InsertionConflictException()
            if (shouldFail) throw Exception()
            val pet = Pet(pets.size, addPetRequest.name)
            pets.add(pet)
            return pet
        }

        fun getPets(): List<Pet> = pets.toList()
    }
}
