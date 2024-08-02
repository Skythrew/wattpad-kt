package com.kattpad

import com.kattpad.requests.CommentsResult
import com.kattpad.requests.StoryPartData
import io.ktor.client.statement.*

data class Part (
    private val client: Wattpad,
    val data: StoryPartData
) {
    suspend fun fetchComments(fields: Set<String> = setOf(), limit: Int? = null): List<Comment> {
        val commentsData = client.fetchObjData<CommentsResult>("v5", "comments/namespaces/parts/resources/${data.id}/comments", fields, limit)

        return commentsData.comments.map {data -> Comment(client, data)}
    }

    suspend fun fetchText(): String {
        return client.simpleGet(data.textUrl.text).bodyAsText()
    }
}