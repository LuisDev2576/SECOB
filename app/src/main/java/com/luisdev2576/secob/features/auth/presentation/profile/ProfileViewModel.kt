package com.luisdev2576.secob.features.auth.presentation.profile

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev2576.secob.features.auth.domain.model.UserData
import com.luisdev2576.secob.features.auth.domain.use_case.SignOutUseCase
import com.luisdev2576.secob.features.auth.domain.repository.AuthRepository
import com.luisdev2576.secob.features.auth.domain.use_case.GetSignedInUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getSignedInUserUseCase: GetSignedInUser
) : ViewModel() {

    val userData: UserData?
        get() = getSignedInUserUseCase()

    fun onSignOutClick(activity: Activity, onSignOutSuccess: () -> Unit) {
        viewModelScope.launch {
            signOutUseCase(activity)
            onSignOutSuccess()
        }
    }
}