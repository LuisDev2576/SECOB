package com.luisdev2576.secob.features.auth.presentation.splash_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    userSingedIn: () -> Unit,
    userNotSingedIn: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
            viewModel.resetState()
            userSingedIn()
        }else {
            userNotSingedIn()
        }
    }
    Box(
        modifier = Modifier.background(Color.Red).fillMaxSize().background(Color.Green),
    )
}
