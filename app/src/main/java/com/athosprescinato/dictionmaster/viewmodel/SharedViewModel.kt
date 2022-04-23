package com.athosprescinato.dictionmaster.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.athosprescinato.dictionmaster.service.constants.DictionConstants
import com.athosprescinato.dictionmaster.service.listener.APIListener
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import com.athosprescinato.dictionmaster.service.repository.WordResultRepository
import com.athosprescinato.dictionmaster.service.repository.local.DictionMasterPreferences
import com.athosprescinato.dictionmaster.service.repository.local.ResultWordCache
import com.athosprescinato.dictionmaster.view.PurchaseActivity

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val mWordResultRepository = WordResultRepository(application)
    private val mSharedPreferences = DictionMasterPreferences(application)

    private val mSearchWord = MutableLiveData<WordResultModel>()
    var searchWord: LiveData<WordResultModel> = mSearchWord


    fun doSearchWord(word: String, context: Context) {

        val cachedWord = ResultWordCache.wordMap[word]
        if (cachedWord != null) {
            mSearchWord.value = cachedWord
            Log.i("[x] Cached Word", word)
            return
        }

        if (mSharedPreferences.get(DictionConstants.SHARED.LIMIT_KEY) < 2) {
            mWordResultRepository.searchWord(
                word,
                DictionConstants.LANGUAGE.CURRENT_LANGUAGE,
                object : APIListener<WordResultModel> {
                    override fun onSuccess(model: WordResultModel) {
                        mSearchWord.value = model
                        ResultWordCache.wordMap[word] = model
                        DictionConstants.SHARED.SEARCHTIMES++
                        mSharedPreferences.store(
                            DictionConstants.SHARED.LIMIT_KEY,
                            DictionConstants.SHARED.SEARCHTIMES
                        )
                    }

                    override fun onFailure(str: String) {

                    }

                })
        } else {
            Log.i(
                "[x] Limite Atingido",
                mSharedPreferences.get(DictionConstants.SHARED.LIMIT_KEY).toString()
            )
            val intent = Intent(context, PurchaseActivity::class.java)
            context.startActivity(intent)

        }

    }


}