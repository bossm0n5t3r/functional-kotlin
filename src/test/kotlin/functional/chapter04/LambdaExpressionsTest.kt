package functional.chapter04

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LambdaExpressionsTest {
    @Test
    fun code00Test() {
        LambdaExpressions.Code00.main()
    }

    @Test
    fun code01Test() {
        LambdaExpressions.Code01.main() // Does not print anything
    }

    @Test
    fun code02Test() {
        assertTrue(LambdaExpressions.Code02.produce() is () -> Int)
    }

    @Test
    fun code03Test() {
        val f = LambdaExpressions.Code03.produceFun()
        assertEquals(42, f())
        assertEquals(42, LambdaExpressions.Code03.produceFun()())
        assertEquals(42, LambdaExpressions.Code03.produceFun().invoke())
        assertEquals(42, LambdaExpressions.Code03.produceNum())
    }

    @Test
    fun code04Test() {
        LambdaExpressions.Code04.printTimes("Na", 7) // NaNaNaNaNaNaNa
        println()
        LambdaExpressions.Code04.printTimes.invoke("Batman", 2) // BatmanBatman
        println()
    }

    @Test
    fun code05Test() {
        LambdaExpressions.Code05.setOnClickListener({ (name, surname), (id, type) ->
            println(
                "User $name $surname clicked " +
                    "element $id of type $type",
            )
        })
    }

    @Test
    fun code06Test() {
//        run({ println("A") }) // A
//        run() { println("A") } // A
        run { println("A") } // A

        repeat(2, { print("B") }) // BB
        println()
        repeat(2) { print("B") } // BB
    }

    @Test
    fun code08Test() {
        // CAAB
        LambdaExpressions.Code08.call({ print("C") })
        LambdaExpressions.Code08.call { print("B") }
    }

    @Test
    fun code09Test() {
        assertEquals(30, LambdaExpressions.Code09.f())
    }

    @Test
    fun code10Test() {
        LambdaExpressions.Code10.main()
    }

    @Test
    fun code11Test() {
        LambdaExpressions.Code11.main()
    }

    @Test
    fun code12Test() {
        val counter1 = LambdaExpressions.Code12.makeCounter()
        val counter2 = LambdaExpressions.Code12.makeCounter()

        assertEquals(0, counter1())
        assertEquals(1, counter1())
        assertEquals(0, counter2())
        assertEquals(2, counter1())
        assertEquals(3, counter1())
        assertEquals(1, counter2())
    }
}
