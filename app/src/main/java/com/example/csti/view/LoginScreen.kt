package com.example.csti.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.csti.model.LoginResult
import com.example.csti.model.LoginViewModel
import com.example.csti.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginResult by viewModel.loginResult.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .size(250.dp),
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                viewModel.login(username, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp, end = 100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c))
        ) {
            Text("Iniciar sesión", color = Color.White)
        }
        LaunchedEffect(loginResult) {
            when (loginResult) {
                is LoginResult.Success -> {
                    val user = (loginResult as LoginResult.Success).user
                    if (user.rol == "admin") {
                        navController.navigate("MainScreenAdmin") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("MainScreen") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }
                is LoginResult.Error -> {
                    val message = (loginResult as LoginResult.Error).message
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
                else -> {}
            }
        }
    }
    // Añadir SnackbarHost al final para mostrar mensajes
    SnackbarHost(hostState = snackbarHostState)
}
