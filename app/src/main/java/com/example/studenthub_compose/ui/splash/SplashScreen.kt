package com.example.studenthub_compose.ui.splash


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.studenthub_compose.R
import kotlinx.coroutines.delay


@Composable
fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    onFinish: (Boolean) -> Unit
){
    val state = viewModel.uiState.collectAsState().value

    SplashScreen(isLoading = state.isLoader)

    LaunchedEffect(state.isFinished) {
        if (state.isFinished) {
            onFinish(viewModel.isUserSignedIn())
        }
    }
}

@Composable
fun SplashScreen(isLoading: Boolean) {
    var visible by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "alpha"
    )

    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                painter = painterResource(id = R.drawable.ic_studenthub_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(alphaAnim)
            )

            Spacer(modifier = Modifier.height(16.dp))

            androidx.compose.material3.Text(
                text = "StudentHub",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alpha(alphaAnim)
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White.copy(alpha= 0.9f),
                    strokeWidth = 3.dp,
                    modifier = Modifier.alpha(alphaAnim)
                )
        }
    }
}}