package com.kattpad.requests

import kotlinx.serialization.Serializable

@Serializable
data class CommentsResult(
    val comments: List<CommentData>
)
