package com.luisdev2576.secob.features.auth.presentation.biometric_auth

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev2576.secob.features.auth.domain.use_case.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiometricAuthViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    fun onSignOut(activity: Activity, onSignOutSuccess: () -> Unit) {
        viewModelScope.launch {
            signOutUseCase(activity)
            onSignOutSuccess()
        }
    }
}
