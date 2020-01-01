package org.antop.restful

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement

/**
 * JAXB로 변환을 하려면 빈 생성자가 필요하다.<br>
 * 그래서 모든 속성에 기본값을 넣어줌...
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
data class Coffee(var type: String = "",
                  var size: String = "",
                  var name: String = "",
                  var price: Double = 0.0,
                  var order: Int = 0) {

    override fun toString(): String {
        return "Coffee(type=$type, size=$size, name=$name, price=$price, order=$order)"
    }
}