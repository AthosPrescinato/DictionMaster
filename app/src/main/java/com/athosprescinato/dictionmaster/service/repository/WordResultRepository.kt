package com.athosprescinato.dictionmaster.service.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.listener.APIListener
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import com.athosprescinato.dictionmaster.service.repository.remote.RetrofitClient
import com.athosprescinato.dictionmaster.service.repository.remote.WordService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordResultRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(WordService::class.java)

    fun searchWord(word: String, language: String, listener: APIListener<WordResultModel>) {
        val call: Call<WordResultModel> = mRemote.searchWord(word, language)

        call.enqueue(object : Callback<WordResultModel> {
            override fun onResponse(
                call: Call<WordResultModel>,
                response: Response<WordResultModel>
            ) {
                Log.i("[x] Search Activity", response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(context, "Sem sucesso - Tente novamente", Toast.LENGTH_SHORT)
                        .show()

                    Log.i("Error", response.toString())
                    return
                }
                response.body()?.let { listener.onSuccess(it) }

            }

            override fun onFailure(call: Call<WordResultModel>, t: Throwable) {
                Toast.makeText(
                    context,
                    context.getString(R.string.ERROR_UNEXPECTED),
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.i("[x] Search Activity", t.message ?: "Null message")
            }

        })

    }

}