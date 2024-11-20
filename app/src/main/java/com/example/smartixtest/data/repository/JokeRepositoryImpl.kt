package com.example.smartixtest.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.smartixtest.data.source.locale.AppDatabase
import com.example.smartixtest.data.source.remote.ApiService
import com.example.smartixtest.domain.models.Joke
import com.example.smartixtest.domain.repository.JokeRepository

class JokeRepositoryImpl(application: Application) : JokeRepository {

    private val apiService = ApiService()
    private val db = AppDatabase.getInstance(application)

    override suspend fun getRandomJoke(): Result<Joke> = runCatching {
        apiService.getRandomJoke()
    }

    override suspend fun getListJokes(): List<Joke> = db.jokeItemDao().getListJokes()

    override suspend fun insertJoke(joke: Joke) = db.jokeItemDao().insertJoke(joke)
}