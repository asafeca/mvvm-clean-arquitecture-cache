package com.plcoding.dictionary.data.remote

import com.plcoding.dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DIctionaryApi {
@GET("/api/v2/entries/en/{word}")
suspend fun getWordInfo(@Path("word") word:String):List<WordInfoDto>

companion object {
   const val BASE_URL = "https://api.dictionaryapi.dev/"

 //  https://api.dictionaryapi.dev/api/v2/entries/en
}

}