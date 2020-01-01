package org.antop.restful

import org.springframework.stereotype.Component
import java.net.URI
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

@Component
@Path("/")
class CoffeeResource(val coffeeService: CoffeeService) {

    /**
     * 302 Found<br>
     * 이 응답 코드는 요청한 리소스의 URI가 일시적으로 변경되었음을 의미합니다.<br>
     * 새롭게 변경된 URI는 나중에 만들어질 수 있습니다. 그러므로, 클라이언트는 향후의 요청도 반드시 동일한 URI로 해야합니다
     */
    @GET
    @Path("/v1/coffees/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffeeV1(@PathParam("id") id: Int): Response =
            Response.status(Status.FOUND).location(URI.create("/v2/coffees/orders/$id")).build()

    /**
     * 최신 버전의 API
     */
    @GET
    @Path("/v2/coffees/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffeeV2(@PathParam("id") id: Int): Response = Response.ok(coffeeService.getCoffee(id)).build()

    /**
     * 기본 API는 최신 버전의 API 경로를 제공해야 한다.
     * TODO: 3xx를 사용하는게 나을까?
     */
    @GET
    @Path("/coffees/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffee(@PathParam("id") id: Int): Response = getCoffeeV2(id)
//        Response.status(Status.MOVED_PERMANENTLY).location(URI.create("/v2/coffees/orders/$id")).build()

    /**
     * 요청 쿼리 파라미터에 버전 지정<br>
     * 1. 응답이 캐시되지 않는다.<br>
     * 2. 소스 코드에서 버전에 따라 흐름을 분기시켜야 한다.
     */
    @GET
    @Path("/coffees/orders")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCoffeeList(
            @DefaultValue("v2")
            @QueryParam("version") version: String): Response {
        val values = coffeeService.coffees.values
        return Response.ok(if (version == "v2") values.sortedBy { it.order } else values.sortedByDescending { it.order }).build()
    }

    @GET
    @Path("/coffees/orders/one")
    @Consumes("application/vnd.antop.v1+json")
    @Produces("application/vnd.antop.v1+json")
    fun getOneV1(@HeaderParam("Accept") accept: String): Response =
            Response.ok(coffeeService.coffees.values.first()).build()

    @GET
    @Path("/coffees/orders/one")
    @Consumes("application/vnd.antop.v2+json")
    @Produces("application/vnd.antop.v2+json")
    fun getOneV2(): Response = Response.ok(coffeeService.coffees.values.last()).build()

}