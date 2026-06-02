package com.example.homework3.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homework3.data.repository.PostRepository
import com.example.homework3.ui.detail.PostDetailScreen
import com.example.homework3.ui.detail.PostDetailViewModel
import com.example.homework3.ui.list.PostListScreen
import com.example.homework3.ui.list.PostListViewModel

@Composable
fun NavGraph(repository: PostRepository) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            val viewModel: PostListViewModel = viewModel(
                factory = PostListViewModelFactory(repository)
            )
            PostListScreen(navController, viewModel)
        }
        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val viewModel: PostDetailViewModel = viewModel(
                factory = PostDetailViewModelFactory(repository, id)
            )
            PostDetailScreen(viewModel)
        }
    }
}