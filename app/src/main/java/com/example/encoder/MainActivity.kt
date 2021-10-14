package com.example.encoder

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { // I have noticed in the provided output that every letter was in lower case, so I assumed that they were converted for simplicity.
            val userInput = editText.text.toString().toLowerCase()
            textView.text = encode(userInput)
        }
    }

    fun encode(input: String): String {
        var output = ""
        var letter = ' '
        for (i in 0 until input.length) {
            // skipping spaces
            letter = if (input[i] == ' ') input[i] else if (input[i].toInt() - 13 < 97) (input[i].toInt() + 13).toChar() // -13+26 = +13
            else (input[i].toInt() - 13).toChar()
            output += letter
        }
        return output
    }
}