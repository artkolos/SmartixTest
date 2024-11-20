package com.example.smartixtest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.smartixtest.domain.models.Joke

class ListJokesActivity : ComponentActivity() {
    private lateinit var listJokesViewModel: ListJokesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        listJokesViewModel = ViewModelProvider.create(this)[ListJokesViewModel::class.java]
        setContent {
            Scaffold(containerColor = Color.White) { innerPadding ->
                ListJokes(
                    innerPadding = innerPadding,
                    listJokes = listJokesViewModel.listJoke.collectAsState().value
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ListJokesActivity::class.java)
            return intent
        }
    }
}

@Composable
fun ListJokes(innerPadding: PaddingValues, listJokes: List<Joke>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 20.dp, vertical = 20.dp),
    ) {
        items(listJokes.size) { position ->
            Row {
                Text(text = "${position + 1}.")
                Spacer(Modifier.width(10.dp))
                Text(text = listJokes[position].value)
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}