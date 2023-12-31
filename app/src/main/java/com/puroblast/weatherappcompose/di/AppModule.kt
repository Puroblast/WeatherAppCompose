package com.puroblast.weatherappcompose.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.puroblast.weatherappcompose.network.WeatherApi
import com.puroblast.weatherappcompose.utils.BASE_URL
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
    fun provideWeatherApi(): WeatherApi {

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd")
            .create()

        val api: WeatherApi by lazy {
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WeatherApi::class.java)
        }

        return api

    }

}