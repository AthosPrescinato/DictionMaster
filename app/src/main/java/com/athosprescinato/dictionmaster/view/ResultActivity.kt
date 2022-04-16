package com.athosprescinato.dictionmaster.view

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.athosprescinato.dictionmaster.R
import com.athosprescinato.dictionmaster.service.model.WordResultModel
import com.athosprescinato.dictionmaster.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_result.*


private lateinit var mViewModel: SharedViewModel
private lateinit var mediaPlayer: MediaPlayer
private var audioUrl: String = ""
private var subSenseList: MutableList<String> = arrayListOf()
private val regex = Regex("[^A-Za-z0-9 •\n]")
private lateinit var meaningWord: String


class ResultActivity : AppCompatActivity(), View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val wordBodyResponse = intent.extras?.get("bodyResponse") as WordResultModel

        setListeners()

        try {
            textSearchWord.text = wordBodyResponse.id.replaceFirstChar { it.uppercase() }
            textThatsFor.text = "That's it for \"" + wordBodyResponse.id + "\"!"

            try {
                val pronunciation: List<WordResultModel.Pronunciation> =
                    wordBodyResponse.results[0].lexicalEntries[0].entries[0].pronunciations!!
                for (pronunciations in pronunciation) {
                    if (pronunciations.audioFile != null) {
                        textPronunciation.text = "/" + pronunciations.phoneticSpelling + "/"
                        audioUrl = pronunciations.audioFile
                        btPlayPronunciations.visibility = View.VISIBLE
                        textPronunciation.visibility = View.VISIBLE
                    }
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
                Log.e("ERROR", e.toString())
            }


            val senses: List<WordResultModel.Sense> =
                wordBodyResponse.results[0].lexicalEntries[0].entries[0].senses

            for ((index, definitions) in senses.withIndex()) when (index) {
                0 -> {
                    meaningWord = definitions.definitions.toString()
                    textMeaning_1.text =
                        "1) " + meaningWord.replace(regex, "").replaceFirstChar { it.uppercase() }

                    for (subsensesDefinitions in definitions.subsenses!!) {
                        subSenseList.add("• " + subsensesDefinitions.definitions.toString() + "\n")
                    }

                    val detailsResponse = regex.replace(subSenseList.toString(), "")
                    textDetails_1.text = detailsResponse


                    if (meaningWord.isNotEmpty())
                        textMeaning_1.visibility = View.VISIBLE
                    if (detailsResponse.isNotEmpty())
                        textDetails_1.visibility = View.VISIBLE

                }

                1 -> {
                    meaningWord = definitions.definitions.toString()
                    textMeaning_2.text =
                        "2) " + meaningWord.replace(regex, "").replaceFirstChar { it.uppercase() }

                    subSenseList.clear()
                    for (subsensesDefinitions in definitions.subsenses!!) {
                        subSenseList.add("• " + subsensesDefinitions.definitions.toString() + "\n")
                    }

                    val detailsResponse = regex.replace(subSenseList.toString(), "")
                    textDetails_2.text = detailsResponse

                    if (meaningWord.isNotEmpty())
                        textMeaning_2.visibility = View.VISIBLE
                    if (detailsResponse.isNotEmpty())
                        textDetails_2.visibility = View.VISIBLE

                }

                2 -> {
                    meaningWord = definitions.definitions.toString()
                    textMeaning_3.text =
                        "3) " + meaningWord.replace(regex, "").replaceFirstChar { it.uppercase() }

                    subSenseList.clear()
                    for (subsensesDefinitions in definitions.subsenses!!) {
                        subSenseList.add("• " + subsensesDefinitions.definitions.toString() + "\n")
                    }

                    val detailsResponse = regex.replace(subSenseList.toString(), "")
                    textDetails_3.text = detailsResponse

                    if (meaningWord.isNotEmpty())
                        textMeaning_3.visibility = View.VISIBLE
                    if (detailsResponse.isNotEmpty())
                        textDetails_3.visibility = View.VISIBLE

                }

            }


        } catch (e: Exception) {
            when (e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    e.printStackTrace()


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
            playAudio(audioUrl)

        }
    }



    private fun playAudio(audioUrl: String) {
        try {

            mediaPlayer = MediaPlayer()
            //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()

        } catch (e: Exception) {
            when (e) {
                is NullPointerException,
                is RuntimeException,
                is IndexOutOfBoundsException -> {
                    Toast.makeText(this, "Audio não encontrado!", Toast.LENGTH_LONG).show()
                    Log.i("[x] Error AudioFile", e.toString())
                }
            }
        }

    }

}