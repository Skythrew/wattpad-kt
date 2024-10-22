package com.kattpad

import com.kattpad.requests.*

class User (
    private val client: Wattpad = Wattpad(),
    val data: UserData
) {
    private val userRequest = "users/${data.username}"

    suspend fun fetchFollowers(fields: Set<String> = setOf(), limit: Int? = null): List<User> {
        val followersResult = client.fetchObjData<FollowersResult>("v3", "$userRequest/followers", setOf("total", "users(username,${fields.joinToString(",")})"), limit)

        return followersResult.users.map {data -> User(client, data)}
    }

    suspend fun fetchFollowing(fields: Set<String> = setOf(), limit: Int? = null): List<User> {
        val followingResult = client.fetchObjData<FollowingResult>("v3", "$userRequest/following", setOf("total", "users(username,${fields.joinToString(",")})"), limit)

        return followingResult.users.map {data -> User(client, data)}
    }

    suspend fun fetchLists(fields: Set<String> = setOf(), limit: Int? = null): List<WattpadList> {
        val listsResult = client.fetchObjData<ListsResult>("v3", "$userRequest/lists", setOf("total", "lists(id,${fields.joinToString(",")})"), limit)

        return listsResult.lists.map {data -> WattpadList(client, data) }
    }

    suspend fun fetchStories(fields: Set<String> = setOf("title"), limit: Int? = null): List<Story> {
        val storiesResult = client.fetchObjData<UserStoriesResult>("v4", "$userRequest/stories/published", setOf("total", "stories(id,user,${fields.joinToString(",")})"), limit)

        return storiesResult.stories.map {data -> Story(client, data)}
    }
}