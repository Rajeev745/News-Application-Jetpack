package com.example.insightfeed.di

import android.app.Application
import com.example.insightfeed.data.manager.LocalUserManagerRepositoryImpl
import com.example.insightfeed.data.remote.api.NewsApi
import com.example.insightfeed.data.repository.NewsRepositoryImpl
import com.example.insightfeed.domain.manager.LocalUserManagerRepository
import com.example.insightfeed.domain.repository.NewsRepository
import com.example.insightfeed.domain.usecase.app_entry.AppEntryUseCase
import com.example.insightfeed.domain.usecase.app_entry.ReadAppEntryUseCase
import com.example.insightfeed.domain.usecase.app_entry.SaveAppEntryUseCase
import com.example.insightfeed.domain.usecase.news.GetNewsUseCase
import com.example.insightfeed.domain.usecase.news.NewsUseCase
import com.example.insightfeed.domain.usecase.news.SearchNewsUseCase
import com.example.insightfeed.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase {
        return NewsUseCase(getNewsUseCase = GetNewsUseCase(newsRepository), getSearchNewsUseCase = SearchNewsUseCase(newsRepository))
    }
}