package com.kattpad.requests

import kotlinx.serialization.Serializable

@Serializable
data class ListsResult(
    val lists: List<ListData>,
    val total: Int
)
