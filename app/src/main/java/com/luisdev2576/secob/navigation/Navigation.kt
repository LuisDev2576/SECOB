package com.luisdev2576.secob.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisdev2576.secob.features.auth.presentation.profile.ProfileScreen
import com.luisdev2576.secob.features.auth.presentation.profile.ProfileViewModel
import com.luisdev2576.secob.features.auth.presentation.sign_in.SignInScreen
import com.luisdev2576.secob.features.auth.presentation.sign_in.SignInViewModel

@Composable
fun Navigation() {
    val navHostController = rememberNavController()
    val activity = LocalContext.current as Activity

    NavHost(
        navController = navHostController,
        startDestination = SignInDestination
    ) {
        composable<SignInDestination> {
            val signInViewModel: SignInViewModel = hiltViewModel()
            SignInScreen(
                activity = activity,
                viewModel = signInViewModel,
                onSignInSuccess = {
                    navHostController.navigate(ProfileDestination)
                }
            )
        }

        composable<ProfileDestination> { backStackEntry ->
            val profileViewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                activity = activity,
                viewModel = profileViewModel,
                onSignOutSuccess = {
                    navHostController.navigate(SignInDestination)
                }
            )
        }
    }
}
