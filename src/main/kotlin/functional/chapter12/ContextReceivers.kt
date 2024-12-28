package functional.chapter12

object ContextReceivers {
    object Code00 {
        private fun String.capitalize() =
            this
                .replaceFirstChar(Char::uppercase)

        private fun Iterable<Int>.product() =
            this
                .fold(1, Int::times)

        fun main() {
            assert("alex".capitalize() == "Alex")
            assert("this is text".capitalize() == "This is text")
            assert((1..5).product() == 120)
            assert(listOf(1, 3, 5).product() == 15)
        }
    }
}
