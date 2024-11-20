package com.example.smartixtest.data.source.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartixtest.domain.models.Joke

@Dao
interface JokeItemDao {
    @Query("SELECT * FROM jokes_table")
    suspend fun getListJokes(): List<Joke>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: Joke)
}