package com.example.homework3.ui.list

import com.example.homework3.domain.Post

sealed class PostListState {
    object Loading : PostListState()
    data class Success(val posts: List<Post>) : PostListState()
    data class Error(val message: String) : PostListState()
    object Empty : PostListState()
} 