package functional.chapter08

import functional.chapter08.Chapter08Exercise01.plusAt
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class Chapter08Exercise01Test {
    @Test
    fun test() {
        val list = listOf(1, 2, 3)
        assertEquals(listOf(1, 4, 2, 3), list.plusAt(1, 4))
        assertEquals(listOf(5, 1, 2, 3), list.plusAt(0, 5))
        assertEquals(listOf(1, 2, 3, 6), list.plusAt(3, 6))

        val list2 = listOf("A", "B", "C")
        assertEquals(listOf("A", "D", "B", "C"), list2.plusAt(1, "D"))
    }

    @Test
    fun `Simple addition to the middle adds correctly at the position`() {
        assertEquals(listOf(1, 2, 7, 3), listOf(1, 2, 3).plusAt(2, 7))
        assertEquals(listOf("A", "B", "D", "C"), listOf("A", "B", "C").plusAt(2, "D"))
    }

    @Test
    fun `When we add at size position, element is added at the end`() {
        assertEquals(listOf(1, 2, 3, 7), listOf(1, 2, 3).plusAt(3, 7))
        assertEquals(listOf("A", "B", "C", "D"), listOf("A", "B", "C").plusAt(3, "D"))
    }

    @Test
    fun `When we add at 0, element is added at the beginning`() {
        assertEquals(listOf(7, 1, 2, 3), listOf(1, 2, 3).plusAt(0, 7))
        assertEquals(listOf("D", "A", "B", "C"), listOf("A", "B", "C").plusAt(0, "D"))
    }

    @Test
    fun `When we try to insert at illegal position, IllegalArgumentException error is thrown`() {
        assertThrows<IllegalArgumentException> { listOf(1, 2, 3).plusAt(-1, 7) }
        assertThrows<IllegalArgumentException> { listOf(1, 2, 3).plusAt(8, 7) }
        assertThrows<IllegalArgumentException> { listOf(1, 2, 3).plusAt(10, 7) }
        assertThrows<IllegalArgumentException> { listOf(1, 2, 3).plusAt(100, 7) }
    }
}
