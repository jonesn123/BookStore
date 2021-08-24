package com.john.bookstore.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.john.bookstore.data.BooksRepository
import com.john.bookstore.data.local.BooksDao
import com.john.bookstore.data.remote.WebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val API_VERSION = "1.0"
    private const val API_DOMAIN = "https://api.itbook.store/$API_VERSION/"

    @Provides
    fun provideGSONConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor();
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_DOMAIN)
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    @Provides
    fun provideWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    fun provideRepository(dao: BooksDao, webService: WebService): BooksRepository =
        BooksRepository(dao, webService)
}