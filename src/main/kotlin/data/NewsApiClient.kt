package data

import data.models.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import utils.Constants

object NewsApiClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json();
        }
    }

    suspend fun getTopHeadLines(): News {
        val url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=${Constants.API_KEY}";
        return client.get(url).body();
    }

    suspend fun getSearchNews(searchText: String): News {
        val url = "https://newsapi.org/v2/everything?q=$searchText&apiKey=${Constants.API_KEY}"
        return client.get(url).body();
    }
}