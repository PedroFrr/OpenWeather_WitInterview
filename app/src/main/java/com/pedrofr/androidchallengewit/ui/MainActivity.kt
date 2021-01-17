package com.pedrofr.androidchallengewit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pedrofr.androidchallengewit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) //Set AppTheme back once MainActivity is created
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}



