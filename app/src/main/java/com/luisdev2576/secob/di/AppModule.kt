
package com.luisdev2576.secob.di

import android.app.Application
import com.luisdev2576.secob.features.auth.data.repository.AuthRepositoryImpl
import com.luisdev2576.secob.features.auth.domain.repository.AuthRepository
import com.luisdev2576.secob.features.auth.domain.use_case.GetSignedInUser
import com.luisdev2576.secob.features.auth.domain.use_case.SignInUseCase
import com.luisdev2576.secob.features.auth.domain.use_case.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        application: Application
    ): AuthRepository {
        return AuthRepositoryImpl(application)
    }

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(authRepository: AuthRepository): SignOutUseCase {
        return SignOutUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetSignedInUserUseCase(authRepository: AuthRepository): GetSignedInUser {
        return GetSignedInUser(authRepository)
    }
}
