package com.kattpad.requests

import kotlinx.serialization.Serializable

@Serializable
data class FollowingResult(
    val users: List<UserData>,
    val total: Int
)
