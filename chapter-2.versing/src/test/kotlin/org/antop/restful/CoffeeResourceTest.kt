package org.antop.restful

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CoffeeResourceTest {
    @LocalServerPort
    var port: Int = 0
    @Autowired
    val restTemplate: TestRestTemplate = TestRestTemplate()

    @Test
    internal fun `기본적으로 최신 버전의 API 경로를 제공`() {
        val response = restTemplate.getForEntity("http://localhost:$port/coffees/orders/1", String::class.java)
        println("response = $response")
    }

    @Test
    internal fun `구버전 API 사용`() {
        val response = restTemplate.getForEntity("http://localhost:$port/v1/coffees/orders/1", String::class.java)
        println("response = $response")
    }

    @Test
    internal fun `최신버전 API 사용`() {
        val response = restTemplate.getForEntity("http://localhost:$port/v2/coffees/orders/1", String::class.java)
        println("response = $response")
    }

    @Test
    internal fun `Accept 헤더에 버전 지정 v1`() {
        val headers = HttpHeaders().apply {
            set(HttpHeaders.ACCEPT, "application/vnd.antop.v1+json")
        }
        val response = restTemplate.exchange(
                "http://localhost:$port/coffees/orders/one",
                HttpMethod.GET,
                HttpEntity<String>(headers),
                Coffee::class.java)
        println("response = $response")

        assertEquals(HttpStatus.OK, response.statusCode)
        response.body?.run {
            assertEquals(1, order)
            assertEquals("Mocha", name)
            assertEquals(3.5, price)
        }
    }

    @Test
    internal fun `Accept 헤더에 버전 지정 v2`() {
        val headers = HttpHeaders().apply {
            set(HttpHeaders.ACCEPT, "application/vnd.antop.v2+json")
        }
        val response = restTemplate.exchange(
                "http://localhost:$port/coffees/orders/one",
                HttpMethod.GET,
                HttpEntity<String>(headers),
                Coffee::class.java)
        println("response = $response")

        assertEquals(HttpStatus.OK, response.statusCode)
        response.body?.run {
            assertEquals(4, order)
            assertEquals("Espresso", name)
            assertEquals(2.5, price)
        }
    }
}