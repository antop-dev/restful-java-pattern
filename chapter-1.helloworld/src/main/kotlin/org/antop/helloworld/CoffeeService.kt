package org.antop.helloworld

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class CoffeeService {
    val coffees = hashMapOf<Int, Coffee>()
    val orderCounter = AtomicInteger()

    init {
        reset()
    }

    fun incrementOrderCounter() = orderCounter.incrementAndGet()

    final fun addCoffee(coffee: Coffee): Int {
        val counter = incrementOrderCounter()
        coffee.order = counter
        coffees[counter] = coffee
        return counter
    }

    fun getCoffee(order: Int) = coffees[order]

    final fun reset() {
        orderCounter.set(0)
        addCoffee(Coffee("Brewed", "Small", "Mocha", 3.50))
    }

}