package org.antop.restful

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class Coffees(
        // https://blog.scottlogic.com/2016/04/04/practical-kotlin.html
        @get:XmlElement(name = "coffee")
        val coffees: MutableCollection<Coffee> = mutableListOf()) {
}