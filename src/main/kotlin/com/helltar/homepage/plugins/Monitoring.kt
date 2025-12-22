package com.helltar.homepage.plugins

import com.helltar.homepage.routes.Endpoints.PATH_METRICS
import com.helltar.homepage.routes.Endpoints.PATH_STATIC
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.slf4j.event.Level

fun Application.configureMonitoring() {

    val appMicrometerRegistry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    install(MicrometerMetrics) {
        registry = appMicrometerRegistry
    }

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

    routing {
        authenticate("auth-bearer") {
            get(PATH_METRICS) {
                call.respond(appMicrometerRegistry.scrape())
            }
        }
    }
}

private fun shouldLog(path: String) =
    !path.startsWith(PATH_METRICS) && !path.startsWith(PATH_STATIC)
