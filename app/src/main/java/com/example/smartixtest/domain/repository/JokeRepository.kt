package com.example.smartixtest.domain.repository

import androidx.lifecycle.LiveData
import com.example.smartixtest.domain.models.Joke

interface JokeRepository {
    suspend fun getRandomJoke(): Result<Joke>

    suspend fun getListJokes(): List<Joke>

    suspend fun insertJoke(joke: Joke)
}