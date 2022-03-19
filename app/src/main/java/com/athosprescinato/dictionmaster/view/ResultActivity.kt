package com.athosprescinato.dictionmaster.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setListeners()

        val bodyResponse = intent.extras?.get("bodyResponse") as WordResultModel


//        textSearchWord.text = getExtraId
//        textPronunciation.text = getPronunciation
//
//        textMeaning_1.text = getdefinition
//        textMeaning_2.text = getdefinition2
//        textMeaning_3.text = getdefinition3
//

        textDetails_1.text = bodyResponse.id

//        textDetails_2.text = getdetails2
//        textDetails_3.text = getdetails3

    }

    private fun setListeners() {
        btNewSearch.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btNewSearch) {
            finish()
        }
    }
}