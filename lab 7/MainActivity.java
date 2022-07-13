package com.example.lab7a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t1;
    EditText txtinput;
    Button txttospeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtinput = findViewById(R.id.txt_input);
        txttospeech = findViewById(R.id.btn_txt2spch);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        txttospeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tospeak = txtinput.getText().toString();
                Toast.makeText(getBaseContext(),tospeak,Toast.LENGTH_SHORT).show();

                t1.speak(tospeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    public void onPause()
    {
        if(t1 != null)
        {
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}
