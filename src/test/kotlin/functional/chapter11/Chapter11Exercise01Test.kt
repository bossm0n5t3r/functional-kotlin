package functional.chapter11

import functional.chapter11.Chapter11Exercise01.orThrow
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Chapter11Exercise01Test {
    @Test
    fun `should throw for null value`() {
        val value: String? = null
        val exception = RuntimeException("Value is null")
        val result = runCatching { value.orThrow { exception } }
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `should return value for non-null value`() {
        val value: String? = "Hello"
        val result = value.orThrow { RuntimeException("Value is null") }
        assertEquals("Hello", result)
    }

    private val value: String? = "Hello"
    val result = value.orThrow { RuntimeException("Value is null") }

    @Test
    fun `should specify result type as non-nullable`() {
        assertFalse(::result.returnType.isMarkedNullable)
    }
}
