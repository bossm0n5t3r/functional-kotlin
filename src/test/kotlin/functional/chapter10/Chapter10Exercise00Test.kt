package functional.chapter10

import functional.chapter10.Chapter10Exercise00.TableBuilder
import functional.chapter10.Chapter10Exercise00.TdBuilder
import functional.chapter10.Chapter10Exercise00.TrBuilder
import functional.chapter10.Chapter10Exercise00.createTable
import functional.chapter10.Chapter10Exercise00.table
import kotlin.test.Test
import kotlin.test.assertEquals

class Chapter10Exercise00Test {
    @Test
    fun createTableTest() {
        val html =
            TableBuilder().apply {
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder().apply { text = "A" }
                        tds += TdBuilder().apply { text = "B" }
                    }
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder().apply { text = "C" }
                        tds += TdBuilder().apply { text = "D" }
                    }
            }
        assertEquals(html, createTable())
    }

    @Test
    fun `Table can be created and it is empty by default`() {
        val expected = TableBuilder()
        val actual = table {}
        assertEquals(expected, actual)
    }

    @Test
    fun `Tr can be created and it is empty`() {
        val expected =
            TableBuilder().apply {
                trs += TrBuilder()
            }
        val actual =
            table {
                tr {}
            }
        assertEquals(expected, actual)
    }

    @Test
    fun `Multiple tr can be created`() {
        val expected =
            TableBuilder().apply {
                trs += TrBuilder()
                trs += TrBuilder()
            }
        val actual =
            table {
                tr {}
                tr {}
            }
        assertEquals(expected, actual)
    }

    @Test
    fun `Td can be created and it is empty`() {
        val expected =
            TableBuilder().apply {
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder()
                    }
            }
        val actual =
            table {
                tr {
                    td {}
                }
            }
        assertEquals(expected, actual)
    }

    @Test
    fun dslTestStandard() {
        val expected =
            TableBuilder().apply {
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder().apply { text = "A" }
                        tds += TdBuilder().apply { text = "B" }
                    }
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder().apply { text = "C" }
                        tds += TdBuilder().apply { text = "D" }
                    }
            }
        val actual =
            table {
                tr {
                    td { +"A" }
                    td { +"B" }
                }
                tr {
                    td { +"C" }
                    td { +"D" }
                }
            }
        assertEquals(expected, actual)
    }

    @Test
    fun dslTestMoreColons() {
        val expected =
            TableBuilder().apply {
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder().apply { text = "A" }
                        tds += TdBuilder().apply { text = "B" }
                        tds += TdBuilder().apply { text = "C" }
                    }
                trs +=
                    TrBuilder().apply {
                        tds += TdBuilder().apply { text = "C" }
                        tds += TdBuilder().apply { text = "D" }
                    }
            }
        val actual =
            table {
                tr {
                    td { +"A" }
                    td { +"B" }
                    td { +"C" }
                }
                tr {
                    td { +"C" }
                    td { +"D" }
                }
            }
        assertEquals(expected, actual)
    }
}
