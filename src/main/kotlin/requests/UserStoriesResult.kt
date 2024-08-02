package com.kattpad.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserStoriesResult (
    val stories: List<StoryData>,
    val total: Int
)