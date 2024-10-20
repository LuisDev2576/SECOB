package com.luisdev2576.secob.features.auth.presentation.splash_screen

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev2576.secob.features.auth.domain.use_case.SignInUseCase
import com.luisdev2576.secob.features.auth.domain.model.SignInResult
import com.luisdev2576.secob.features.auth.domain.use_case.GetSignedInUser
import com.luisdev2576.secob.features.auth.presentation.sing_in.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val getSignedInUserUseCase: GetSignedInUser
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInClick(activity: Activity) {
        viewModelScope.launch {
            onSignInLoading()
            val result = signInUseCase(activity)
            onSignInResult(result)
        }
    }

    private fun onSignInLoading() {
        _state.update {
            SignInState(
                isSignInLoading = true
            )
        }
    }

    private fun onSignInResult(result: SignInResult) {
        _state.update {
            SignInState(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    init {
        viewModelScope.launch {
            val userData = getSignedInUserUseCase()
            if (userData != null) {
                _state.update {
                    SignInState(
                        isSignInSuccessful = true
                    )
                }
            }
        }
    }
}