package com.example.homework3.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework3.data.repository.PostRepository
import com.example.homework3.ui.detail.PostDetailViewModel
import com.example.homework3.ui.list.PostListViewModel

class PostListViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PostDetailViewModelFactory(
    private val repository: PostRepository,
    private val postId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostDetailViewModel(repository, postId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}