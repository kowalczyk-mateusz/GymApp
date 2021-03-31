package com.example.silka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

//--------------- INTENCJE --------------------
    fun trening1(view: View) {
        val intent = Intent(applicationContext, Trening_1::class.java)
        startActivity(intent)
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


    fun trening2(view: View) {
        val intent2 = Intent(applicationContext, Trening_2::class.java)
        startActivity(intent2)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
//-----------------------------------------------
}