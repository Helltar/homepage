package com.helltar.homepage

import com.helltar.homepage.routes.index
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

const val ROUTE_PATH_METRICS = "/metrics"
const val ROUTE_PATH_STATIC = "/static"

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
        staticFiles("/", File("data/helltar.com")) { enableAutoHeadResponse() }
    }
}
