package com.helltar.homepage

import com.helltar.homepage.updaters.GitHubRepositoryUpdater
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureTemplating()
    configureRouting()
    configureUpdaters()
}

private fun Application.configureUpdaters() {
    GitHubRepositoryUpdater(20).start()
}
