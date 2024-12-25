package functional.chapter08

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class CollectionsProcessing03DropTakeTest {
    @Test
    fun code00() {
        CollectionsProcessing03DropTake.Code00.main()
    }

    @Test
    fun code01() {
        CollectionsProcessing03DropTake.Code01.main()
    }

    @Test
    fun code02() {
        CollectionsProcessing03DropTake.Code02.main()
    }

    @Test
    fun code03() {
        assertThrows<IndexOutOfBoundsException> {
            CollectionsProcessing03DropTake.Code03.main()
        }
    }
}
