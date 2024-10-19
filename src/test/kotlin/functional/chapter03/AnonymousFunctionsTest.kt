package functional.chapter03

import kotlin.test.Test
import kotlin.test.assertEquals

class AnonymousFunctionsTest {
    @Test
    fun code2Test() {
        val cheer: () -> Unit = fun() {
            println("Hello")
        }
        cheer.invoke() // Hello
        cheer() // Hello

        val printNumber: (Int) -> Unit = fun(i: Int) {
            println(i)
        }
        printNumber.invoke(10) // 10
        printNumber(20) // 20

        val log: (String, String) -> Unit =
            fun(
                ctx: String,
                message: String,
            ) {
                println("[$ctx] $message")
            }
        log.invoke("UserService", "Name changed")
        // [UserService] Name changed
        log("UserService", "Surname changed")
        // [UserService] Surname changed

        val makeAdmin: () -> AnonymousFunctions.Code2.User = fun() = AnonymousFunctions.Code2.User(id = 0)
        assertEquals(AnonymousFunctions.Code2.User(0), makeAdmin())

        val add: (String, String) -> String =
            fun(
                s1: String,
                s2: String,
            ): String = s1 + s2

        assertEquals("AB", add.invoke("A", "B"))
        assertEquals("CD", add("C", "D"))

        val toName: (String) -> AnonymousFunctions.Code2.Name = fun(name: String) = AnonymousFunctions.Code2.Name(name)
        val name: AnonymousFunctions.Code2.Name = toName("Cookie")

        assertEquals(AnonymousFunctions.Code2.Name("Cookie"), name)
    }
}
