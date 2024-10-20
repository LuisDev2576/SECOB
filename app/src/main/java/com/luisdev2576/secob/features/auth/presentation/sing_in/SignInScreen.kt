package com.luisdev2576.secob.features.auth.presentation.sing_in

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.luisdev2576.secob.features.auth.presentation.sign_in.SignInViewModel
import com.luisdev2576.secob.R

@Composable
fun SignInScreen(
    activity: Activity,
    viewModel: SignInViewModel,
    onSignInSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(state.isSignInSuccessful) {
        if (state.isSignInSuccessful) {
            Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
            viewModel.resetState()
            onSignInSuccess()
        }
    }

    LaunchedEffect(state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ElevatedButton (
            onClick = { viewModel.onSignInClick(activity) },
            enabled = !state.isSignInLoading,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            when {
                state.isSignInLoading -> SignInLoadingContent()
                state.isSignInSuccessful -> SignInSuccessContent()
                else -> SignInButtonContent()
            }
        }
    }
}

@Composable
fun SignInLoadingContent() {
    CircularProgressIndicator()
}

@Composable
fun SignInSuccessContent() {
    Icon(
        imageVector = Icons.Filled.Check,
        contentDescription = "Check",
        tint = MaterialTheme.colorScheme.primary
    )
}
@Composable
fun SignInButtonContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = R.drawable.google__g__logo,
            contentDescription = "Google logo",
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Iniciar sesión con Google", color = MaterialTheme.colorScheme.onBackground)
    }
}