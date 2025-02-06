package com.helltar.homepage.plugins

import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry
import org.slf4j.event.Level

fun Application.configureMonitoring() {

    install(CallLogging) {
        level = Level.INFO

        filter { call -> shouldLog(call.request.path()) }

        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            val remoteAddress = call.request.headers["X-Forwarded-For"] ?: call.request.origin.remoteAddress
            val path = call.request.path()
            val time = call.processingTimeMillis()
            "$status: $httpMethod - $path in $time ms ($remoteAddress - $userAgent)"
        }
    }

    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
    }

    routing {
        get("/metrics") {
            call.respond(appMicrometerRegistry.scrape())
        }
    }
}

private fun shouldLog(path: String) =
    !path.startsWith("/metrics") && !path.startsWith("/static")
