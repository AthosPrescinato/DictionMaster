package com.athosprescinato.dictionmaster.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.constants.DictionConstants
import com.athosprescinato.dictionmaster.service.repository.local.DictionMasterPreferences
import kotlinx.android.synthetic.main.activity_purchase.*

class PurchaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        btSubscribe.setOnClickListener {
            DictionMasterPreferences(baseContext).store(DictionConstants.SHARED.LIMIT_KEY, 0)
            Log.i("[x] App de demostração", "Resetando o limite de pesquisa diário")
            finish()
        }
    }
}