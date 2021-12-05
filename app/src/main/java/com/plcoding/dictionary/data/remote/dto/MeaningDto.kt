package com.plcoding.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.plcoding.dictionary.domain.model.Meaning

data class MeaningDto(
    @SerializedName("definitions")
    val definitions: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String = ""
) {
    fun toMeaning(): Meaning {

        return Meaning(
            definitions = definitions.map{it.toDefinition()},
            partOfSpeech = partOfSpeech
        )
    }
}