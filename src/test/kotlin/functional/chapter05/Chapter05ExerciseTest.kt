package functional.chapter05

import kotlin.reflect.KClass
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class Chapter05ExerciseTest {
    @Test
    fun `FunctionReference has correct property signatures`() {
        checkPropertySignatures(Chapter05Exercise.FunctionReference::class, expectLongestOf = false)
    }

    @Test
    fun `FunctionReference has correct property behavior`() {
        checkPropertyBehavior(Chapter05Exercise.FunctionReference(), expectLongestOf = false)
    }

    @Test
    fun `FunctionMemberReference has correct property signatures`() {
        checkPropertySignatures(Chapter05Exercise.FunctionMemberReference::class)
    }

    @Test
    fun `FunctionMemberReference has correct property behavior`() {
        checkPropertyBehavior(Chapter05Exercise.FunctionMemberReference())
    }

    @Test
    fun `BoundedFunctionReference has correct property signatures`() {
        checkPropertySignatures(Chapter05Exercise.BoundedFunctionReference::class)
    }

    @Test
    fun `BoundedFunctionReference has correct property behavior`() {
        checkPropertyBehavior(Chapter05Exercise.BoundedFunctionReference())
    }

    private fun checkPropertySignatures(
        classToCheck: KClass<*>,
        expectLongestOf: Boolean = true,
    ) {
        val c = classToCheck.members
        val properties =
            mutableMapOf(
                "add" to typeOf<(Int, Int) -> Int>(),
                "printNum" to typeOf<(Int) -> Unit>(),
                "triple" to typeOf<(Int) -> Int>(),
                "produceName" to typeOf<(String) -> Chapter05Exercise.Name>(),
            )
        if (expectLongestOf) {
            properties += "longestOf" to typeOf<(String, String, String) -> String>()
        }
        for ((propertyName, propertyType) in properties) {
            val propertyReference = c.find { it.name == propertyName }
            assertNotNull(propertyReference) { "Property $propertyName is missing" }
            assertEquals(propertyType, propertyReference.returnType, "Property $propertyName has wrong type")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> checkPropertyBehavior(
        instance: T,
        expectLongestOf: Boolean = true,
    ) {
        val members = instance::class.members
        val add = members.find { it.name == "add" }!!
        assertEquals(3, (add.call(instance) as (Int, Int) -> Int)(1, 2))
        assertEquals(12, (add.call(instance) as (Int, Int) -> Int)(4, 8))
        val printNum = members.find { it.name == "printNum" }!!
        (printNum.call(instance) as (Int) -> Unit)(42)
        val triple = members.find { it.name == "triple" }!!
        assertEquals(9, (triple.call(instance) as (Int) -> Int)(3))
        assertEquals(15, (triple.call(instance) as (Int) -> Int)(5))
        val produceName = members.find { it.name == "produceName" }!!
        assertEquals(Chapter05Exercise.Name("John"), (produceName.call(instance) as (String) -> Chapter05Exercise.Name)("John"))
        assertEquals(Chapter05Exercise.Name("Jane"), (produceName.call(instance) as (String) -> Chapter05Exercise.Name)("Jane"))
        if (expectLongestOf) {
            val longestOf = members.find { it.name == "longestOf" }!!
            assertEquals("abc", (longestOf.call(instance) as (String, String, String) -> String)("a", "ab", "abc"))
            assertEquals("xyz", (longestOf.call(instance) as (String, String, String) -> String)("x", "xy", "xyz"))
        }
    }
}
