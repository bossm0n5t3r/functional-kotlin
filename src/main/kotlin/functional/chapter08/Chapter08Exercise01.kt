package functional.chapter08

import kotlin.random.Random

object Chapter08Exercise01 {
    fun <T> List<T>.plusAt(
        index: Int,
        element: T,
    ): List<T> {
        require(index in 0..size) { "Index $index is out of bounds for size $size" }
        return when (Random.nextInt(4)) {
            0 -> this.toMutableList().apply { add(index, element) }
            1 -> this.take(index) + element + this.drop(index)
            2 -> this.slice(0 until index) + element + this.slice(index until size)
            3 -> {
                when (index) {
                    0 -> listOf(element) + this
                    size -> this + element
                    else ->
                        flatMapIndexed { i: Int, e: T ->
                            if (i == index) listOf(element, e) else listOf(e)
                        }
                }
            }
            else -> throw IllegalStateException("Random.nextInt(4) returned unexpected value")
        }
    }
}
