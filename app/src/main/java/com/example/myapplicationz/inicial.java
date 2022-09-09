package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class inicial extends AppCompatActivity implements View.OnClickListener {

    TextView visitante;
    AppCompatButton criarcontra, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        getWindow().setStatusBarColor(ContextCompat.getColor(inicial.this, R.color.verdeouazulsla));
        visitante = findViewById(R.id.visitante);
        visitante.setPaintFlags(visitante.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        criarcontra = findViewById(R.id.criarconta);
        login = findViewById(R.id.login);

        criarcontra.setOnClickListener(this);
        login.setOnClickListener(this);
        visitante.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.visitante) {
            Intent iniciar = new Intent(this, ferramentastela.class);
            startActivity(iniciar);
        } else if (v.getId() == R.id.login) {
            Intent iniciar = new Intent(this, login.class);
            startActivity(iniciar);
        } else {
            Intent iniciar = new Intent(this, cadastrar.class);
            startActivity(iniciar);
        }


    }
}
