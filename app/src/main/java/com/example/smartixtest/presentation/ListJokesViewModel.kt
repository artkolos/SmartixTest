package com.example.smartixtest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartixtest.data.repository.JokeRepositoryImpl
import com.example.smartixtest.domain.models.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListJokesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = JokeRepositoryImpl(application)
    private val _listJokes = MutableStateFlow<List<Joke>>(emptyList())
    val listJoke: StateFlow<List<Joke>>
        get() = _listJokes

    init {
        fetchListJokes()
    }

    private fun fetchListJokes() {
        viewModelScope.launch {
            _listJokes.value = repository.getListJokes()
        }
    }
}