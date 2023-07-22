package team.pompom.mukmap.di.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.pompom.mukmap.network.HeaderApiKeyInterceptor
import team.pompom.mukmap.remote.BuildConfig
import javax.inject.Singleton

/**
 * Retrofit 객체 관리 모듈
 */
@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun provideDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor())
            addInterceptor(HeaderApiKeyInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideDefaultRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build()
    }
}