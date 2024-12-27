package functional.chapter10

object Chapter10Exercise01 {
    data class User(
        val id: String,
        val name: String,
        val points: Int,
        val category: String,
    )

    fun userTable(users: List<User>): TableBuilder =
        table {
            tr {
                td { +"Id" }
                td { +"Name" }
                td { +"Points" }
                td { +"Category" }
            }
            for (user in users) {
                userRow(user)
            }
        }

    private fun TableBuilder.userRow(user: User) {
        tr {
            td { +user.id }
            td { +user.name }
            td { +user.points.toString() }
            td { +user.category }
        }
    }

    private fun table(init: TableBuilder.() -> Unit): TableBuilder = TableBuilder().apply(init)

    data class TableBuilder(
        var trs: List<TrBuilder> = emptyList(),
    ) {
        fun tr(init: TrBuilder.() -> Unit) {
            trs += TrBuilder().apply(init)
        }

        override fun toString(): String = "<table>${trs.joinToString(separator = "")}</table>"
    }

    data class TrBuilder(
        var tds: List<TdBuilder> = emptyList(),
    ) {
        fun td(init: TdBuilder.() -> Unit) {
            tds += TdBuilder().apply(init)
        }

        override fun toString(): String = "<tr>${tds.joinToString(separator = "")}</tr>"
    }

    data class TdBuilder(
        var text: String = "",
    ) {
        operator fun String.unaryPlus() {
            text += this
        }

        override fun toString(): String = "<td>$text</td>"
    }
}
