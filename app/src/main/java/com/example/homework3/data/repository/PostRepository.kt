package com.example.homework3.data.repository

import com.example.homework3.data.remote.ApiService
import com.example.homework3.data.remote.toDomain
import com.example.homework3.domain.Post

class PostRepository(private val apiService: ApiService) {
    suspend fun getPosts(userId: Int? = null): List<Post> {
        return apiService.getPosts(userId).map { it.toDomain() }
    }

    suspend fun getPost(id: Int): Post {
        return apiService.getPost(id).toDomain()
    }
}