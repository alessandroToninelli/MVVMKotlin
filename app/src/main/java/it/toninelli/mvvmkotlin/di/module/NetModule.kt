package it.toninelli.mvvmkotlin.di.module

import dagger.Module
import dagger.Provides
import it.toninelli.mvvmkotlin.util.BASE_URL
import it.toninelli.mvvmkotlin.di.interfaces.ApplicationScope
import it.toninelli.mvvmkotlin.retrofit.ApiService
import it.toninelli.mvvmkotlin.util.BASE_URL_POST
import it.toninelli.mvvmkotlin.util.BASE_URL_USER
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


@Module
class NetModule{


    @ApplicationScope
    @Provides
    @Named("api_post")
    fun provideApiServicePost(httpClient: OkHttpClient):ApiService{
        return Retrofit.Builder()
                .baseUrl(BASE_URL_POST)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    @ApplicationScope
    @Provides
    @Named("api_user")
    fun provideApiServiceUser(httpClient: OkHttpClient):ApiService{
        return Retrofit.Builder()
                .baseUrl(BASE_URL_USER)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }


    @ApplicationScope
    @Provides
    fun getOkHttpClientRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }



    @ApplicationScope
    @Provides
    fun getOkHttpLogginInterceptors(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return httpLoggingInterceptor
    }


}