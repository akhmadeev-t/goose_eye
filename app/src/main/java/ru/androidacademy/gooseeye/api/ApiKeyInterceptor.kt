package ru.androidacademy.gooseeye.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter(NetworkModule.getApiKeyQuery(), NetworkModule.getApiKey())
            .build()

        val request = originalRequest.newBuilder()
            .url(originalHttpUrl)
            .build()

        return chain.proceed(request)
    }

}