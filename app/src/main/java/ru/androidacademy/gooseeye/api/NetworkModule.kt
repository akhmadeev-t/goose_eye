package ru.androidacademy.gooseeye.api

object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "48bdbc8f69ede1803318aa3563d0829c"
    private const val API_KEY_QUERY = "api_key"
    private const val IMAGE_URL = "https://image.tmdb.org/t/p/original"

    fun getApiKey() : String = API_KEY

    fun getBaseUrl() : String = BASE_URL

    fun getApiKeyQuery() : String = API_KEY_QUERY

    fun createImageUrl(path: String) : String = IMAGE_URL + path
}