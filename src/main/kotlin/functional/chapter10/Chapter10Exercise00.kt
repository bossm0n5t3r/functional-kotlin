package functional.chapter10

object Chapter10Exercise00 {
    fun createTable(): TableBuilder =
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

    @DslMarker
    annotation class TableDsl

    @TableDsl
    fun table(init: TableBuilder.() -> Unit): TableBuilder = TableBuilder().apply(init)

    @TableDsl
    data class TableBuilder(
        var trs: List<TrBuilder> = emptyList(),
    ) {
        @TableDsl
        fun tr(init: TrBuilder.() -> Unit) {
            trs += TrBuilder().apply(init)
        }

        override fun toString(): String = "<table>${trs.joinToString(separator = "")}</table>"
    }

    @TableDsl
    data class TrBuilder(
        var tds: List<TdBuilder> = emptyList(),
    ) {
        @TableDsl
        fun td(init: TdBuilder.() -> Unit) {
            tds += TdBuilder().apply(init)
        }

        override fun toString(): String = "<tr>${tds.joinToString(separator = "")}</tr>"
    }

    @TableDsl
    data class TdBuilder(
        var text: String = "",
    ) {
        @TableDsl
        operator fun String.unaryPlus() {
            text = this
        }

        override fun toString(): String = "<td>$text</td>"
    }
}
