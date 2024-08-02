package com.kattpad

import com.kattpad.requests.*

class Wattpad (cookie: String = "") : Authentication (cookie) {
    suspend fun fetchList(id: Int, fields: Set<String> = setOf()): WattpadList {
        val listData = fetchObjData<ListData>("v3", "lists/$id", fields)

        return WattpadList(this, listData)
    }

    suspend fun fetchStory(id: Int, fields: Set<String> = setOf()): Story {
        val storyData = fetchObjData<StoryData>("v3", "stories/$id", fields)

        return Story(this, storyData)
    }

    suspend fun fetchStoryPart(id: Int, fields: Set<String> = setOf()): Part {
        val storyPartData = fetchObjData<StoryPartData>("v4", "parts/$id", setOf("id", "text_url").plus(fields))

        return Part(this, storyPartData)
    }

    suspend fun fetchUser(username: String, fields: Set<String> = setOf()): User {
        val userData = fetchObjData<UserData>("v3", "users/$username", fields)

        return User(this, userData)
    }

    suspend fun searchUser(username: String): List<UserSearchResult> {
        val params = mapOf(
            "query" to username
        )

        return fetch<List<UserSearchResult>>("v4", "search/users/", params)
    }
}