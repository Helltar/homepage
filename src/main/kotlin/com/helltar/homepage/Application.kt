package com.helltar.homepage

import com.helltar.homepage.plugins.configureMonitoring
import com.helltar.homepage.plugins.configureRouting
import com.helltar.homepage.plugins.configureSerialization
import com.helltar.homepage.plugins.configureTemplating
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
