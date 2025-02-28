package com.example.janitri_assignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.janitri_assignment.data.local.model.PregnancyData
import com.example.janitri_assignment.data.repository.PregnancyDataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PregnancyDataScreenVM @Inject constructor(private val repository:PregnancyDataRepo):ViewModel() {

    private val _pregnancyData = MutableStateFlow<List<PregnancyData>>(emptyList())
    val pregnancyData = _pregnancyData

    fun insertData (data: PregnancyData){
        viewModelScope.launch {
            repository.InsertData(data)
            getAllData()
        }
    }

    fun getAllData() {
        CoroutineScope(Dispatchers.IO).launch{
            repository.GetData().collectLatest {
                _pregnancyData.emit(it)
            }
        }
    }
}