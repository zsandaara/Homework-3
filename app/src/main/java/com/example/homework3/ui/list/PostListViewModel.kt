package com.example.homework3.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework3.data.repository.PostRepository
import com.example.homework3.domain.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel(private val repository: PostRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<PostListState>(PostListState.Loading)
    val uiState: StateFlow<PostListState> = _uiState.asStateFlow()

    private var currentUserId: Int? = null

    /*fun loadPosts(userId: Int? = null) {
        currentUserId = userId
        viewModelScope.launch {
            _uiState.value = PostListState.Loading
            try {
                val posts = repository.getPosts(userId)
                if (posts.isEmpty()) {
                    _uiState.value = PostListState.Empty
                } else {
                    _uiState.value = PostListState.Success(posts)
                }
            } catch (e: Exception) {
                _uiState.value = PostListState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }*/

    fun loadPosts(userId: Int? = null) {
        currentUserId = userId
        viewModelScope.launch {
            _uiState.value = PostListState.Loading
            try {
                android.util.Log.d("PostListVM", "Начинаем загрузку, userId=$userId")
                val posts = repository.getPosts(userId)
                android.util.Log.d("PostListVM", "Загружено постов: ${posts.size}")
                if (posts.isEmpty()) {
                    _uiState.value = PostListState.Empty
                } else {
                    _uiState.value = PostListState.Success(posts)
                }
            } catch (e: Exception) {
                android.util.Log.e("PostListVM", "Ошибка загрузки", e)
                _uiState.value = PostListState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }

    fun retry() {
        loadPosts(currentUserId)
    }
}