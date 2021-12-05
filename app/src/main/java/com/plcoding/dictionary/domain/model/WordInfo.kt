package com.plcoding.dictionary.domain.model

import com.google.gson.annotations.SerializedName
import com.plcoding.dictionary.data.remote.dto.MeaningDto
import com.plcoding.dictionary.data.remote.dto.PhoneticDto

data class WordInfo(
    val meanings: List<Meaning>,
    val origin: String?=null,
    val phonetic: String?= null,
    val word: String = ""
)

