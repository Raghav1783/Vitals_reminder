package com.example.janitri_assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.janitri_assignment.data.local.dao.PregnancydataDao
import com.example.janitri_assignment.data.local.model.PregnancyData

@Database(entities = [PregnancyData::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun pregnancydataDao(): PregnancydataDao
}