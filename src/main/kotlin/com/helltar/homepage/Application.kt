package com.helltar.homepage

import com.helltar.homepage.plugins.configureMonitoring
import com.helltar.homepage.plugins.configureRouting
import com.helltar.homepage.plugins.configureSerialization
import com.helltar.homepage.plugins.configureTemplating
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {

    embeddedServer(
        Netty,
        port = 8443,
        module = Application::module,
        configure = {
            connectionGroupSize = 16
            workerGroupSize = 32
            callGroupSize = 64
        }
    )
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureTemplating()
    configureRouting()
}
