package com.example.studenthub_compose.ui.auth.register


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun RegisterRoute(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    openRegisterScreen: () -> Unit = {},
    openLoginScreen: () -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is RegisterViewModel.RegisterEvent.SuccessLogin -> openLoginScreen()
                is RegisterViewModel.RegisterEvent.SuccessRegister -> {
                    Toast.makeText(context, "✅Registered successfully!", Toast.LENGTH_SHORT).show()
                    openLoginScreen()
                }
                is RegisterViewModel.RegisterEvent.UsernameAlreadyExists -> {
                    Toast.makeText(context, "User already exists!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    RegisterScreen(
        modifier = modifier,
        uiState = uiState,
        onFirstNameChanged = { viewModel.onFirstNameChanged(it) },
        onLastNameChanged = { viewModel.onLastNameChanged(it) },
        onUserNameChanged = { viewModel.onUserNameChanged(it) },
        onPasswordChanged = { viewModel.onPasswordChanged(it) },
        registerButtonClicked = { viewModel.register() },
        visibilityButtonClicked = {viewModel.onVisibilityButtonClicked()},
        loginButtonClicked = openLoginScreen
    )
}


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState,
    onFirstNameChanged: (String) -> Unit = {},
    onLastNameChanged: (String) -> Unit = {},
    onUserNameChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    registerButtonClicked: () -> Unit = {},
    visibilityButtonClicked: () -> Unit = {},
    loginButtonClicked: () -> Unit = {}
) {

    var passwordVisible by remember { mutableStateOf(false) }


    Box(
        modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 40.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 👤 Avatar rasmi
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Avatar",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 70.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(Modifier.height(4.dp))

            // 🪪 "Sign In"
            Text(
                text = "Create your account",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(20.dp))

            TextField(
                value = uiState.firstName,
                onValueChange = onFirstNameChanged,
                isError = uiState.firstNameError != null,
                placeholder = { Text("First name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                    )
                if (uiState.firstNameError != null) {
                    Text(
                        text = uiState.firstNameError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 16.dp, top = 2.dp)
                    )
                }

            Spacer(Modifier.height(7.dp))

            TextField(
                value = uiState.lastName,
                onValueChange = onLastNameChanged,
                isError = uiState.lastNameError != null,
                placeholder = { Text("Last name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                    )
                if (uiState.lastNameError != null) {
                    Text(
                        text = uiState.lastNameError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 16.dp, top = 2.dp)
                    )
                }

            Spacer(Modifier.height(7.dp))

            TextField(
                value = uiState.userName,
                onValueChange = onUserNameChanged,
                placeholder = { Text("Username") },
                isError = uiState.usernameError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.dp, MaterialTheme.colorScheme.primary,RoundedCornerShape(20.dp))
                    )
                if (uiState.usernameError != null) {
                    Text(
                        text = uiState.usernameError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 16.dp, top = 2.dp)
                    )
                }

            Spacer(Modifier.height(7.dp))

            TextField(
                value = uiState.password,
                onValueChange = onPasswordChanged,
                placeholder = { Text("Password") },
                isError = uiState.passwordError != null,

                // Parolni yashirish yoki ko‘rsatish
                visualTransformation = if (uiState.passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                // Ko'zcha ikonkasi
                trailingIcon = {
                    val image = if (uiState.passwordVisible)
                        Icons.Default.Visibility
                    else
                        Icons.Default.VisibilityOff

                    IconButton(onClick = visibilityButtonClicked) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (uiState.passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .border(1.dp,MaterialTheme.colorScheme.primary,RoundedCornerShape(20.dp))
                    )
                if (uiState.passwordError != null) {
                    Text(
                        text = uiState.passwordError,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 16.dp, top = 2.dp)
                    )
                }


            if (uiState.successError) {
                Text(
                    text = "This username already exists!",
                    color = MaterialTheme.colorScheme.error,
                )

            }

            Button(
                onClick = registerButtonClicked,
                enabled = uiState.enableRegisterButton,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp)
                    .width(110.dp)


            ) {
                Text(text = "Sign In")
            }
        }


        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already have an account?")
            TextButton(onClick = loginButtonClicked) {
                Text(
                    text = "Sign In",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold

                )
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        uiState = RegisterUiState(
            enableRegisterButton = false,
            successError = true
        )
    )
}
