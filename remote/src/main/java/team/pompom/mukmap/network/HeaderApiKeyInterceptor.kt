package team.pompom.mukmap.network

import okhttp3.Interceptor
import okhttp3.Response
import team.pompom.mukmap.remote.BuildConfig

object HeaderApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(BuildConfig.API_KEY_HEADER, BuildConfig.API_KEY_VALUE)
            .build()
        return chain.proceed(request)
    }
}