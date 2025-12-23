package com.helltar.homepage

import io.ktor.server.config.*

object Config {

    const val DATA_DIR = "data"
    const val WALLPAPERS_DIR = "$DATA_DIR/wallpapers"

    data class ConfigData(
        val apiKey: String,
        val githubToken: String
    )

    lateinit var config: ConfigData
        private set

    fun initialize(config: ApplicationConfig) {
        this.config =
            ConfigData(
                apiKey = config.property("homepage.apikey").getString(),
                githubToken = config.property("github.token").getString()
            )
    }
}
