package com.helltar.homepage.updaters.models

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse(
    val data: GraphQLData
)

@Serializable
data class GraphQLData(
    val user: GitHubUser?
)

@Serializable
data class GitHubUser(
    val pinnedItems: UserPinnedItems
)

@Serializable
data class UserPinnedItems(
    val nodes: List<GitHubRepository>
)

@Serializable
data class GitHubRepository(
    val name: String,
    val description: String? = null,
    val url: String,
    val primaryLanguage: RepositoryLanguage? = null
)

@Serializable
data class RepositoryLanguage(
    val name: String
)
