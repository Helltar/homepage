package com.helltar.homepage.routes

import com.helltar.homepage.updaters.PinnedReposFetcher.Companion.githubRepositories
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.index() {

    get("/") {
        val model = mapOf("projects" to githubRepositories())
        call.respond(FreeMarkerContent("index.ftl", model))
    }
}
