package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ferramentastela extends AppCompatActivity implements View.OnClickListener {

    ImageButton botaobhaskara, botaodesconto,casinha,quadradoestranho,calculadora,botaonumerosprimos;
    TextView bhaskara1,bhaskara2,desconto1,desconto2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ferramentastela);
        this.getSupportActionBar().hide();


        botaobhaskara = findViewById(R.id.bhaskara);
        botaodesconto = findViewById(R.id.desconto);
        bhaskara1 = findViewById(R.id.bhaskara1);
        bhaskara2 = findViewById(R.id.bhaskara2);
        desconto1 = findViewById(R.id.desconto1);
        desconto2 = findViewById(R.id.desconto2);
        casinha = findViewById(R.id.casinha);
        quadradoestranho = findViewById(R.id.quadradoestranho);
        calculadora = findViewById(R.id.calculadora);
        botaonumerosprimos = findViewById(R.id.numerosprimos1);



        botaobhaskara.setOnClickListener(this);
        botaodesconto.setOnClickListener(this);
        bhaskara1.setOnClickListener(this);
        bhaskara2.setOnClickListener(this);
        desconto1.setOnClickListener(this);
        desconto2.setOnClickListener(this);
        casinha.setOnClickListener(this);
        quadradoestranho.setOnClickListener(this);
        calculadora.setOnClickListener(this);
        botaonumerosprimos.setOnClickListener(this);


 }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.casinha:
                Intent irparacasinha = new Intent(this,ferramentastela.class);
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

            case R.id.bhaskara:
            case R.id.bhaskara1:
            case R.id.bhaskara2:
                Intent irparabhaskara = new Intent(this, Bhaskara.class);
                startActivity(irparabhaskara);
                Teclado.espaco = 1;
                break;

            case R.id.desconto:
            case R.id.desconto1:
            case R.id.desconto2:
                Intent irparadesconto = new Intent(this, Desconto.class);
                startActivity(irparadesconto);
                Teclado.espaco = 1;
                break;

            case R.id.numerosprimos1:
                Intent irparanumerosprimos = new Intent(this,Numerosprimos.class);
                startActivity(irparanumerosprimos);
                break;

            default: System.exit(0);
        }






        }




    }


