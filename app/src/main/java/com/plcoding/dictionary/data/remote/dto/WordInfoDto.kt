package com.plcoding.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.plcoding.dictionary.data.local.entity.WordInfoEntity
import com.plcoding.dictionary.domain.model.Definition
import com.plcoding.dictionary.domain.model.WordInfo

data class WordInfoDto(
    @SerializedName("meanings")
    val meanings: List<MeaningDto>,
    @SerializedName("origin")
    val origin: String = "",
    @SerializedName("phonetic")
    val phonetic: String = "",
    @SerializedName("phonetics")
    val phoneticDtos: List<PhoneticDto> = listOf(),
    @SerializedName("word")
    val word: String = ""
){
    fun toWordInfo():WordInfo{
        return WordInfo(
            meanings = meanings.map{it.toMeaning()},
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }fun toWordInfoEntiy():WordInfoEntity{
        return WordInfoEntity(
            meanings = meanings.map{it.toMeaning()},
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}