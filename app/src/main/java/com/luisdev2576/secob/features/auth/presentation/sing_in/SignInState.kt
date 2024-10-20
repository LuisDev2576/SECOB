package com.luisdev2576.secob.features.auth.presentation.sing_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isSignInLoading: Boolean = false
)
