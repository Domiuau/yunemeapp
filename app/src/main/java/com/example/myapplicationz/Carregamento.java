package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class Carregamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {
            Thread.sleep(200);
        } catch (Exception e){}

        Intent iniciar = new Intent(Carregamento.this,ferramentastela.class);
        startActivity(iniciar);
        finish();


    }

}
