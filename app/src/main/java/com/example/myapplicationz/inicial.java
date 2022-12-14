package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class inicial extends AppCompatActivity implements View.OnClickListener {

    TextView visitante;
    AppCompatButton criarcontra, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setStatusBarColor(ContextCompat.getColor(inicial.this, R.color.verdeouazulsla));
        visitante = findViewById(R.id.visitante);
        visitante.setPaintFlags(visitante.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        criarcontra = findViewById(R.id.criarconta);
        login = findViewById(R.id.login);

        criarcontra.setOnClickListener(this);
        login.setOnClickListener(this);
        visitante.setOnClickListener(this);




    }

    //tela inicial, a primeira tela que o usuario tera contato, onde ele podera criar uma conta, entrar em uma conta ja existente ou entrar como visitante

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.visitante) {
            startActivity(Data.a(this,ferramentastela.class));
        } else if (v.getId() == R.id.login) {
            startActivity(Data.a(this,login.class));
        } else {
            startActivity(Data.a(this,cadastrar.class));
        }


    }


}
