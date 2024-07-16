package com.maher.ad_sdk.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private const val BASE_URL = "https://voodoo-adn-framework.s3.eu-west-1.amazonaws.com/test/"

    val retrofit: Retrofit = provideRetrofit(provideOkHttpClient())

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().build()

}
