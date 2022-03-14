package com.athosprescinato.dictionmaster.service.model

data class WordResultModel(
    val id: String,
    //val metadata: Metadata,
    //val results: List<Result>,
    val word: String
) {
    data class Metadata(
        val operation: String,
        val provider: String,
        val schema: String
    )

    data class Result(

        val id: String,
        val language: String,
        val lexicalEntries: List<LexicalEntry>,
        val type: String,
        val word: String
    )

    data class LexicalEntry(
        val entries: List<Entry>,
        val language: String,
        val lexicalCategory: LexicalCategory,
        val text: String
    )

    data class Entry(
        val pronunciations: List<Pronunciation>,
        val senses: List<Sense>
    )

    data class LexicalCategory(
        val id: String,
        val text: String
    )

    data class Pronunciation(
        //val audioFile: String,
        val dialects: List<String>,
        val phoneticNotation: String,
        val phoneticSpelling: String
    )

    data class Sense(
        val definitions: List<String>,
        val id: String,
        //val subsenses: List<Subsense>
    )

    data class Subsense(
        val definitions: List<String>,
        val id: String
    )

}