package com.athosprescinato.dictionmaster.service.repository.local

import android.content.Context
import android.content.SharedPreferences

class DictionMasterPreferences(context: Context) {

    private val mPreferences: SharedPreferences =
        context.getSharedPreferences("dictionShared", Context.MODE_PRIVATE)


    fun store(key: String, searchLimit: Int) {
        mPreferences.edit().putInt(key, searchLimit).apply()
    }

    fun get(key: String): Int{
        return mPreferences.getInt(key, 0)

    }

    fun getTimeShared(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

    fun storeTime(key: String, date: String) {
        mPreferences.edit().putString(key, date).apply()
    }


}