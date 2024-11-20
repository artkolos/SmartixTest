package com.example.smartixtest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.smartixtest.R
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider.create(this)[MainViewModel::class.java]
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            JokeContent(
                joke = mainViewModel.joke.collectAsState().value?.value ?: "",
                onClick = {
                    lifecycleScope.launch {
                        mainViewModel.fetchRandomJoke()
                    }
                },
                onClickListJokes = {
                    startActivity(ListJokesActivity.newIntent(this@MainActivity))
                },
                isLading = mainViewModel.isLoading.collectAsState().value,
                error = mainViewModel.error.collectAsState().value
            )
        }
    }
}

@Composable
fun JokeContent(joke: String, onClick: () -> Unit, onClickListJokes: () -> Unit, error: String?, isLading: Boolean) {
    val res = LocalContext.current.resources
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLading) CircularProgressIndicator()
        else {
            if (error == null)
                Text(
                    joke,
                    textAlign = TextAlign.Center
                )
            else
                Text(
                    res.getString(R.string.network_error), textAlign = TextAlign.Center
                )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onClick
        ) {
            Text(res.getString(R.string.random_joke))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onClickListJokes
        ) {
            Text(res.getString(R.string.view_list_jokes))
        }
    }
}