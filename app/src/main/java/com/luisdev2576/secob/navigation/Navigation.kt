package com.luisdev2576.secob.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luisdev2576.secob.features.auth.presentation.biometric_auth.BiometricAuthViewModel
import com.luisdev2576.secob.features.auth.presentation.biometric_auth.BiometricAuthenticationScreen
import com.luisdev2576.secob.features.auth.presentation.sign_in.SignInViewModel
import com.luisdev2576.secob.features.auth.presentation.sing_in.SignInScreen
import com.luisdev2576.secob.features.auth.presentation.splash_screen.SplashScreen
import com.luisdev2576.secob.features.auth.presentation.splash_screen.SplashViewModel
import com.luisdev2576.secob.features.denuncias.presentation.new_denuncia.NewDenunciaScreen
import com.luisdev2576.secob.features.lideres.presentation.new_lider.NewLiderScreen
import com.luisdev2576.secob.utils.BiometricPromptManager

@Composable
fun Navigation(
    promptManager: BiometricPromptManager,
    navHostController: NavHostController
) {
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
            val biometricAuthViewModel: BiometricAuthViewModel = hiltViewModel()
            BiometricAuthenticationScreen(
                activity = activity,
                promptManager = promptManager,
                onBiometricAuthenticationSuccess = {
                    navHostController.navigate(LideresHomeDestination){
                        popUpTo(BiometricsAuthenticationDestination) {
                            inclusive = true
                        }
                    }
                },
                viewModel = biometricAuthViewModel,
                onSignOut = {
                    navHostController.navigate(SignInDestination) {
                        popUpTo(BiometricsAuthenticationDestination) {
                            inclusive = true
                        }
                    }
                }
            )
            BackHandler(enabled = true) {
                // Evitar que el usuario vuelva atrás sin autenticarse
            }
        }

        composable<LideresHomeDestination> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                Text("Lideres")
            }
            BackHandler(enabled = true) {
                // Evitar que el usuario vuelva atrás sin autenticarse
            }
        }

        composable<DenunciasHomeDestination> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                Text("Denuncias")
            }
            BackHandler(enabled = true) {
                // Evitar que el usuario vuelva atrás sin autenticarse
            }
        }

        composable<NewDenunciaDestination> {
            NewDenunciaScreen()
            BackHandler(enabled = true) {
                navHostController.navigate(DenunciasHomeDestination)
            }
        }
        composable<NewLiderDestination> {
            NewLiderScreen()
            BackHandler(enabled = true) {
                navHostController.navigate(LideresHomeDestination)
            }
        }

        composable<ProfileDestination> {

        }
    }
}
