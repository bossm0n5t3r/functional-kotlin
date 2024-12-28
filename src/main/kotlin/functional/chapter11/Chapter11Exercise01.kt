package functional.chapter11

object Chapter11Exercise01 {
    fun <T> T?.orThrow(lazyException: () -> Throwable): T = this ?: throw lazyException()
}
