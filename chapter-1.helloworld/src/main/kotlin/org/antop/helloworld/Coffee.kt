package org.antop.helloworld

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Coffee(var type: String, var size: String, var name: String, var price: Double, var order: Int = 0) {
    override fun toString(): String {
        return "Coffee(type=$type, size=$size, name=$name, price=$price, order=$order)"
    }
}