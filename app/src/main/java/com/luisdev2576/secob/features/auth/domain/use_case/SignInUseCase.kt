package com.luisdev2576.secob.features.auth.domain.use_case

import android.app.Activity
import com.luisdev2576.secob.features.auth.domain.model.SignInResult
import com.luisdev2576.secob.features.auth.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(activity: Activity): SignInResult {
        return repository.signIn(activity)
    }
}