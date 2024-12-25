package functional.chapter08

import kotlin.test.Test
import kotlin.test.assertEquals

class CollectionsProcessing00BasicTest {
    @Test
    fun code02() {
        assertEquals(listOf(2, 6), CollectionsProcessing00Basic.Code02.main())
    }

    @Test
    fun code03() {
        assertEquals(listOf(1, 11), CollectionsProcessing00Basic.Code03.main())
    }

    @Test
    fun code05() {
        assertEquals(listOf(1, 4, 9, 16), CollectionsProcessing00Basic.Code05.main())
    }

    @Test
    fun code06() {
        assertEquals(listOf(4, 3, 5), CollectionsProcessing00Basic.Code06.main())
    }

    @Test
    fun code08() {
        CollectionsProcessing00Basic.Code08.main()
    }

    @Test
    fun code10() {
        assertEquals(listOf(1, 11, 2, 12, 3, 13), CollectionsProcessing00Basic.Code10.main())
    }

    @Test
    fun code11() {
        CollectionsProcessing00Basic.Code11.main()
    }
}
