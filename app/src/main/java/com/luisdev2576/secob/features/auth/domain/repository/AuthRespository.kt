package com.luisdev2576.secob.features.auth.domain.repository

import android.app.Activity
import com.luisdev2576.secob.features.auth.domain.model.SignInResult
import com.luisdev2576.secob.features.auth.domain.model.UserData

interface AuthRepository {
    suspend fun signIn(activity: Activity): SignInResult
    suspend fun signOut(activity: Activity)
    fun getSignedInUser(): UserData?
}