package com.example.janitri_assignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.janitri_assignment.data.local.model.PregnancyData
import kotlinx.coroutines.flow.Flow

@Dao
interface PregnancydataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPregnancyData(data: PregnancyData)

    @Query("SELECT * FROM pregnancy_table ORDER BY dateTime DESC")
    fun getAllPregnancyData(): Flow<List<PregnancyData>>
}