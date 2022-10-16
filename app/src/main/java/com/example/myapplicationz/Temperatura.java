package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.TextViewCompat;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temperatura extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2;
    TextView formula;
    Spinner spinner1, spinner2;
    BigDecimal vezes;
    LinearLayout linear;
    String formulausada;
    AppCompatImageButton voltar,nbackspace,inverter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);
        Data.fluxo = "";
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);

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
        linear = findViewById(R.id.mostrarformula);
        spinner2.setSelection(1);
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

        //exibi a formula na tela

        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!espaco1.getText().toString().isEmpty()){

                    tentar(DB_hist);
                    formula.setText(formulausada);
                }

            }
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //inverte as unidades de temperatura e calcula novamente e determina se a formula sera exibida ou não

        inverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(spinner1.getSelectedItemPosition());
                int a = spinner1.getSelectedItemPosition();
                spinner1.setSelection(spinner2.getSelectedItemPosition());
                spinner2.setSelection(a);

                if (espaco1.getText().toString().isEmpty()){
                    limpar();
                } else {
                    tentar(DB_hist);
                }
                if (!formula.getText().toString().isEmpty()){

                    formula.setText(formulausada);
                }


            }
        });

        //limpa

        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espaco1.setText("");
                espaco2.setText("");
                formula.setText("");
                Data.fluxo = "";
                formulausada = "";
            }
        });

        //calcula sem motrar a formula

        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                formula.setText("");
                espaco2.setText("");
                tentar(DB_hist);

            }
        });

    }

    //exibi na tela o resultado, mecanismo para não poluir o historico e adicionar os valores ao historico

    void tentar (SQLiteDatabase banco){

        try {
            String comp = espaco1.getText().toString() + spinner1.getSelectedItem().toString() + spinner2.getSelectedItem().toString();
            if (!comp.equals(Data.fluxo) && !espaco1.getText().toString().equals("")) {

                espaco2.setText(selecionarformula(espaco1.getText().toString()));

                Teclado.adicionarhist(banco,"Temperatura", espaco1.getText().toString() + " " + spinner1.getSelectedItem().toString()
                        + " = " + espaco2.getText().toString() + " " + spinner2.getSelectedItem().toString(), formulausada, R.drawable.hist_temperatura,comp);

            } else if (espaco1.getText().toString().equals("")) {
                Toast.makeText(Temperatura.this, "Insira um número", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(Temperatura.this, "Não foi possivel calcular", Toast.LENGTH_SHORT).show();

        }
    }

    void adicionarformula(String formulausada1) {

        formulausada = formulausada1;
    }

    void limpar(){
        espaco1.setText("");
        espaco2.setText("");
        formula.setText("");
        espaco2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        espaco1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco1,AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco2,AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        espaco1.setTextSize(16);
        espaco2.setTextSize(16);
        Data.fluxo = "";
    }

    //seleciona a formula que sera utilizada e retorna o valor ja convertido
    //aqui é utilizada a api BigDecimal para formatar os numeros melhor e para evitar burrices do java como 1x1.2 =  1.19999999999999999999999999999999999999999999

    String selecionarformula(String valor) {
        switch (spinner1.getSelectedItemPosition()) {
            case 0:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = new BigDecimal(valor).multiply(new BigDecimal("1"));
                    adicionarformula(valor + " x " + "1 = " + vezes.toString());
                } else if (spinner2.getSelectedItemPosition() == 1) {
                    vezes = new BigDecimal(valor).add(new BigDecimal("273.15"));
                    adicionarformula(valor + " + 273,15 = " + vezes.toString());
                } else {

                    vezes = new BigDecimal(valor).multiply(new BigDecimal("9"))
                            .divide(new BigDecimal("5"))
                            .add(new BigDecimal("32"));
                    adicionarformula(valor + " x 9 ÷ 5 + 32 = " + vezes.toString());
                }

                break;

            case 1:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = new BigDecimal(valor).subtract(new BigDecimal("273.15"));
                    adicionarformula(valor + " - 273.15 = " + vezes.toString());

                } else if (spinner2.getSelectedItemPosition() == 1) {
                    vezes = new BigDecimal(valor).multiply(new BigDecimal("1"));
                    adicionarformula(valor + " x 1 = " + vezes.toString());
                } else {
                    vezes = new BigDecimal(valor).subtract(new BigDecimal("273.15"))
                            .multiply(new BigDecimal("9"))
                            .divide(new BigDecimal("5"))
                            .add(new BigDecimal("32"));
                    adicionarformula(valor + " - 273,15 x 9 ÷ 5 + 32 = " + vezes.toString());
                }
                break;

            case 2:
                if (spinner2.getSelectedItemPosition() == 0) {
                    vezes = new BigDecimal(valor).subtract(new BigDecimal("32")).multiply(new BigDecimal("5"));
                    adicionarformula(valor + " - 32 x 5 = " + vezes.toString());
                    return String.format("%.1f", Double.parseDouble(vezes.toString()) / 9);

                } else if (spinner2.getSelectedItemPosition() == 1) {
                    vezes = new BigDecimal(valor).subtract(new BigDecimal("32"))
                            .multiply(new BigDecimal("5"));
                    adicionarformula(valor + " - 32 x 5 ÷ 9 + 273,15 = " + vezes.toString());
                    return String.format("%.1f", Double.parseDouble(vezes.toString()) / 9 + 273.15);


                } else {
                    vezes = new BigDecimal(valor).multiply(new BigDecimal("1"));
                    adicionarformula(valor + " x 1 = " + vezes.toString());
                }


        }
        return vezes.toString();

    }

    //teclado

    @Override
    public void onClick(View v) {


        Teclado.adicionarnatela(espaco1,v);


    }
}