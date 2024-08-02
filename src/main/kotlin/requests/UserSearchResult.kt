package com.kattpad.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserSearchResult (
    val username: String,
    val name: String = "",
    val avatar: String = "",
    val description: String = "",
    val numFollowers: Int,
    val numStoriesPublished: Int,
    val numLists: Int
)