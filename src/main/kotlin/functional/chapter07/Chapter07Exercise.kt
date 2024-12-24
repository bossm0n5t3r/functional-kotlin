package functional.chapter07

object Chapter07Exercise {
    inline fun <reified T> Iterable<*>.anyOf(): Boolean = any { it is T }

    inline fun <reified T> Iterable<*>.firstOfOrNull(): T? = firstOrNull { it is T } as T?

    @Suppress("UNCHECKED_CAST")
    inline fun <reified K, reified V> Map<*, *>.filterValuesInstanceOf(): Map<K, V> =
        filter { (key, value) -> key is K && value is V } as Map<K, V>
}
