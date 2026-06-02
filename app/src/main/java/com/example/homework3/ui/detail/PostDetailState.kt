package com.example.homework3.ui.detail

import com.example.homework3.domain.Post

sealed class PostDetailState {
    object Loading : PostDetailState()
    data class Success(val post: Post) : PostDetailState()
    data class Error(val message: String) : PostDetailState()
}