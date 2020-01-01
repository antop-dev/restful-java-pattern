package org.antop.restful

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class CoffeeService {
    val coffees = hashMapOf<Int, Coffee>()
    val orderCounter = AtomicInteger()

    init {
        addCoffee(Coffee("Brewed", "Small", "Mocha", 3.5))
        addCoffee(Coffee("Brewed", "Small", "Americano", 3.0))
        addCoffee(Coffee("Brewed", "Small", "Cold brew", 4.0))
        addCoffee(Coffee("Brewed", "Small", "Espresso", 2.5))
    }

    fun incrementOrderCounter() = orderCounter.incrementAndGet()

    final fun addCoffee(coffee: Coffee): Int {
        val counter = incrementOrderCounter()
        coffee.order = counter
        coffees[counter] = coffee
        return counter
    }

    fun getCoffee(order: Int) = coffees[order]

}