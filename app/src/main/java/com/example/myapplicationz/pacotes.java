package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pacotes extends AppCompatActivity implements View.OnClickListener {
    ImageButton casinha,quadradoestranho,calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacotes);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        casinha = findViewById(R.id.casinha);
        quadradoestranho = findViewById(R.id.quadradoestranho);
        calculadora = findViewById(R.id.calculadora);

        casinha.setOnClickListener(this);
        quadradoestranho.setOnClickListener(this);
        calculadora.setOnClickListener(this);

    }

    //futuros pacotes que serão adicionados, é a tela de ferramentas, mas para pacotes

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.casinha:
                startActivity(Data.a(this,ferramentastela.class));
                break;

            case R.id.calculadora:
                startActivity(Data.a(this, Calculadora.class));
                break;

            case R.id.quadradoestranho:
                startActivity(Data.a(this,pacotes.class));
                break;

        }
    }
}