package com.example.smartixtest.data.source.remote

import android.util.Log
import com.example.smartixtest.domain.models.Joke
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService {
    private val httpClient = httpClientAndroid

    suspend fun getRandomJoke(): Joke {
            val joke = httpClient.get(BASE_URL).body<Joke>()
            Log.i("msg", joke.toString())
            return joke;
    }

    companion object {
        private const val BASE_URL = "https://api.chucknorris.io/jokes/random"
    }
}