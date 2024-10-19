package functional.chapter02

object FunctionTypes {
    object Code0 {
        fun fetchText(
            onSuccess: (String) -> Unit,
            onFailure: (Throwable) -> Boolean,
        ) {
            // ...
            onSuccess.invoke("Some text") // returns Unit
            // or
            val handled: Boolean = onFailure.invoke(Error("Some error"))
        }
    }

    object Code1 {
        fun fetchText(
            onSuccess: (String) -> Unit,
            onFailure: (Throwable) -> Boolean,
        ) {
            // ...
            onSuccess("Some text") // returns Unit
            // or
            val handled: Boolean = onFailure(Error("Some error"))
        }
    }

    object Code2 {
        fun someOperations(
            onStart: (() -> Unit)? = null,
            onCompletion: (() -> Unit)? = null,
        ) {
            onStart?.invoke()
            // ...
            onCompletion?.invoke()
        }
    }

    object Code3 {
//        typealias Minutes = Int
//        typealias Seconds = Int
//
//        fun decideAboutTime(): Minutes = 10
//        fun setupTimer(time: Seconds) {
//            /*...*/
//        }
//
//        fun main() {
//            val time = decideAboutTime()
//            setupTimer(time)
//        }
    }

    object Code4 {
        class OnClick : (Int) -> Unit {
            override fun invoke(viewId: Int) {
                // ...
            }
        }

        private fun setListener(l: (Int) -> Unit) {
            // ...
        }

        fun main() {
            val onClick = OnClick()
            setListener(onClick)
        }
    }
}
