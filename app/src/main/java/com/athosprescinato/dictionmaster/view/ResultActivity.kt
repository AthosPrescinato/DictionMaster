package com.athosprescinato.dictionmaster.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.athosprescinato.dictionmaster.R
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_search.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setListeners()

        val getExtraId = intent.getStringExtra("id")
        val getPronunciation = intent.getStringExtra("pronunciation")

        val getdefinition = intent.getStringExtra("definition")
        val getdefinition2 = intent.getStringExtra("definition")
        val getdefinition3 = intent.getStringExtra("definition")

        val getdetails = intent.getStringExtra("details")
        val getdetails2 = intent.getStringExtra("details")
        val getdetails3 = intent.getStringExtra("details")


        textSearchWord.text = getExtraId
        textPronunciation.text = getPronunciation

        textMeaning_1.text = getdefinition
        textMeaning_2.text = getdefinition2
        textMeaning_3.text = getdefinition3

        textDetails_1.text = getdetails
        textDetails_2.text = getdetails2
        textDetails_3.text = getdetails3

    }

    private fun setListeners() {
        btNewSearch.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btNewSearch) {
            val intent = Intent(this@ResultActivity, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}