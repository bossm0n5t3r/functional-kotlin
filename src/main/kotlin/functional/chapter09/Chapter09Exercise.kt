package functional.chapter09

object Chapter09Exercise {
    private fun m(i: Int): Int {
        print("m$i ")
        return i * i
    }

    private fun f(i: Int): Boolean {
        print("f$i ")
        return i % 2 == 0
    }

    fun main() {
        val list = listOf(1, 2, 3, 4)
        list.map(::m).filter(::f) // `m1 m2 m3 m4 f1 f4 f9 f16 `
        println()
        list.filter(::f).map(::m) // `f1 f2 f3 f4 m2 m4 `
        println()

        val sequence = sequenceOf(1, 2, 3, 4)
        sequence.map(::m).filter(::f).toList() // `m1 f1 m2 f4 m3 f9 m4 f16 `
        println()
        sequence.map(::m).filter(::f) // No output
        println()
        sequence.map(::m).filter(::f).first() // `m1 f1 m2 f4 `
        println()
        sequence.filter(::f).map(::m).toList() // `f1 f2 m2 f3 f4 m4 `
        println()

        val sequence2 = list.asSequence().map(::m)
        sequence2.toList() // `m1 m2 m3 m4 `
        println()
        sequence2.filter(::f).toList() // `m1 f1 m2 f4 m3 f9 m4 f16 `
        println()
    }
}
