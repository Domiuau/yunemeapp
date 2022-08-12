package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pacotes extends AppCompatActivity implements View.OnClickListener {
    ImageButton casinha,quadradoestranho,calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacotes);
        getSupportActionBar().hide();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        casinha = findViewById(R.id.casinha);
        quadradoestranho = findViewById(R.id.quadradoestranho);
        calculadora = findViewById(R.id.calculadora);

        casinha.setOnClickListener(this);
        quadradoestranho.setOnClickListener(this);
        calculadora.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.casinha:
                Intent irparacasinha = new Intent(this, ferramentastela.class);
                startActivity(irparacasinha);
                break;

            case R.id.calculadora:
                Intent irparacalculadora = new Intent(this, calculadora.class);
                startActivity(irparacalculadora);
                break;

            case R.id.quadradoestranho:
                Intent irparaquadradoestranho = new Intent(this, pacotes.class);
                startActivity(irparaquadradoestranho);
                break;

        }
    }
}