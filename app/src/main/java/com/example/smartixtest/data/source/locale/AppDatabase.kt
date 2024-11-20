package com.example.smartixtest.data.source.locale

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartixtest.domain.models.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun jokeItemDao(): JokeItemDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()
        fun getInstance(application: Application): AppDatabase {
            db?.let {
                return it
            }
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME).build()
                db = instance
                return instance
            }
        }
    }
}