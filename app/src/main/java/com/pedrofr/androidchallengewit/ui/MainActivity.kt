package com.pedrofr.androidchallengewit.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.pedrofr.androidchallengewit.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) //Set AppTheme back once MainActivity is created

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}