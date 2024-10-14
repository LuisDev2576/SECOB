package com.luisdev2576.secob.features.auth.presentation.profile

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev2576.secob.features.auth.domain.model.UserData
import com.luisdev2576.secob.features.auth.domain.use_case.SignOutUseCase
import com.luisdev2576.secob.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    val userData: UserData?
        get() = authRepository.getSignedInUser()

    fun onSignOutClick(activity: Activity, onSignOutSuccess: () -> Unit) {
        viewModelScope.launch {
            signOutUseCase(activity)
            onSignOutSuccess()
        }
    }
}