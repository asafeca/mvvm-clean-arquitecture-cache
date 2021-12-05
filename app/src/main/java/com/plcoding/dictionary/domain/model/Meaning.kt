package com.plcoding.dictionary.domain.model
import com.plcoding.dictionary.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String
)
{
    fun toMeaning():Meaning{
        return Meaning(
            definitions = definitions,
            partOfSpeech = partOfSpeech
        )
    }
}
