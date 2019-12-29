package org.antop.helloworld

import org.springframework.stereotype.Component
import java.net.URI
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Component
@Path("/v1/coffees")
class CoffeeResource(val coffeeService: CoffeeService) {

    @GET // 리소스 접근
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffee(@PathParam("id") id: Int): Response = Response.ok(coffeeService.getCoffee(id)).build()

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffeeList(): Response = Response.ok(coffeeService.coffees.values).build()

    @POST // 리소스 생성 (안전x 멱등x)
    // https://stackoverflow.com/questions/33513654/warning-the-subresource-method-contains-empty-path-annotation
    // @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun addCoffee(coffee: Coffee): Response {
        val order = coffeeService.addCoffee(coffee)
        return Response.created(URI.create("/orders/$order")).entity(coffee).build()
    }

    @PUT // 리소스 생성 (안전x 멱등x)
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun editCoffee(@PathParam("id") id: Int, coffee: Coffee): Response {
        val c = coffeeService.getCoffee(id)
        return if (c == null) {
            val order = coffeeService.addCoffee(coffee)
            Response.created(URI.create("/orders/$order")).entity(coffee).build()
        } else {
            Response.ok(c.apply {
                name = coffee.name
                price = coffee.price
                size = coffee.size
                type = coffee.type
            }).build()
        }
    }

}