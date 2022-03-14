package com.athosprescinato.dictionmaster.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athosprescinato.dictionmaster.service.listener.APIListener
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import com.athosprescinato.dictionmaster.service.repository.WordResultRepository

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val mWordResultRepository = WordResultRepository(application)

    private val mSearchWord = MutableLiveData<WordResultModel>()
    var searchWord: LiveData<WordResultModel> = mSearchWord


    fun doSearchWord(word: String, language: String) {
        mWordResultRepository.searchWord(word, language, object: APIListener<WordResultModel> {
            override fun onSuccess(model: WordResultModel) {

               mSearchWord.value = model


            }

            override fun onFailure(str: String) {



            }

        })

    }
}