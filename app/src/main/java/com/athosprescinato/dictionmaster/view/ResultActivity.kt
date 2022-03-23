package com.athosprescinato.dictionmaster.view

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import com.athosprescinato.dictionmaster.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_result.*
import java.io.IOException

private lateinit var mViewModel: SharedViewModel
private lateinit var mediaPlayer: MediaPlayer


class ResultActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val wordBodyResponse = intent.extras?.get("bodyResponse") as WordResultModel

        setListeners()

        textSearchWord.text = wordBodyResponse.id.replaceFirstChar { it.uppercase() }
        textPronunciation.text =
            "/" + wordBodyResponse.results[0].lexicalEntries[0].entries[0].pronunciations!![1].phoneticSpelling + "/"
        textThatsFor.text = "That's it for \"" + wordBodyResponse.id + "\"!"

        try {
            validateData(textMeaning_1, wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses!![0].definitions[0])
        }
        catch (e: Exception) {
            when(e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    e.printStackTrace()
                    textMeaning_1.text = ""

                }
            }
        }

        try {
            validateData(textMeaning_2, wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses!![1].definitions[0])
        }
        catch (e: Exception) {
                when(e) {
                    is NullPointerException,
                    is RuntimeException,
                    is IndexOutOfBoundsException -> {
                        e.printStackTrace()
                        textMeaning_2.text = ""
                    }
                }
        }

        try {
            validateData(textMeaning_3, wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses!![2].definitions[0])
        }
        catch (e: Exception) {
            when(e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    e.printStackTrace()
                    textMeaning_3.text = ""

                }
            }
        }

        try {
            validateData(textDetails_1, wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses[0].subsenses!![0].definitions[0])
        }
        catch (e: Exception) {
            when(e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    e.printStackTrace()
                    textDetails_1.text = ""

                }
            }
        }

        try {
            validateData(textDetails_2, wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses[0].subsenses!![1].definitions[0])
        }
        catch (e: Exception) {
            when(e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    e.printStackTrace()
                    textDetails_2.text = ""

                }
            }
        }

        try {
            validateData(textDetails_3, wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses[0].subsenses!![2].definitions[0])
        }
        catch (e: Exception) {
            when(e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    e.printStackTrace()
                    textDetails_3.text = ""

                }
            }
        }

    }

    private fun setListeners() {
        btNewSearch.setOnClickListener(this)
        btPlayPronunciations.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btNewSearch) {
            finish()
        } else if (v.id == R.id.btPlayPronunciations) {
            playAudio(intent.extras?.get("bodyResponse") as WordResultModel)

        }
    }

    private fun validateData(view: TextView, data: String) {
        view.text = data
    }





    private fun playAudio(data: WordResultModel) {
        try {
            val audioUrl =
                data.results[0].lexicalEntries[0].entries[0].pronunciations!![1].audioFile
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()

        } catch (e: NullPointerException) {
            Toast.makeText(this, "Audio não encontrado! $e", Toast.LENGTH_SHORT).show()

        }

    }

}