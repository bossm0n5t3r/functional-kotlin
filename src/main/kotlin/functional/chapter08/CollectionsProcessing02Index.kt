package functional.chapter08

object CollectionsProcessing02Index {
    object Code00 {
        fun main(): List<String> =
            listOf("A", "B", "C", "D") // List<String>
                .withIndex() // List<IndexedValue<String>>
                .filter { (index, value) -> index % 2 == 0 }
                .map { (index, value) -> "[$index] $value" }
    }

    object Code01 {
        fun main() {
            val chars = listOf("A", "B", "C", "D")

            val filtered =
                chars
                    .filterIndexed { index, _ -> index % 2 == 0 }
            assert(filtered == listOf("A", "C"))

            val mapped =
                chars
                    .mapIndexed { index, value -> "[$index] $value" }
            assert(mapped == listOf("[0] A", "[1] B", "[2] C", "[3] D"))
        }
    }

    object Code02 {
        fun main() {
            val chars = listOf("A", "B", "C", "D")

            val r1 =
                chars
                    .withIndex()
                    .filter { (index, value) -> index % 2 == 0 }
                    .map { (index, value) -> "[$index] $value" }
            assert(r1 == listOf("[0] A", "[2] C"))

            val r2 =
                chars
                    .filterIndexed { index, _ -> index % 2 == 0 }
                    .mapIndexed { index, value -> "[$index] $value" }
            assert(r2 == listOf("[0] A", "[1] C"))
        }
    }
}
