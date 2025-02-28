package com.example.janitri_assignment.data.repository

import com.example.janitri_assignment.data.local.dao.PregnancydataDao
import com.example.janitri_assignment.data.local.model.PregnancyData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PregnancyDataRepo @Inject constructor(
    private val pregnancydataDao: PregnancydataDao
) {
    fun GetData(): Flow<List<PregnancyData>> = pregnancydataDao.getAllPregnancyData()

    suspend fun InsertData(data: PregnancyData) {

        pregnancydataDao.insertPregnancyData(
            data
        )
    }


}