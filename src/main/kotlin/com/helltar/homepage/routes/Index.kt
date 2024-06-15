package com.helltar.homepage.routes

import com.helltar.homepage.routes.Routes.ROUTE_PATH_STATIC
import com.helltar.homepage.routes.models.ProjectData
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.index() {

    get("/") {
        val model =
            mapOf(
                "projects" to
                        setOf(
                            ProjectData(
                                "aibot", "artific_intellig_bot",
                                "AI Telegram Bot, ChatGPT, Dalle2, Whisper, GPT-4 Vision, Stability AI",
                                "Kotlin, Telegram Bot"
                            ),
                            ProjectData(
                                "twitchviewer_bot", "twitchviewer_bot",
                                "A bot for obtaining clips or screenshots from Twitch",
                                "Kotlin, Telegram Bot"
                            )
                        ),

                "routePathStatic" to ROUTE_PATH_STATIC
            )

        call.respond(FreeMarkerContent("index.ftl", model))
    }
}