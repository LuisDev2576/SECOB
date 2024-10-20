package com.luisdev2576.secob.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisdev2576.secob.features.auth.presentation.biometric_auth.BiometricAuthenticationScreen
import com.luisdev2576.secob.features.auth.presentation.profile.ProfileScreen
import com.luisdev2576.secob.features.auth.presentation.profile.ProfileViewModel
import com.luisdev2576.secob.features.auth.presentation.sing_in.SignInScreen
import com.luisdev2576.secob.features.auth.presentation.sign_in.SignInViewModel
import com.luisdev2576.secob.features.auth.presentation.splash_screen.SplashScreen
import com.luisdev2576.secob.features.auth.presentation.splash_screen.SplashViewModel

@Composable
fun Navigation() {
    val navHostController = rememberNavController()
    val activity = LocalContext.current as Activity

    NavHost(
        navController = navHostController,
        startDestination = SplashDestination
    ) {
        composable<SplashDestination> {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                viewModel = splashViewModel,
                userSingedIn = {
                    navHostController.navigate(BiometricsAuthenticationDestination){
                        popUpTo(SplashDestination) {
                            inclusive = true
                        }
                    }
                },
                userNotSingedIn = {
                    navHostController.navigate(SignInDestination) {
                        popUpTo(SplashDestination) {
                            inclusive = true
                        }
                    }
                }
            )
            BackHandler(enabled = true) {

            }
        }

        composable<SignInDestination> {
            val signInViewModel: SignInViewModel = hiltViewModel()
            SignInScreen(
                activity = activity,
                viewModel = signInViewModel,
                onSignInSuccess = {
                    navHostController.navigate(BiometricsAuthenticationDestination) {
                        popUpTo(SignInDestination) {
                            inclusive = true
                        }
                    }
                }
            )
            BackHandler(enabled = true) {

            }
        }

        composable<BiometricsAuthenticationDestination> {
//            BiometricAuthenticationScreen(
//
//            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("autenticación biométrica")
            }
            BackHandler(enabled = true) {
                // Evitar que el usuario vuelva atrás sin autenticarse
            }
        }

        composable<HomeDestination> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Home")
            }
        }

        composable<ProfileDestination> {

        }
    }
}
