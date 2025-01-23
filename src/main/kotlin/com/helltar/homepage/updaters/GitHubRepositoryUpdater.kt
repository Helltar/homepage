package com.helltar.homepage.updaters

import com.helltar.homepage.routes.models.GitHub
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.Duration.Companion.hours

class GitHubRepositoryUpdater(private val delayHours: Long) {

    companion object {
        val githubRepositories = CopyOnWriteArrayList<GitHub.Repository>()
        private val log = KotlinLogging.logger {}
    }

    fun start() {
        CoroutineScope(Dispatchers.IO + CoroutineName(javaClass.name))
            .launch {
                while (isActive) {
                    log.info { "update github repositories data ..." }
                    update()
                    delay(delayHours.hours.inWholeMilliseconds)
                }
            }
    }

    private suspend fun update() {
        getPinnedRepos()?.let {
            githubRepositories.clear()
            githubRepositories.addAll(it)
        }
    }

    private suspend fun getPinnedRepos(size: Int = 3, login: String = "Helltar"): List<GitHub.Repository>? {
        val query =
            """
                query {
                    user(login: "$login") {
                        pinnedItems(first: $size, types: [REPOSITORY]) {
                            nodes {
                                ... on Repository {
                                  name
                                  description
                                  url
                                  primaryLanguage {
                                    name
                                  }
                                }
                            }
                        }
                    }
                }
                """.trimIndent()

        return try {
            HttpClient {
                install(ContentNegotiation) { json() }
            }.use {
                it.post("https://api.github.com/graphql") {
                    headers { append(HttpHeaders.Authorization, "Bearer ${System.getenv("GITHUB_TOKEN")}") }
                    contentType(ContentType.Application.Json)
                    setBody(mapOf("query" to query))
                }
                    .body<GitHub.GraphQLResponse>()
            }
                .data.user?.pinnedItems?.nodes
        } catch (e: Exception) {
            log.error { e.message }
            null
        }
    }
}
