package com.example.janitri_assignment.ui_screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.janitri_assignment.R
import com.example.janitri_assignment.ViewModel.PregnancyDataScreenVM
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.janitri_assignment.Application
import com.example.janitri_assignment.resetNotificationWorker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: PregnancyDataScreenVM = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Track My Pregnancy",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6A1B9A)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color(0xFF8E44AD),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PregnancyDataScreen(viewModel)
        }
    }

    if (showDialog) {
        AddVitalDialogBox(
            onDismiss = { showDialog = false },
            onSubmit = { data ->

                if (data.weight.isNotEmpty()&&data.dateTime.isNotEmpty()&&data.babyKicks.isNotEmpty()&&data.heartRate.isNotEmpty()&&data.bloodPressure.isNotEmpty()){
                    viewModel.insertData(data)
                    resetNotificationWorker(context)
                }

                showDialog = false
            }
        )
    }
}

@Composable
fun PregnancyDataScreen(viewModel:PregnancyDataScreenVM) {



    LaunchedEffect (Unit){
        viewModel.getAllData()
    }
    val dataList = viewModel.pregnancyData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
           items(dataList.value){item ->
               PregnancyDataItem(item)
           }
        }
    }
}


@Composable
fun HomeScreenPreview() {
    HomeScreen()
}