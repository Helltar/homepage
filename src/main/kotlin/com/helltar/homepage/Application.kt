package com.helltar.homepage

import com.helltar.homepage.Config.config
import com.helltar.homepage.plugins.*
import com.helltar.homepage.updaters.PinnedReposFetcher
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    Config.initialize(environment.config)

    configureSecurity()
    configureMonitoring()
    configureSerialization()
    configureTemplating()
    configureRouting()

    monitor.subscribe(ApplicationStarted) {
        PinnedReposFetcher(config.githubToken)
            .start(delayHours = 2)
    }
}
