package com.plcoding.dictionary.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.plcoding.dictionary.domain.model.Definition

data class DefinitionDto(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definition")
    val definition: String = "",
    @SerializedName("example")
    val example: String = "",
    @SerializedName("synonyms")
    val synonyms: List<String> = listOf()
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms

        )

    }
}