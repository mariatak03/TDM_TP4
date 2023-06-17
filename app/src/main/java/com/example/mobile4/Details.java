package com.example.mobile4;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Details extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    TextView Note;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_details);

        Note = findViewById(R.id.note);
        date = findViewById(R.id.date);
        ImageView audiobutton = findViewById(R.id.audio);

        Date currentDate = Calendar.getInstance().getTime();

        date.setText(currentDate.toString());

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language not supported");
                    }
                }else{
                    Log.e("TTS","Language not supported");
                }
            }
        });

        audiobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(String.valueOf(Note.getText()),0,null);
            }
        });

        Intent intent = this.getIntent();

        if (intent != null){
            String note = intent.getStringExtra("note");
            Note.setText(note);
        }



    }
    @Override
    protected void onPause() {
        if (textToSpeech != null){
            textToSpeech.stop();

        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();

    }


}