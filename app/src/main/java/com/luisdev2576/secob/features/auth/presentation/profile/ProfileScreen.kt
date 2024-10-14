package com.luisdev2576.secob.features.auth.presentation.profile

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.luisdev2576.secob.features.auth.domain.model.UserData

@Composable
fun ProfileScreen(
    activity: Activity,
    viewModel: ProfileViewModel,
    onSignOutSuccess: () -> Unit
) {
    val context = LocalContext.current
    val userData = viewModel.userData

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        userData?.profilePictureUrl?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        userData?.username?.let { name ->
            Text(
                text = name,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(onClick = {
            viewModel.onSignOutClick(activity) {
                Toast.makeText(context, "Signed out", Toast.LENGTH_LONG).show()
                onSignOutSuccess()
            }
        }) {
            Text(text = "Sign out")
        }
    }
}