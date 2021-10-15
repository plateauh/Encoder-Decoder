package com.example.encoder

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phrasesList = arrayListOf<Phrase>()
        val phrasesRecyclerView = findViewById<RecyclerView>(R.id.phrases_rv)
        val adapter = Adapter(phrasesList)
        phrasesRecyclerView.adapter = adapter
        phrasesRecyclerView.layoutManager = LinearLayoutManager(this)

        val encodeEditText = findViewById<EditText>(R.id.encode_et)
        val encodeButton = findViewById<Button>(R.id.encode_btn)
        encodeButton.setOnClickListener {
            if (encodeEditText.text.toString().isNotEmpty()){
                val textToBeEncoded = encodeEditText.text.toString()
                phrasesList.add(Phrase(textToBeEncoded, Color.BLACK))
                phrasesList.add(Phrase(encode(textToBeEncoded), Color.BLUE))
                adapter.update()
                encodeEditText.setText("")
            }
            else
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
        }

        val decodeEditText = findViewById<EditText>(R.id.decode_et)
        val decodeButton = findViewById<Button>(R.id.decode_btn)
        decodeButton.setOnClickListener {
            if (decodeEditText.text.toString().isNotEmpty()){
                val textToBeDecoded = decodeEditText.text.toString()
                phrasesList.add(Phrase(textToBeDecoded, Color.BLACK))
                phrasesList.add(Phrase(decode(textToBeDecoded), Color.BLUE))
                adapter.update()
                decodeEditText.setText("")
            }
            else
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
        }
    }

    private fun encode (input: String): String {
        var output = ""
        var letter: Char
        for (char in input) {
            letter = when {
                !char.isLetter() -> char
                char.toInt()-13 < 65 -> (char.toInt() + 13).toChar()
                char.isLowerCase() && char.toInt()-13 < 97 -> (char.toInt() + 13).toChar()
                else -> (char.toInt() - 13).toChar()
            }
            output += letter
        }
        return output
    }

    private fun decode(input: String): String {
        return input
    }
}