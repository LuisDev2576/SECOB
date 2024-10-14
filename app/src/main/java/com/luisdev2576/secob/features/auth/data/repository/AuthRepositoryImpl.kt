// features/auth/data/repository/AuthRepositoryImpl.kt

package com.luisdev2576.secob.features.auth.data.repository

import android.app.Activity
import android.content.Context
import com.luisdev2576.secob.features.auth.domain.model.SignInResult
import com.luisdev2576.secob.features.auth.domain.model.UserData
import com.luisdev2576.secob.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.CancellationException


class AuthRepositoryImpl(
    context: Context
) : AuthRepository {

    private val googleAuthUiClient = GoogleAuthUiClient(context)

    override suspend fun signIn(activity: Activity): SignInResult {
        return try {
            googleAuthUiClient.signIn(activity)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            SignInResult(null, e.localizedMessage)
        }
    }

    override suspend fun signOut(activity: Activity) {
        googleAuthUiClient.signOut(activity)
    }

    override fun getSignedInUser(): UserData? {
        return googleAuthUiClient.getSignedInUser()
    }
}
