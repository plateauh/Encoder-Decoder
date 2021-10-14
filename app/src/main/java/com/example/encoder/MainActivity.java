package com.example.encoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // I have noticed in the provided output that every letter was in lower case, so I assumed that they were converted for simplicity.
                String userInput = editText.getText().toString().toLowerCase();
                textView.setText(encode(userInput));
            }
        });
    }

    public String encode(String input){
        String output = "";
        char letter = ' ';
        for (int i=0; i<input.length(); i++) {

            // skipping spaces
            if (input.charAt(i) == ' ')
                letter = input.charAt(i);

            // 97 represents 'a', so if the letter encoded passed 'a' we're gonna add 26
            else if (input.charAt(i)-13 < 97)
                letter = (char)(input.charAt(i)+13); // -13+26 = +13

            // The default
            else
                letter = (char)(input.charAt(i)-13);

            output += letter;
        }
        return output;
    }
}