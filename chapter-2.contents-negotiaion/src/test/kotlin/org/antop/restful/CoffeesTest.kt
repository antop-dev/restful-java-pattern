package org.antop.restful

import org.junit.jupiter.api.Test
import java.io.StringWriter
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

internal class CoffeesTest {
    @Test
    internal fun `JAXB 변환`() {
        val c1 = Coffee("Brewed", "Small", "Mocha", 3.50)
        val c2 = Coffee("Brewed", "Small", "Mocha", 3.50)
        val coffees = Coffees(mutableListOf(c1, c2))
        // Create JAXB Context
        val jaxbContext: JAXBContext = JAXBContext.newInstance(Coffees::class.java)
        // Create Marshaller
        val jaxbMarshaller: Marshaller = jaxbContext.createMarshaller()
        // Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        // Print XML String to Console
        val sw = StringWriter()
        // Write XML to StringWriter
        jaxbMarshaller.marshal(coffees, sw)
        // Verify XML Content
        val xmlContent = sw.toString()
        println(xmlContent)
    }
}