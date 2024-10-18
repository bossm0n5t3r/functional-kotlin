package functional.chapter01

import kotlin.test.Test
import kotlin.test.assertEquals

class CodeTest {
    @Test
    fun example0Test() {
        assertEquals(45, Code.Example0.sum(5, 10))
        assertEquals(151200, Code.Example0.product(5, 10))
    }

    @Test
    fun example1Test() {
        assertEquals(45, Code.Example1.sum(5, 10))
        assertEquals(151200, Code.Example1.product(5, 10))
    }

    @Test
    fun example2Test() {
        assertEquals(45, Code.Example2.sum(5, 10))
        assertEquals(151200, Code.Example2.product(5, 10))
    }

    @Test
    fun example3Test() {
        assertEquals(45, Code.Example3.sum(5, 10))
        assertEquals(151200, Code.Example3.product(5, 10))
    }

    @Test
    fun example4Test() {
        assertEquals(45, Code.Example4.sum(5, 10))
        assertEquals(151200, Code.Example4.product(5, 10))
    }
}
