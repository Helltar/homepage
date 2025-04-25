package com.helltar.homepage.updaters

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.Duration.Companion.hours

class PinnedReposFetcher(private val githubToken: String) {

    companion object {
        private val githubRepositories = CopyOnWriteArrayList<GitHubRepository>()
        private val log = KotlinLogging.logger {}

        fun githubRepositories() =
            githubRepositories.toList()
    }

    fun start(delayHours: Long) {
        CoroutineScope(Dispatchers.IO + CoroutineName(javaClass.name))
            .launch {
                while (isActive) {
                    val repos = getPinnedRepos()

                    log.debug { "github repositories: $repos" }

                    repos?.let {
                        githubRepositories.clear()
                        githubRepositories.addAll(it)
                        log.info { "github repositories updated" }
                    }

                    delay(delayHours.hours.inWholeMilliseconds)
                }
            }
    }

    private suspend fun getPinnedRepos(size: Int = 3, login: String = "Helltar"): List<GitHubRepository>? {
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
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            encodeDefaults = true
                            explicitNulls = false
                        })
                }
            }.use {
                it.post("https://api.github.com/graphql") {
                    headers { append(HttpHeaders.Authorization, "Bearer $githubToken") }
                    contentType(ContentType.Application.Json)
                    setBody(mapOf("query" to query))
                }
                    .body<GraphQLResponse>()
            }
                .data.user?.pinnedItems?.nodes
        } catch (e: Exception) {
            log.error { e.message }
            null
        }
    }
}
