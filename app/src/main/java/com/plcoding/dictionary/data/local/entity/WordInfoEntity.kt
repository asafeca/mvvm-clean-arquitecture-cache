package com.plcoding.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.dictionary.domain.model.Meaning
import com.plcoding.dictionary.domain.model.WordInfo

@Entity(tableName = "wordinfosentity")
data class WordInfoEntity(
    val word: String,
    val phonetic: String?=null,
    val origin: String?=null,
    val meanings: List<Meaning>,
    @PrimaryKey val id: Int? = null
){
    fun toWordInfo(): WordInfo {
        return  WordInfo(
            word = word,
            phonetic = phonetic,
            origin = origin,
            meanings = meanings.map{it.toMeaning()}
        )
    }

}
