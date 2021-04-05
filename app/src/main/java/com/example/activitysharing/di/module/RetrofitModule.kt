package com.example.activitysharing.di.module

import com.example.activitysharing.data.network.EventAPI
import com.example.activitysharing.data.network.EventService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesEventApi(retrofit: Retrofit): EventAPI {
        return retrofit.create(EventAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesEventService(eventAPI: EventAPI): EventService {
        return EventService(eventAPI)
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://c4e836d5-1544-4b52-9c15-35683f09a91b.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}