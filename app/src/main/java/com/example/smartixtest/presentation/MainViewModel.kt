package com.example.smartixtest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartixtest.data.repository.JokeRepositoryImpl
import com.example.smartixtest.domain.models.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = JokeRepositoryImpl(application)

    private val _joke = MutableStateFlow<Joke?>(null)
    val joke: StateFlow<Joke?>
        get() = _joke
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?>
        get() = _error

    init {
        fetchRandomJoke()
    }

    fun fetchRandomJoke() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getRandomJoke().fold({
                _isLoading.value = false
                repository.insertJoke(it)
                _joke.value = it
                _error.value = null
            }) {
                _isLoading.value = false
                _error.value = it.message
            }
        }
    }
}