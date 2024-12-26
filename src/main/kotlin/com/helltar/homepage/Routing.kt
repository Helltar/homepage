package com.helltar.homepage

import com.helltar.homepage.routes.index
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.respondTemplate
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
            call.respondTemplate("404.ftl", null)
        }
    }

    routing {
        index()
        staticFiles("/", File("data")) { enableAutoHeadResponse() }
    }
}
