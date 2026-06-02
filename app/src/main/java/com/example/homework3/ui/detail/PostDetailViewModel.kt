package com.example.homework3.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework3.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val repository: PostRepository,
    private val postId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostDetailState>(PostDetailState.Loading)
    val uiState: StateFlow<PostDetailState> = _uiState.asStateFlow()

    init {
        loadPost()
    }

    private fun loadPost() {
        viewModelScope.launch {
            _uiState.value = PostDetailState.Loading
            try {
                val post = repository.getPost(postId)
                _uiState.value = PostDetailState.Success(post)
            } catch (e: Exception) {
                _uiState.value = PostDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun retry() {
        loadPost()
    }
}