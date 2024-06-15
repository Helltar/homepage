package com.helltar.homepage.plugins

import com.helltar.homepage.PathConstants.DIR_SITE_DATA
import com.helltar.homepage.routes.index
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureRouting() {

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }

        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText("\uD83E\uDD2D", status = status)
        }
    }

    routing {
        index()
        staticFiles("/", File(DIR_SITE_DATA)) { enableAutoHeadResponse() }
    }
}
