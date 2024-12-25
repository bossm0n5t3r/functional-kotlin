package functional.chapter08

import functional.chapter08.Chapter08Exercise00.StudentJson
import functional.chapter08.Chapter08Exercise00.getPassingSurnames
import kotlin.test.Test
import kotlin.test.assertEquals

class Chapter08Exercise00Test {
    @Test
    fun `should return passing surnames`() {
        val students =
            listOf(
                StudentJson("John", "Smith", 60.0, 20),
                StudentJson("Jane", "Doe", 45.0, 20),
                StudentJson("Ivan", "Ivanov", 60.0, 10),
                StudentJson("John", "Doe", 30.0, 10),
                StudentJson("Jake", "Simonson", 80.0, 20),
            )
        assertEquals(
            listOf("Smith", "Simonson"),
            students.getPassingSurnames(),
        )
    }
}
