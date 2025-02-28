package com.example.janitri_assignment.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pregnancy_table")
data class PregnancyData(

    val heartRate: String,
    val bloodPressure: String,
    val weight: String,
    val babyKicks: String,
    val dateTime: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
