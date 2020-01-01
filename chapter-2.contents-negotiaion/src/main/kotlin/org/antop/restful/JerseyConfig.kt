package org.antop.restful

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component


@Component
class JerseyConfig : ResourceConfig() {

    init {
        register(CoffeeResource::class.java)
    }

}
