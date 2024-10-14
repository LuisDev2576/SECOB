package com.luisdev2576.secob.features.auth.domain.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)