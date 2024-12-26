package com.helltar.homepage.routes

import com.helltar.homepage.routes.models.ProjectData
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
                                "Kotlin"
                            ),
                            ProjectData(
                                "signai", "signai",
                                "ChatGPT Bot for the Signal Messenger",
                                "Kotlin"
                            ),
                            ProjectData(
                                "twitchviewer_bot", "twitchviewer_bot",
                                "Telegram Bot for obtaining clips and screenshots from Twitch",
                                "Kotlin"
                            )
                        )
            )

        call.respond(FreeMarkerContent("index.ftl", model))
    }
}
