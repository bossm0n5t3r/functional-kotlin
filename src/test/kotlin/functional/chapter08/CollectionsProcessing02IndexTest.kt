package functional.chapter08

import kotlin.test.Test
import kotlin.test.assertEquals

class CollectionsProcessing02IndexTest {
    @Test
    fun code00() {
        assertEquals(listOf("[0] A", "[2] C"), CollectionsProcessing02Index.Code00.main())
    }

    @Test
    fun code01() {
        CollectionsProcessing02Index.Code01.main()
    }

    @Test
    fun code02() {
        CollectionsProcessing02Index.Code02.main()
    }
}
