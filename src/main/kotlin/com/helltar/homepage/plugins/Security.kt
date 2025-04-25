package com.helltar.homepage.plugins

import com.helltar.homepage.Config.config
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureSecurity() {

    install(Authentication) {
        bearer("auth-bearer") {
            realm = "Access to API"

            authenticate { tokenCredential ->
                if (tokenCredential.token == config.apiKey)
                    UserIdPrincipal("MAIN_KEY")
                else
                    null
            }
        }
    }
}
