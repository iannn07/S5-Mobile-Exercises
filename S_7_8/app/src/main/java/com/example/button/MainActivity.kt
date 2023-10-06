package com.example.button

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var num:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val plus_button = findViewById<Button>(R.id.btn_count)
//        val plus_button = findViewById(R.id.btn_count) as Button
//        val plus_button:Button = findViewById(R.id.btn_count) as Button
        val minus_button = findViewById<Button>(R.id.btn_decrease)
        val text_1 = findViewById<TextView>(R.id.textView)

        plus_button.setOnClickListener(){
            num += 1
            text_1.text = num.toString()
            text_1.setTextColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
        }

        minus_button.setOnClickListener(){
            num -= 1
            if (num > 0) {
                text_1.text = num.toString()
                text_1.setTextColor(Color.parseColor("#EC0B43"))
            } else if (num <= 0){
                num = 0
                text_1.text = "It's Zero la haiya"
                text_1.setTextColor(Color.parseColor("#000000"))
            }
        }
    }
}