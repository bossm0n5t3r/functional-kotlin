package functional.chapter08

object Chapter08Exercise02 {
    fun Shop.getWaitingCustomers(): List<Customer> =
        customers.filter { customer -> customer.orders.any { order -> order.isDelivered.not() } }

    fun Shop.countProductSales(product: Product): Int =
        customers.sumOf { customer -> customer.orders.sumOf { order -> order.products.count { it == product } } }

    fun Shop.getCustomers(minAmount: Double): List<Customer> =
        customers
            .filter { customer ->
                customer.orders.sumOf { order -> order.products.sumOf { product -> product.price } } >= minAmount
            }

    data class Shop(
        val name: String,
        val customers: List<Customer>,
    )

    data class Customer(
        val name: String,
        val city: City,
        val orders: List<Order>,
    )

    data class Order(
        val products: List<Product>,
        val isDelivered: Boolean,
    )

    data class Product(
        val name: String,
        val price: Double,
    )

    data class City(
        val name: String,
    )
}
