package com.athosprescinato.dictionmaster.view

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        mViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        validateData(intent.extras?.get("bodyResponse") as WordResultModel)

        setListeners()




//        textSearchWord.text = getExtraId
//        textPronunciation.text = getPronunciation
//        textMeaning_1.text = getdefinition
//        textMeaning_2.text = getdefinition2
//        textMeaning_3.text = getdefinition3
//        textDetails_1.text = bodyResponse.results[0].lexicalEntries[0].entries[0].pronunciations!![1].audioFile
//        textDetails_2.text = getdetails2
//        textDetails_3.text = getdetails3

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

    private fun validateData(data :WordResultModel) {
        mViewModel.validateData(data)


    }

    private fun playAudio(data: WordResultModel) {
        try {
            val audioUrl = data.results[0].lexicalEntries[0].entries[0].pronunciations!![1].audioFile
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

                mediaPlayer!!.setDataSource(audioUrl)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
            } catch (e: IOException){
                e.printStackTrace()

        } catch (e: NullPointerException) {
            Toast.makeText(this, "Audio não encontrado! $e", Toast.LENGTH_SHORT).show()

        }

    }

}