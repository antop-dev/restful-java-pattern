package org.antop.restful

import org.junit.jupiter.api.Test
import java.io.StringWriter
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

internal class CoffeeTest {

    @Test
    internal fun `JAXB 변환`() {
        val c = Coffee("Brewed", "Small", "Mocha", 3.50)
        // Create JAXB Context
        val jaxbContext: JAXBContext = JAXBContext.newInstance(Coffee::class.java)
        // Create Marshaller
        val jaxbMarshaller: Marshaller = jaxbContext.createMarshaller()
        // Required formatting??
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        // Print XML String to Console
        val sw = StringWriter()
        // Write XML to StringWriter
        jaxbMarshaller.marshal(c, sw)
        // Verify XML Content
        val xmlContent = sw.toString()
        println(xmlContent)
    }
}