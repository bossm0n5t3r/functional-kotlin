package functional.chapter10

object DSLs {
    object Code00 {
        private fun String.myPlus1(other: String) = this + other

        fun main() {
            assert("A".myPlus1("B") == "AB")

            // Anonymous extension function assigned to a variable
            val myPlus2 = fun String.(other: String) = this + other
            assert(myPlus2.invoke("A", "B") == "AB")
            assert(myPlus2("A", "B") == "AB")
            assert("A".myPlus2("B") == "AB")
        }
    }

    object Code01 {
        fun main() {
            val myPlus2: String.(String) -> String =
                fun String.(other: String) = this + other
            assert(myPlus2.invoke("A", "B") == "AB")
            assert(myPlus2("A", "B") == "AB")
            assert("A".myPlus2("B") == "AB")
        }
    }

    object Code02 {
        fun main() {
            val myPlus3: String.(String) -> String = { other ->
                this + other
                // Inside, we can use receiver `this`,
                // that is of type `String`
            }
            // Here, there is no receiver, so `this` has no meaning
            assert(myPlus3.invoke("A", "B") == "AB")
            assert(myPlus3("A", "B") == "AB")
            assert("A".myPlus3("B") == "AB")
        }
    }

    object Code03 {
        class Dialog {
            var title: String = ""
            var message: String = ""
            var okButtonText: String = ""
            var okButtonHandler: () -> Unit = {}
            var cancelButtonText: String = ""
            var cancelButtonHandler: () -> Unit = {}

            fun show() {
                // ...
            }
        }

        fun main() {
            val dialog = Dialog()
            dialog.title = "Some dialog"
            dialog.message = "Just accept it, ok?"
            dialog.okButtonText = "OK"
            dialog.okButtonHandler = { /*OK*/ }
            dialog.cancelButtonText = "Cancel"
            dialog.cancelButtonHandler = { /*Cancel*/ }
            dialog.show()
        }
    }

    object Code04 {
        inline fun <T> T.apply(block: T.() -> Unit): T {
            this.block() // same as block.invoke(this)
            return this
        }
    }

    object Code05 {
        fun main() {
            val list =
                buildList {
                    add(1)
                    add(2)
                    add(3)
                }
            assert(list == listOf(1, 2, 3))
        }
    }

    object Code06 {
        fun <T> buildList(init: MutableList<T>.() -> Unit): List<T> {
            val list = mutableListOf<T>()
            list.init()
            return list
            // or just
            // return mutableListOf<T>().apply(init)
        }
    }

    object Code07 {
        fun main() {
            val string =
                buildString {
                    append("A")
                    append("B")
                    append("C")
                }
            assert(string == "ABC")
        }
    }

    object Code08 {
        class Dialog {
            var title: String = ""
            var message: String = ""
            var okButton: Button? = null
            var cancelButton: Button? = null

            fun show() {
                // ...
            }

            class Button {
                var message: String = ""
                var handler: () -> Unit = {}
            }
        }
    }

    object Code09 {
        class Dialog {
            var title: String = ""
            var message: String = ""
            private var okButton: Button? = null
            private var cancelButton: Button? = null

            fun okButton(init: Button.() -> Unit) {
                okButton = Button().apply(init)
            }

            fun cancelButton(init: Button.() -> Unit) {
                cancelButton = Button().apply(init)
            }

            fun show() {
                // ...
            }

            class Button {
                var message: String = ""
                var handler: () -> Unit = {}
            }
        }

        fun showDialog(init: Dialog.() -> Unit) {
            Dialog().apply(init).show()
        }

        fun main() {
            showDialog {
                title = "Some dialog"
                message = "Just accept it, ok?"
                okButton {
                    message = "OK"
                    handler = { /*OK*/ }
                }
                cancelButton {
                    message = "Cancel"
                    handler = { /*Cancel*/ }
                }
            }
        }
    }

    object Code10 {
        @DslMarker
        annotation class DialogDsl

        @DialogDsl
        class Dialog {
            var title: String = ""
            var message: String = ""
            private var okButton: Button? = null
            private var cancelButton: Button? = null

            @DialogDsl
            fun okButton(init: Button.() -> Unit) {
                okButton = Button().apply(init)
            }

            @DialogDsl
            fun cancelButton(init: Button.() -> Unit) {
                cancelButton = Button().apply(init)
            }

