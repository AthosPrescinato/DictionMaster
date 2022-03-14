package com.athosprescinato.dictionmaster.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.athosprescinato.dictionmaster.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)

    }
}