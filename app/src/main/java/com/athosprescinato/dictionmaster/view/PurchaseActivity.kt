package com.athosprescinato.dictionmaster.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.constants.DictionConstants
import com.athosprescinato.dictionmaster.service.repository.local.DictionMasterPreferences
import kotlinx.android.synthetic.main.activity_purchase.*
import kotlinx.android.synthetic.main.activity_search.*

class PurchaseActivity : AppCompatActivity() {

    private lateinit var mSharedPreferences: DictionMasterPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        btSubscribe.setOnClickListener {
            mSharedPreferences.store(DictionConstants.SHARED.LIMIT_KEY, 0)
            Toast.makeText(this, "App de Demonstração - Resetando o limite de pesquisa por dia!", Toast.LENGTH_SHORT).show()
            Log.i("[x] App de demostração", "Resetando o limite de pesquisa diário")
            finish()
        }
    }
}