            fun show() {
                // ...
            }

            @DialogDsl
            class Button {
                var message: String = ""
                var handler: () -> Unit = {}
            }
        }

        @DialogDsl
        fun showDialog(init: Dialog.() -> Unit) {
            Dialog().apply(init).show()
        }
    }

    object Code11 {
        class HeadBuilder {
            var title: String = ""

            fun style(body: String) {
                // ...
            }
        }
    }

    object Code12 {
        class BodyBuilder {
            fun h1(text: String) {
                // ...
            }

            fun h3(text: String) {
                // ...
            }

            operator fun String.unaryPlus() {
                // ...
            }
        }
    }

    object Code13 {
        class HeadBuilder {
            var title: String = ""
            private var styles: List<String> = emptyList()

            fun style(body: String) {
                styles += body
            }
        }
    }

    object Code14 {
        class BodyBuilder {
            private var elements: List<BodyElement> = emptyList()

            fun h1(text: String) {
                this.elements += H1(text)
            }

            fun h3(text: String) {
                this.elements += H3(text)
            }

            operator fun String.unaryPlus() {
                elements += Text(this)
            }
        }

        sealed interface BodyElement

        data class H1(
            val text: String,
        ) : BodyElement

        data class H3(
            val text: String,
        ) : BodyElement

        data class Text(
            val text: String,
        ) : BodyElement
    }

    object Code15 {
        @DslMarker
        annotation class HtmlDsl

        @HtmlDsl
        fun html(init: HtmlBuilder.() -> Unit): HtmlBuilder = HtmlBuilder().apply(init)

        @HtmlDsl
        class HtmlBuilder {
            private var head: HeadBuilder? = null
            private var body: BodyBuilder? = null

            @HtmlDsl
            fun head(init: HeadBuilder.() -> Unit) {
                this.head = HeadBuilder().apply(init)
            }

            @HtmlDsl
            fun body(init: BodyBuilder.() -> Unit) {
                this.body = BodyBuilder().apply(init)
            }

            override fun toString(): String =
                listOfNotNull(head, body)
                    .joinToString(
                        separator = "",
                        prefix = "<html>\n",
                        postfix = "</html>",
                        transform = { "$it\n" },
                    )
        }

        @HtmlDsl
        class HeadBuilder {
            var title: String = ""
            private var cssList: List<String> = emptyList()

            @HtmlDsl
            fun css(body: String) {
                cssList += body
            }

            override fun toString(): String {
                val css =
                    cssList.joinToString(separator = "") {
                        "<style>$it</style>\n"
                    }
                return "<head>\n<title>$title</title>\n$css</head>"
            }
        }

        @HtmlDsl
        class BodyBuilder {
            private var elements: List<BodyElement> = emptyList()

            @HtmlDsl
            fun h1(text: String) {
                this.elements += H1(text)
            }

            @HtmlDsl
            fun h3(text: String) {
                this.elements += H3(text)
            }

            operator fun String.unaryPlus() {
                elements += Text(this)
            }

            override fun toString(): String {
                val body = elements.joinToString(separator = "\n")
                return "<body>\n$body\n</body>"
            }
        }

        sealed interface BodyElement

        data class H1(
            val text: String,
        ) : BodyElement {
            override fun toString(): String = "<h1>$text</h1>"
        }

        data class H3(
            val text: String,
        ) : BodyElement {
            override fun toString(): String = "<h3>$text</h3>"
        }

        data class Text(
            val text: String,
        ) : BodyElement {
            override fun toString(): String = text
        }

        private val html =
            html {
                head {
                    title = "My website"
                    css("Some CSS1")
                    css("Some CSS2")
                }
                body {
                    h1("Title")
                    h3("Subtitle 1")
                    +"Some text 1"
                    h3("Subtitle 2")
                    +"Some text 2"
                }
            }

        fun main() {
            assert(
                html.toString() ==
                    """
                    <html>
                    <head>
                    <title>My website</title>
                    <style>Some CSS1</style>
                    <style>Some CSS2</style>
                    </head>
                    <body>
                    <h1>Title</h1>
                    <h3>Subtitle 1</h3>
                    Some text 1
                    <h3>Subtitle 2</h3>
                    Some text 2
                    </body>
                    </html>
                    """.trimIndent(),
            )
        }
    }
}
