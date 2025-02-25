package com.example.msgapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.msgapp.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM message ORDER BY timestamp ASC")
    fun getAllMessage(): Flow<List<Message>>

    @Insert
    suspend fun insertMessage(message: Message)
}