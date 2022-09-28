package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.core.app.ActivityCompat;

import android.content.pm.ActivityInfo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Velocidade extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2;
    AppCompatImageButton nbackspace;
    TextView formula;
    Spinner spinner1, spinner2;
    AppCompatImageButton inverter;
    BigDecimal vezes;
    AppCompatImageView voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidade);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
        voltar = findViewById(R.id.voltar);
        Data.fluxo = "";


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
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(spinner1.getSelectedItemPosition());
                int a = spinner1.getSelectedItemPosition();
                spinner1.setSelection(spinner2.getSelectedItemPosition());
                spinner2.setSelection(a);
                tentar();


            }
        });
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espaco1.setText("");
                espaco2.setText("");
                formula.setText("");
                Data.fluxo = "";
            }
        });




        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tentar();


            }
        });


    }

    void adicionarhist(BigDecimal vezes, Double numero) {
        BigDecimal a = new BigDecimal(numero.toString());
        formula.setText(espaco1.getText().toString() + " x " + vezes + " = " + a.multiply(vezes).toString());

        espaco2.setText(a.multiply(vezes).toString());

        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
        DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_coisas (Ferramenta VARCHAR(20),Entrada VARCHAR,Saida VARCHAR,Data VARCHAR,Icone INT)");
        DB_hist.execSQL("INSERT INTO TB_coisas (Ferramenta, Entrada, Saida, Data, Icone) VALUES ('Velocidade'," +
                " '" + espaco1.getText().toString() + " " + spinner1.getSelectedItem().toString() +
                " = " + espaco2.getText().toString() + " " + spinner2.getSelectedItem().toString() + "'," +
                " '" + formula.getText().toString() + "'," +
                "'" + Data.dataatual() + "', '" + R.drawable.hist_velocidade + "' )");


    }

    void tentar (){

        try {

            String consulta = espaco1.getText().toString()+spinner1.getSelectedItem().toString()+spinner2.getSelectedItem().toString();

            if (!consulta.equals(Data.fluxo) && !espaco1.getText().toString().equals("")) {

                selecionarformula();
                Data.atualizaradicionadas();
                Data.fluxo = consulta;

            } else if (espaco1.getText().toString().equals("")) {
                Toast.makeText(Velocidade.this, "Insira um número", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {
            Toast.makeText(Velocidade.this, "Não foi possivel calcular", Toast.LENGTH_SHORT).show();

        }

    }


    void selecionarformula() {
        switch (spinner1.getSelectedItemPosition()) {
            case 0:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = new BigDecimal("1");
                } else if (spinner2.getSelectedItemPosition() == 1) {
                    vezes = new BigDecimal("0.621");
                } else {
                    vezes = new BigDecimal("0.277");
                }

                break;

            case 1:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = new BigDecimal("1.609");

                } else if (spinner2.getSelectedItemPosition() == 1) {
                    vezes = new BigDecimal("1");
                } else {
                    vezes = new BigDecimal("0.447");
                }
                break;

            case 2:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = new BigDecimal("3.6");

                } else if (spinner2.getSelectedItemPosition() == 1) {
                    vezes = new BigDecimal("2.237");
                } else {
                    vezes = new BigDecimal("1");
                }


        }
        adicionarhist(vezes, Double.parseDouble(espaco1.getText().toString()));
    }

    @Override
    public void onClick(View v) {


        espaco1.setText(Teclado.teclado(v, espaco1.getText().toString()));


    }

}