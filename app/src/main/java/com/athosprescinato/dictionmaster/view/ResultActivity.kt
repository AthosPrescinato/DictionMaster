package com.athosprescinato.dictionmaster.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.athosprescinato.dictionmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textSearchWord = findViewById<TextView>(R.id.textSearchWord)
        val textPronunciation = findViewById<TextView>(R.id.textPronunciation)
        val textMeaning_1 = findViewById<TextView>(R.id.textMeaning_1)
        val textMeaning_2 = findViewById<TextView>(R.id.textMeaning_2)
        val textMeaning_3 = findViewById<TextView>(R.id.textMeaning_3)
        val textDetails1 = findViewById<TextView>(R.id.textDetails_1)
        val textDetails2 = findViewById<TextView>(R.id.textDetails_2)
        val textDetails3 = findViewById<TextView>(R.id.textDetails_3)

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

        textDetails1.text = getdetails
        textDetails2.text = getdetails2
        textDetails3.text = getdetails3

    }
}