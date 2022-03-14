package com.athosprescinato.dictionmaster.service.repository.remote

import com.athosprescinato.dictionmaster.service.model.WordResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WordService {

    @GET("entries/{language}/{search_word}?fields=definitions%2Cpronunciations%2CvariantForms&strictMatch=false")
    fun searchWord(
        @Path("search_word") searchWord: String,
        @Path("language") language: String
    ): Call<WordResultModel>

}