package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ferramentastela extends AppCompatActivity implements View.OnClickListener {

    ImageButton quadradoestranho, calculadora, botaobhaskara, botaodesconto, casinha, botaonumerosprimos, botaovelocidade;
    TextView bhaskara1, desconto1, numerosprimos1, velocidade1;
    AppCompatButton teste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ferramentastela);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        botaobhaskara = findViewById(R.id.bhaskara);
        botaodesconto = findViewById(R.id.desconto);
        casinha = findViewById(R.id.casinha);
        quadradoestranho = findViewById(R.id.quadradoestranho);
        calculadora = findViewById(R.id.calculadora);
        botaonumerosprimos = findViewById(R.id.numerosprimos);
        botaovelocidade = findViewById(R.id.velocidade);
        bhaskara1 = findViewById(R.id.bhaskara1);
        desconto1 = findViewById(R.id.desconto1);
        numerosprimos1 = findViewById(R.id.numerosprimos1);
        velocidade1 = findViewById(R.id.velocidade1);
        teste = findViewById(R.id.teste);


        botaobhaskara.setOnClickListener(this);
        botaodesconto.setOnClickListener(this);
        casinha.setOnClickListener(this);
        quadradoestranho.setOnClickListener(this);
        calculadora.setOnClickListener(this);
        botaonumerosprimos.setOnClickListener(this);
        botaovelocidade.setOnClickListener(this);
        bhaskara1.setOnClickListener(this);
        desconto1.setOnClickListener(this);
        numerosprimos1.setOnClickListener(this);
        velocidade1.setOnClickListener(this);
        teste.setOnClickListener(this);





    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        inicial trocar = new inicial();

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

            case R.id.bhaskara:
            case R.id.bhaskara1:
                Intent irparabhaskara = new Intent(this, Bhaskara.class);
                startActivity(irparabhaskara);
                Teclado.espaco = 1;
                break;

            case R.id.desconto:
            case R.id.desconto1:
                Intent irparadesconto = new Intent(this, Desconto.class);
                startActivity(irparadesconto);
                Teclado.espaco = 1;
                break;

            case R.id.numerosprimos:
            case R.id.numerosprimos1:
                Intent irparanumerosprimos = new Intent(this, Numerosprimos.class);
                startActivity(irparanumerosprimos);
                Teclado.espaco = 1;
                break;

            case R.id.velocidade:
            case R.id.velocidade1:
                Intent irparavelocidade = new Intent(this, Velocidade.class);
                startActivity(irparavelocidade);
                break;

            case R.id.teste:
                Intent irparateste = new Intent(this, Historico.class);
                startActivity(irparateste);
                break;

            default:
                break;
        }


    }


}


