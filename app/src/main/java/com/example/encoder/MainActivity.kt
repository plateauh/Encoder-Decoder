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
    /* Notes:
    *  65 corresponds to A
    *  90 corresponds to Z
    *  97 corresponds to a
    *  122 corresponds to z
    * */
    private fun encode (input: String): String {
        var output = ""
        var letter: Char
        for (char in input) {
            letter = when {
                !char.isLetter() -> char // current character is not letter
                char.toInt()-13 < 65 -> (char.toInt() + 13).toChar() // current character is in upper case and will pass characters range after encoding
                char.isLowerCase() && char.toInt()-13 < 97 -> (char.toInt() + 13).toChar() // current character is in lower case and will pass lower characters range after encoding
                else -> (char.toInt() - 13).toChar() // current character will not pass its range after encoding
            }
            output += letter
        }
        return output
    }

    private fun decode (input: String): String {
        var output = ""
        var letter: Char
        for (char in input) {
            letter = when {
                !char.isLetter() -> char // current character is not letter
                char.isUpperCase() && char.toInt()+13 > 90 -> (char.toInt() - 13).toChar() // current character is in upper case and will pass upper characters range after decoding
                char.toInt()+13 > 122 -> (char.toInt() - 13).toChar() // current character is in lower case and will pass characters range after decoding
                else -> (char.toInt() + 13).toChar() // current character will not pass its range after decoding
            }
            output += letter
        }
        return output
    }
}