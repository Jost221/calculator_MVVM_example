package com.example.calculator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insert(calculation: Entity)

    @Query("SELECT * FROM calculations ORDER BY timestamp DESC")
    fun getAll(): Flow<List<Entity>>

    @Query("DELETE FROM calculations")
    suspend fun deleteAll()
}