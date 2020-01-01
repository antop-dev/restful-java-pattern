package org.antop.restful

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CoffeeResourceTest {
    @LocalServerPort
    var port: Int = 0
    @Autowired
    val restTemplate: TestRestTemplate = TestRestTemplate()

    @Test
    fun `HTTP 헤더를 이용한 콘텐츠협상 - XML`() {
        val headers = HttpHeaders().apply {
            accept = mutableListOf(MediaType.APPLICATION_XML)
        }
        val httpEntity = HttpEntity<Unit>(headers)

        val response = restTemplate.exchange("http://localhost:$port/v1/coffees/orders/1", HttpMethod.GET, httpEntity, String::class.java)
        println("response = $response")
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `HTTP 헤더를 이용한 콘텐츠협상 - JSON`() {
        val headers = HttpHeaders().apply {
            accept = mutableListOf(MediaType.APPLICATION_JSON)
        }
        val httpEntity = HttpEntity(null, headers)

        val response = restTemplate.exchange("http://localhost:$port/v1/coffees/orders/2", HttpMethod.GET, httpEntity, String::class.java)
        println("response = $response")
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `HTTP 헤더를 이용한 콘텐츠협상 - 406 에러`() {
        val headers = HttpHeaders().apply {
            accept = mutableListOf(MediaType.TEXT_PLAIN)
        }
        val httpEntity = HttpEntity(null, headers)

        val response = restTemplate.exchange("http://localhost:$port/v1/coffees/orders/2", HttpMethod.GET, httpEntity, String::class.java)
        println("response = $response")
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.statusCode)
    }

    @Test
    fun `URL 패턴을 이용한 콘텐츠 협상 - XML`() {
        val response = restTemplate.getForEntity("/v1/coffees/orders.xml", String::class.java)
        println("response = $response")

        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `URL 패턴을 이용한 콘텐츠 협상 - JSON`() {
        val response = restTemplate.getForEntity("/v1/coffees/orders.json", String::class.java)
        println("response = $response")

        assertEquals(HttpStatus.OK, response.statusCode)
    }

}