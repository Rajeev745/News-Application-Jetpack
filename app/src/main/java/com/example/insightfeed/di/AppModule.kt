package com.example.insightfeed.di

import android.app.Application
import com.example.insightfeed.data.manager.LocalUserManagerRepositoryImpl
import com.example.insightfeed.domain.manager.LocalUserManagerRepository
import com.example.insightfeed.domain.usecase.AppEntryUseCase
import com.example.insightfeed.domain.usecase.ReadAppEntryUseCase
import com.example.insightfeed.domain.usecase.SaveAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManagerRepository(application: Application): LocalUserManagerRepository {
        return LocalUserManagerRepositoryImpl(context = application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCase(localUserManagerRepository: LocalUserManagerRepository): AppEntryUseCase {
        return AppEntryUseCase(
            ReadAppEntryUseCase(localUserManagerRepository = localUserManagerRepository),
            SaveAppEntryUseCase(localUserManagerRepository = localUserManagerRepository)
        )
    }
}