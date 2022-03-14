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
import com.athosprescinato.dictionmaster.service.repository.local.DictionMasterPreferences
import com.athosprescinato.dictionmaster.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*


class SearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: SharedViewModel
    private lateinit var mSharedPreferences: DictionMasterPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        mViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        mSharedPreferences = DictionMasterPreferences(applicationContext)

        checkTimeToReset()
        setListeners()
        observe()
    }

    private fun setListeners() {
        btSearch.setOnClickListener(this)
        btLanguage.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btSearch) {
            doSearchWord()
        } else if (v.id == R.id.btLanguage) {
            callDialog()
        }
    }

    private fun callDialog() {

        btLanguage.setOnClickListener {
            val messageBoxView =
                LayoutInflater.from(this).inflate(R.layout.custom_alertdialog, null)

            val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)

            val messageBoxInstance = messageBoxBuilder.show()

            val imbtnEnglishLanguage =
                messageBoxView.findViewById<ImageButton>(R.id.imbtnEnglishLanguage)
            val imbtnFranchLanguage =
                messageBoxView.findViewById<ImageButton>(R.id.imbtnFranchLanguage)
            val imbtnSpanishLanguage =
                messageBoxView.findViewById<ImageButton>(R.id.imbtnSpanishLanguage)

            imbtnEnglishLanguage.setOnClickListener {
                DictionConstants.LANGUAGE.CURRENT_LANGUAGE = DictionConstants.LANGUAGE.ENGLISH
                btLanguage.text = DictionConstants.LANGUAGE.ENGLISH
                btLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_en, 0, 0, 0)
                Toast.makeText(this, "Selected English", Toast.LENGTH_SHORT).show()
                messageBoxInstance.dismiss()
            }
            imbtnFranchLanguage.setOnClickListener {
                DictionConstants.LANGUAGE.CURRENT_LANGUAGE = DictionConstants.LANGUAGE.FRANCH
                btLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fr, 0, 0, 0)
                btLanguage.text = DictionConstants.LANGUAGE.FRANCH

                Toast.makeText(this, "Selected Franch", Toast.LENGTH_SHORT).show()
                messageBoxInstance.dismiss()
            }
            imbtnSpanishLanguage.setOnClickListener {
                DictionConstants.LANGUAGE.CURRENT_LANGUAGE = DictionConstants.LANGUAGE.SPANISH
                btLanguage.text = DictionConstants.LANGUAGE.SPANISH
                btLanguage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_es, 0, 0, 0)
                Toast.makeText(this, "Selected Spanish", Toast.LENGTH_SHORT).show()
                messageBoxInstance.dismiss()
            }
        }
    }

    private fun doSearchWord() {
        val word = editSearchWord.text.toString()
        mViewModel.doSearchWord(word)
    }

    private fun observe() {
        mViewModel.searchWord.observe(this, Observer {
            if (it != null) {

                // TODO Passar dados corretos

                val definition =
                    "1) [uncountable, countable] a process of teaching, training and learning, especially in schools, colleges or universities, to improve knowledge and develop skills"
                val intent = Intent(this@SearchActivity, ResultActivity::class.java)
                intent.putExtra("id", it.id)
                intent.putExtra("pronunciation", it.word)
                intent.putExtra("definition", definition)
                startActivity(intent)

            }
        })

    }

    private fun checkTimeToReset() {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = formatter.format(Calendar.getInstance().time)

        if(!formattedDate.equals(mSharedPreferences.getTimeShared("time"))) {
            mSharedPreferences.storeTime("time", formattedDate)
            mSharedPreferences.store(DictionConstants.SHARED.LIMIT_KEY, 0)
        }

    }


}

