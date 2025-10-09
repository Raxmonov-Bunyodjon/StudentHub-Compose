package com.example.studenthub_compose.ui.main.student


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
fun StudentRoute(
    viewModel: StudentViewModel = hiltViewModel()
){
    StudentScreen()
}

@Composable
fun StudentScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Student Screen")
    }
}