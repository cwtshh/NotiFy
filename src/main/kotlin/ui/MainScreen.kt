package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import data.NewsApiClient
import data.models.Article
import io.ktor.client.plugins.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var articles by remember { mutableStateOf(emptyList<Article>()) }
    var headerTitle by remember { mutableStateOf("Top Headlines") }
    var searchText by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope();

    // famoso useEffect kkkkkkk
    LaunchedEffect(searchText) {
        scope.launch {
            try {
                val newsData = if(searchText.isNotEmpty()) {
                    NewsApiClient.getSearchNews(searchText);
                } else {
                    NewsApiClient.getTopHeadLines();
                }

                articles = newsData.articles;
            } catch (e: ClientRequestException) {
                println("Erro ao buscar dados: ${e.message}")
            }
        }
    }

    Row {
        // sidepanel
    }
}