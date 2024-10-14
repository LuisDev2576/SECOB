package com.luisdev2576.secob.features.auth.data.repository

import android.app.Activity
import android.content.Context
import androidx.credentials.*
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.luisdev2576.secob.R
import com.luisdev2576.secob.features.auth.domain.model.SignInResult
import com.luisdev2576.secob.features.auth.domain.model.UserData
import kotlinx.coroutines.tasks.await
import java.security.SecureRandom
import java.util.Base64
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(
    private val context: Context
) {
    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.create(context)
    private val nonce: String = generateNonce()

    suspend fun signIn(activity: Activity): SignInResult {
        // Primera solicitud: intentar obtener credenciales existentes
        val googleIdOptionAuthorized = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setAutoSelectEnabled(true)
            .setNonce(nonce)
            .build()

        val requestAuthorized = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOptionAuthorized)
            .build()

        return try {
            val result = credentialManager.getCredential(activity, requestAuthorized)
            handleSignInResult(result)
        } catch (e: GetCredentialException) {
            if (e is NoCredentialException) {
                // No hay credenciales existentes, intentar sin filtrar cuentas
                signInWithoutFiltering(activity)
            } else {
                e.printStackTrace()
                if (e is CancellationException) throw e
                SignInResult(
                    data = null,
                    errorMessage = e.localizedMessage
                )
            }
        }
    }

    private suspend fun signInWithoutFiltering(activity: Activity): SignInResult {
        val googleIdOptionUnfiltered = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setAutoSelectEnabled(false)
            .setNonce(nonce)
            .build()

        val requestUnfiltered = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOptionUnfiltered)
            .build()

        return try {
            val result = credentialManager.getCredential(activity, requestUnfiltered)
            handleSignInResult(result)
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.localizedMessage
            )
        }
    }

    private suspend fun handleSignInResult(result: GetCredentialResponse): SignInResult {
        val credential = result.credential

        return when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        val idToken = googleIdTokenCredential.idToken

                        val googleCredentials = GoogleAuthProvider.getCredential(idToken, null)
                        val user = auth.signInWithCredential(googleCredentials).await().user
                        SignInResult(
                            data = user?.run {
                                UserData(
                                    userId = uid,
                                    username = displayName,
                                    profilePictureUrl = photoUrl?.toString()
                                )
                            },
                            errorMessage = null
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                        if (e is CancellationException) throw e
                        SignInResult(
                            data = null,
                            errorMessage = e.localizedMessage
                        )
                    }
                } else {
                    SignInResult(
                        data = null,
                        errorMessage = "Tipo de credencial inesperado"
                    )
                }
            }
            else -> {
                SignInResult(
                    data = null,
                    errorMessage = "Tipo de credencial inesperado"
                )
            }
        }
    }

    suspend fun signOut(activity: Activity) {
        try {
            // Crea una instancia de ClearCredentialStateRequest
            val clearCredentialStateRequest = ClearCredentialStateRequest()

            // Pasa la solicitud al método clearCredentialState
            credentialManager.clearCredentialState(clearCredentialStateRequest)

            // Luego realiza el signOut de Firebase
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }
}


fun generateNonce(length: Int = 16): String {
    val random = SecureRandom()
    val bytes = ByteArray(length)
    random.nextBytes(bytes)
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
}