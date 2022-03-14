package com.athosprescinato.dictionmaster.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.constants.DictionConstants
import com.athosprescinato.dictionmaster.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        setListeners()
        observe()

        val editSearchWord = findViewById<EditText>(R.id.editSearchWord)
        val btSearch = findViewById<Button>(R.id.btSearch)

        var languageSelected = DictionConstants.LANGUAGE.ENGLISH

        val btnSelectLanguage = findViewById<Button>(R.id.btLanguage)

        btnSelectLanguage.setOnClickListener {

            //Inflate the dialog as custom view
            val messageBoxView =
                LayoutInflater.from(this).inflate(R.layout.custom_alertdialog, null)

            //AlertDialogBuilder
            val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)

            //show dialog
            val messageBoxInstance = messageBoxBuilder.show()

            val imbtnEnglishLanguage = messageBoxView.findViewById<ImageButton>(R.id.imbtnEnglishLanguage)
            val imbtnFranchLanguage = messageBoxView.findViewById<ImageButton>(R.id.imbtnFranchLanguage)
            val imbtnSpanishLanguage = messageBoxView.findViewById<ImageButton>(R.id.imbtnSpanishLanguage)

            //set Listener

            imbtnEnglishLanguage.setOnClickListener {
                languageSelected = DictionConstants.LANGUAGE.ENGLISH
                btnSelectLanguage.text = "ENGLISH"
                btnSelectLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_en, 0, 0, 0)
                Toast.makeText(this, "Selected English", Toast.LENGTH_SHORT).show()
                messageBoxInstance.dismiss()
            }
            imbtnFranchLanguage.setOnClickListener {
                languageSelected = DictionConstants.LANGUAGE.FRANCH
                btnSelectLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fr, 0, 0, 0)
                btnSelectLanguage.text = "FRANCH"

                Toast.makeText(this, "Selected Franch", Toast.LENGTH_SHORT).show()
                messageBoxInstance.dismiss()
            }
            imbtnSpanishLanguage.setOnClickListener {
                languageSelected = DictionConstants.LANGUAGE.SPANISH
                btnSelectLanguage.text = "SPANISH"
                btnSelectLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_es, 0, 0, 0)
                Toast.makeText(this, "Selected Spanish", Toast.LENGTH_SHORT).show()
                messageBoxInstance.dismiss()
            }
        }

    }

    private fun setListeners() {
        btSearch.setOnClickListener(this)
        btLanguage.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btSearch ){
            doSearchWord()
        }
    }

    private fun doSearchWord() {
        val word = editSearchWord.text.toString()
        mViewModel.doSearchWord(word, "en-us")
    }

    private fun observe(){
        mViewModel.searchWord.observe(this, Observer {
            if(it != null){
                val definition =
                    "1) [uncountable, countable] a process of teaching, training and learning, especially in schools, colleges or universities, to improve knowledge and develop skills"
                val intent = Intent(this@SearchActivity, ResultActivity::class.java)
                intent.putExtra("id", it.id)
                intent.putExtra("pronunciation", it.id)
                intent.putExtra("definition", definition)
                startActivity(intent)

            }
        })

    }


}

