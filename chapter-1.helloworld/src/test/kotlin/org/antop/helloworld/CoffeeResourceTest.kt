package org.antop.helloworld

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
    @Autowired
    lateinit var coffeeService: CoffeeService

    @AfterEach
    internal fun tearDown() {
        // 테스트 순서가 보장되지 않기 때문에 하나의 테스트를 하면 상태를 리셋한다.
        coffeeService.reset()
    }

    @Test
    fun `GET 커피 주문 조회`() {
        val response = restTemplate.getForEntity("/v1/coffees/orders/1", Coffee::class.java)
        println("response = $response")

        assertEquals(200, response.statusCode.value())
        Assertions.assertNotNull(response.body)
        response.body?.let {
            assertEquals(1, it.order)
            assertEquals("Brewed", it.type)
            assertEquals("Small", it.size)
            assertEquals("Mocha", it.name)
            assertEquals(3.50, it.price)
        }
    }

    @Test
    fun `GET 커피 주문 목록 조회`() {
        val response = restTemplate.getForEntity("/v1/coffees/orders", Array<Coffee>::class.java)
        println("response = $response")

        assertEquals(HttpStatus.OK, response.statusCode)
        Assertions.assertNotNull(response.body)

        response.body?.let {
            for (coffee in it.iterator()) {
                println("coffee = $coffee")
            }

            assertTrue(it.isNotEmpty())
        }
    }

    @Test
    fun `POST 커피 주문 추가`() {
        val response = restTemplate.postForEntity(
                "/v1/coffees",
                Coffee("Brewed", "Small", "Americano", 4.0),
                Coffee::class.java)
        println("response = $response")

        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun `PUT 같은 주문번호는 수정`() {
        val c1 = Coffee("Brewed", "Small", "Americano", 4.0)

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val httpEntity = HttpEntity(c1, headers)

        val response = restTemplate.exchange("http://localhost:$port/v1/coffees/1", HttpMethod.PUT, httpEntity, Coffee::class.java)
        println("response = $response")
        assertEquals(HttpStatus.OK, response.statusCode)
    }

    @Test
    fun `PUT 다른 주문번호는 등록`() {
        val c1 = Coffee("Brewed", "Small", "Americano", 4.0)

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val httpEntity = HttpEntity(c1, headers)

        val response = restTemplate.exchange("http://localhost:$port/v1/coffees/9", HttpMethod.PUT, httpEntity, Coffee::class.java)
        println("response = $response")
        assertEquals(HttpStatus.CREATED, response.statusCode)
    }

}