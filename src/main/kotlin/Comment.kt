package com.kattpad

import com.kattpad.requests.CommentData
import com.kattpad.requests.CommentsResult

class Comment (
    private val client: Wattpad,
    val data: CommentData
) {
    suspend fun fetchReplies(fields: Set<String> = setOf(), limit: Int? = null): List<Comment> {
        val repliesResult = client.fetchObjData<CommentsResult>("v5", "comments/namespaces/comments/resources/${data.commentId.resourceId}/comments", fields, limit)

        return repliesResult.comments.map {data -> Comment(client, data)}
    }
}