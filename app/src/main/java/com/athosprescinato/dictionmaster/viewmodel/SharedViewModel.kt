package com.athosprescinato.dictionmaster.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athosprescinato.dictionmaster.service.constants.DictionConstants
import com.athosprescinato.dictionmaster.service.listener.APIListener
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import com.athosprescinato.dictionmaster.service.repository.WordResultRepository
import com.athosprescinato.dictionmaster.service.repository.local.DictionMasterPreferences
import com.athosprescinato.dictionmaster.view.ResultActivity

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val mWordResultRepository = WordResultRepository(application)
    private val mSharedPreferences = DictionMasterPreferences(application)

    private val mSearchWord = MutableLiveData<WordResultModel>()
    var searchWord: LiveData<WordResultModel> = mSearchWord


    fun doSearchWord(word: String) {
        if( mSharedPreferences.get(DictionConstants.SHARED.LIMIT_KEY) < 2 ) {
            mWordResultRepository.searchWord(
                word,
                DictionConstants.LANGUAGE.CURRENT_LANGUAGE,
                object : APIListener<WordResultModel> {
                    override fun onSuccess(model: WordResultModel) {
                        mSearchWord.value = model
                        DictionConstants.SHARED.SEARCHTIMES++
                        mSharedPreferences.store(
                            DictionConstants.SHARED.LIMIT_KEY,
                            DictionConstants.SHARED.SEARCHTIMES
                        )
                    }

                    override fun onFailure(str: String) {
                    //TODO Implementar alerta visual de erro ao usuario

                    }

                })
        }

        // TODO Implementar Tela de Compra
        Log.i("[x] Limite Atingido", mSharedPreferences.get(DictionConstants.SHARED.LIMIT_KEY).toString())


    }

}