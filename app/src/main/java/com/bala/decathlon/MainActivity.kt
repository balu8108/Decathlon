package com.bala.decathlon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bala.decathlon.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MainActivityBinding.inflate(layoutInflater).root)
    }
}