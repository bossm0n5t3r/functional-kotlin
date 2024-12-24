package functional.chapter07

import functional.chapter07.Chapter07Exercise.anyOf
import functional.chapter07.Chapter07Exercise.filterValuesInstanceOf
import functional.chapter07.Chapter07Exercise.firstOfOrNull
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Chapter07ExerciseTest {
    @Test
    fun test() {
        val list = listOf(1, "A", 3, "B")
        assertTrue(list.anyOf<Int>())
        assertTrue(list.anyOf<String>())
        assertFalse(list.anyOf<Double>())

        assertEquals("A", list.firstOfOrNull<String>())
        assertEquals(1, list.firstOfOrNull<Int>())
        assertEquals(null, list.firstOfOrNull<Double>())

        val map = mapOf<Any, Any>(1 to 2, 2 to "A", 3 to 4, "B" to "C")
        assertEquals(mapOf(2 to "A"), map.filterValuesInstanceOf<Int, String>())
        assertEquals(mapOf("B" to "C"), map.filterValuesInstanceOf<String, String>())
        assertEquals(mapOf(1 to 2, 3 to 4), map.filterValuesInstanceOf<Int, Int>())
        assertEquals(mapOf(), map.filterValuesInstanceOf<String, Int>())
    }

    @Test
    fun anyOfTest() {
        assert(listOf(1, 2, 3, "4").anyOf<Int>())
        assert(listOf(1, 2, 3, "4").anyOf<String>())
        assert(!listOf(1, 2, 3, "4").anyOf<Double>())
        assert(listOf(1, 2, 3).anyOf<Int>())
        assert(!listOf(1, 2, 3).anyOf<String>())
        val o =
            object {
                val result = listOf(1, 2, 3, "4").anyOf<Int>()
            }
        assertEquals(typeOf<Boolean>(), o::result.returnType)
    }

    @Test
    fun firstOfOrNullTest() {
        assertEquals(1, listOf(1, 2, 3, "4").firstOfOrNull<Int>())
        assertEquals("4", listOf(1, 2, 3, "4").firstOfOrNull<String>())
        assertEquals(null, listOf(1, 2, 3, "4").firstOfOrNull<Double>())
        assertEquals(1, listOf(1, 2, 3).firstOfOrNull<Int>())
        assertEquals(null, listOf(1, 2, 3).firstOfOrNull<String>())
        val o =
            object {
                val result = listOf(1, 2, 3, "4").firstOfOrNull<Int>()
            }
        assertEquals(typeOf<Int?>(), o::result.returnType)
    }

    @Test
    fun filterValuesInstanceOfTest() {
        assertEquals(
            mapOf(1 to 1, 2 to 2, 3 to 3),
            mapOf(1 to 1, 2 to 2, 3 to 3, 4 to "4").filterValuesInstanceOf<Int, Int>(),
        )
        assertEquals(
            mapOf(4 to "4"),
            mapOf(1 to 1, 2 to 2, 3 to 3, 4 to "4").filterValuesInstanceOf<Int, String>(),
        )
        assertEquals(
            mapOf(),
            mapOf(1 to 1, 2 to 2, 3 to 3, 4 to "4").filterValuesInstanceOf<String, String>(),
        )
        assertEquals(
            mapOf(1 to 1, 2 to 2, 3 to 3),
            mapOf(1 to 1, 2 to 2, 3 to 3).filterValuesInstanceOf<Int, Int>(),
        )
        assertEquals(
            mapOf(),
            mapOf(1 to 1, 2 to 2, 3 to 3).filterValuesInstanceOf<Int, String>(),
        )
        val o =
            object {
                val result = mapOf(1 to 1, 2 to 2, 3 to 3, 4 to "4").filterValuesInstanceOf<Int, Int>()
            }
        assertEquals(typeOf<Map<Int, Int>>(), o::result.returnType)
    }
}
