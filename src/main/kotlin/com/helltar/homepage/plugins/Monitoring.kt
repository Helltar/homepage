package com.helltar.homepage.plugins

import com.helltar.homepage.routes.Routes.ROUTE_PATH_METRICS
import com.helltar.homepage.routes.Routes.ROUTE_PATH_STATIC
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheus.*
import org.slf4j.event.*

fun Application.configureMonitoring() {

    install(CallLogging) {
        level = Level.INFO

        filter { call -> shouldLog(call.request.path()) }

        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            val remoteAddress = call.request.origin.remoteAddress
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
        get(ROUTE_PATH_METRICS) {
            call.respond(appMicrometerRegistry.scrape())
        }
    }
}

private fun shouldLog(path: String) =
    !path.startsWith(ROUTE_PATH_METRICS) && !path.startsWith(ROUTE_PATH_STATIC)
