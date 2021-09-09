package com.bala.nytnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bala.nytnews.databinding.MainActivityBinding
import com.bala.nytnews.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MainActivityBinding.inflate(layoutInflater).root)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }*/
    }
}