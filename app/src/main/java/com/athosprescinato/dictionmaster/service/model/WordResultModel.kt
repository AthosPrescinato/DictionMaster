package com.athosprescinato.dictionmaster.service.model

import java.io.Serializable

data class WordResultModel(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>,
    val word: String

) : Serializable {

    data class Metadata(
        val operation: String,
        val provider: String,
        val schema: String
    ) : Serializable

    data class Result(

        val id: String,
        val language: String,
        val lexicalEntries: List<LexicalEntry>,
        val type: String,
        val word: String
    ) : Serializable

    data class LexicalEntry(
        val entries: List<Entry>,
        val language: String,
        val lexicalCategory: LexicalCategory,
        val text: String
    ) : Serializable

    data class Entry(
        val pronunciations: List<Pronunciation>? = null,
        val senses: List<Sense>
    ) : Serializable

    data class LexicalCategory(
        val id: String,
        val text: String
    ) : Serializable

    data class Pronunciation(
        val audioFile: String? = null,
        val dialects: List<String>,
        val phoneticNotation: String,
        val phoneticSpelling: String
    ) : Serializable

    data class Sense(
        val definitions: List<String>,
        //val id: String,
        val subsenses: List<Subsense>? = null
    ) : Serializable

    data class Subsense(
        val definitions: List<String>,
       // val id: String
    ) : Serializable

}