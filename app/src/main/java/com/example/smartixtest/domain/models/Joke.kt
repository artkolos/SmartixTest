package com.example.smartixtest.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "jokes_table")
data class Joke(
    @PrimaryKey
    val id: String,
    val value: String
)
