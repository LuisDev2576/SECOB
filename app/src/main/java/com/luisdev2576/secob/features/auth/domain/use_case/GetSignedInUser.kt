package com.luisdev2576.secob.features.auth.domain.use_case

import com.luisdev2576.secob.features.auth.domain.model.UserData
import com.luisdev2576.secob.features.auth.domain.repository.AuthRepository

class GetSignedInUser (
    private val repository: AuthRepository
) {
    operator fun invoke(): UserData? {
        return repository.getSignedInUser()
    }
}