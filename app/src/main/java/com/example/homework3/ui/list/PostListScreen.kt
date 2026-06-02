package com.example.homework3.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homework3.domain.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    navController: NavController,
    viewModel: PostListViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var filterText by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Посты") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = filterText,
                onValueChange = {
                    filterText = it
                    val userId = it.toIntOrNull()
                    viewModel.loadPosts(userId)
                },
                label = { Text("Фильтр по ID пользователя") },
                placeholder = { Text("Введите число") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))

            when (uiState) {
                is PostListState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is PostListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Ошибка: ${(uiState as PostListState.Error).message}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.retry() }) {
                            Text("Повторить")
                        }
                    }
                }
                is PostListState.Empty -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Посты не найдены")
                    }
                }
                is PostListState.Success -> {
                    val posts = (uiState as PostListState.Success).posts
                    LazyColumn {
                        items(posts, key = { it.id }) { post ->
                            PostItem(post = post) {
                                navController.navigate("detail/${post.id}")
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Text(text = post.title, style = MaterialTheme.typography.titleMedium)
        Text(text = "ID пользователя: ${post.userId}", style = MaterialTheme.typography.bodySmall)
    }
}