package org.antop.restful

import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Variant

@Component
@Path("/v1/coffees")
class CoffeeResource(val coffeeService: CoffeeService) {

    /**
     * HTTP 헤더를 이용한 콘텐츠 협상
     */
    @GET
    @Path("/orders/{id}")
    fun getCoffeeXml(@HeaderParam("Accept") accept: String, @PathParam("id") id: Int): Response {
        return if (accept != MediaType.APPLICATION_XML && accept != MediaType.APPLICATION_JSON) {
            val variants = Variant.VariantListBuilder.newInstance()
                    .mediaTypes(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).build()
            Response.notAcceptable(variants).build() // 406
        } else {
            Response.ok(coffeeService.getCoffee(id)).type(accept).build()
        }
    }

    /**
     * URL 패턴을 이용한 콘텐츠 협상: XML
     */
    @GET
    @Path("/orders.xml")
    @Produces(MediaType.APPLICATION_XML)
    fun getCoffeeListXml(): Response = Response.ok(Coffees(coffeeService.coffees.values)).build()

    /**
     * URL 패턴을 이용한 콘텐츠 협상: JSON
     */
    @GET
    @Path("/orders.json")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffeeListJson(): Response = Response.ok(coffeeService.coffees.values).build()

}