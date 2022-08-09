package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatToggleButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Velocidade extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nbackspace, nok, nmenos, espaco1, espaco2;
    TextView formula;
    Spinner spinner1, spinner2;
    AppCompatImageButton inverter;
    float vezes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidade);
        getSupportActionBar().hide();

        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        n6 = findViewById(R.id.n6);
        n7 = findViewById(R.id.n7);
        n8 = findViewById(R.id.n8);
        n9 = findViewById(R.id.n9);
        n0 = findViewById(R.id.n0);
        nvirgula = findViewById(R.id.nvirgula);
        nc = findViewById(R.id.nc);
        nbackspace = findViewById(R.id.nbackspace);
        nok = findViewById(R.id.nok);
        nmenos = findViewById(R.id.nmenos);
        espaco1 = findViewById(R.id.espaco1);
        espaco2 = findViewById(R.id.espaco2);
        formula = findViewById(R.id.formula);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        inverter = findViewById(R.id.inverter);


        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
        n4.setOnClickListener(this);
        n5.setOnClickListener(this);
        n6.setOnClickListener(this);
        n7.setOnClickListener(this);
        n8.setOnClickListener(this);
        n9.setOnClickListener(this);
        n0.setOnClickListener(this);
        nvirgula.setOnClickListener(this);
        nbackspace.setOnClickListener(this);
        nmenos.setOnClickListener(this);
        espaco1.setOnClickListener(this);
        espaco2.setOnClickListener(this);



        inverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(spinner1.getSelectedItemPosition());
                int a = spinner1.getSelectedItemPosition();
                spinner1.setSelection(spinner2.getSelectedItemPosition());
                spinner2.setSelection(a);
                selecionarformula();


            }
        });
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espaco1.setText("");
                espaco2.setText("");
                formula.setText("");
            }
        });


        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarformula();




            }
        });


    }

    void adicionar(Double valorconvertido,float vezes) {
        formula.setText(espaco1.getText().toString() + " x " + vezes + " = " + (valorconvertido*vezes));

        espaco2.setText(valorconvertido*vezes+"");


    }

    void selecionarformula (){
        switch (spinner1.getSelectedItemPosition()) {
            case 0:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = 1;
                } else if (spinner2.getSelectedItemPosition() == 1){
                    vezes = 0.621f;
                } else {
                    vezes = 0.277f;
                }

                break;
            case 1:
                if (spinner2.getSelectedItemPosition() == 0){
                    vezes = 1.609f;

                } else if (spinner2.getSelectedItemPosition() == 1){
                    vezes = 1;
                } else {
                    vezes = 0.447f;
                }
                break;

            case 2:
                if (spinner2.getSelectedItemPosition() == 0){
                    vezes = 3.6f;

                } else if (spinner2.getSelectedItemPosition() == 1){
                    vezes = 2.236f;
                } else {
                    vezes = 1;
                }


        }
        adicionar(Double.parseDouble(espaco1.getText().toString()),vezes);
    }

    @Override
    public void onClick(View v) {


            espaco1.setText(Teclado.teclado(v, espaco1.getText().toString()));





    }

}