package com.example.janitri_assignment.di

import android.content.Context
import androidx.room.Room
import com.example.janitri_assignment.data.local.AppDatabase
import com.example.janitri_assignment.data.local.dao.PregnancydataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun providePregancydataDb(
        @ApplicationContext app: Context
    ): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "Pregnancydata_DB"
    ).build()

    @Singleton
    @Provides
    fun providePregancydataDao(db: AppDatabase): PregnancydataDao = db.pregnancydataDao()
}