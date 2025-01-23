package com.helltar.homepage.routes.models

import kotlinx.serialization.Serializable

object GitHub {

    @Serializable
    data class GraphQLResponse(
        val data: GraphQLData
    )

    @Serializable
    data class GraphQLData(
        val user: User?
    )

    @Serializable
    data class User(
        val pinnedItems: PinnedItems
    )

    @Serializable
    data class PinnedItems(
        val nodes: List<Repository>
    )

    @Serializable
    data class Repository(
        val name: String,
        val description: String? = null,
        val url: String,
        val primaryLanguage: Language? = null
    )

    @Serializable
    data class Language(
        val name: String
    )
}
