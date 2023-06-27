package com.example.texttospeach;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    Button btn;
    EditText textInput;
    AudioManager m_amAudioManager;
    private static final String TAG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        textInput = findViewById(R.id.input);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(MainActivity.this, "Lang. missing Data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Status error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn.setOnClickListener(v -> {
            String msg = textInput.getText().toString();
            tts.setPitch(.8f);
            tts.setSpeechRate(.72f);
            tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
        });




    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}