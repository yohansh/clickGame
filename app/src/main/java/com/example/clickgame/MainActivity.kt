package com.example.clickgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn:Button=findViewById(R.id.play)

        btn.setOnClickListener()
        {
            val intent= Intent(this,GameActivity::class.java)
            startActivity(intent)
        }

    }

}