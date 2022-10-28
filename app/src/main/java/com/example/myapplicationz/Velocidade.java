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

public class Velocidade extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2;
    TextView formula;
    Spinner spinner1, spinner2;
    BigDecimal vezes;
    LinearLayout linear;
    AppCompatImageButton voltar,nbackspace,inverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidade);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
        Data.fluxo = "";

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
        Data.spinner(spinner1,spinner2,R.array.velocidade,Velocidade.this);


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
                    formula.setText(espaco1.getText().toString() + " x " + vezes.toString() + " = " + espaco2.getText().toString());
                }




            }
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
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

                    formula.setText(espaco1.getText().toString() + " x " + vezes.toString() + " = " + espaco2.getText().toString());
                }



            }
        });
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                limpar();
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

    //calcula e exibi na tela o resultado, mecanismo para não poluir o historico e adicionar os valores ao historico

    void tentar (SQLiteDatabase banco){

        try {
            String comp = espaco1.getText().toString() + spinner1.getSelectedItem().toString() + spinner2.getSelectedItem().toString();
            if (!comp.equals(Data.fluxo) && !espaco1.getText().toString().equals("")) {
                espaco2.setText(selecionarformula().multiply(new BigDecimal(espaco1.getText().toString())).toString());
                Teclado.adicionarhist(banco,"Temperatura", espaco1.getText().toString() + " " + spinner1.getSelectedItem().toString()
                        + " = " + espaco2.getText().toString() + " " + spinner2.getSelectedItem().toString(),
                        espaco1.getText().toString() + " x " + vezes.toString() + " = " + espaco2.getText().toString(), R.drawable.hist_temperatura,comp);
                Data.atualizaradicionadas();
                Data.fluxo = comp;

                System.out.println(Data.fluxo + espaco1.getText().toString() + spinner1.getSelectedItem().toString().equals(Data.fluxo) + espaco1.getText().toString().equals("")+"fjnhnjdfhjnjdfghjnfjnfjnkjnkg");
            } else if (espaco1.getText().toString().equals("")) {
                Toast.makeText(Velocidade.this, "Insira um número", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(Velocidade.this, "Não foi possivel calcular", Toast.LENGTH_SHORT).show();

        }
    }

    //retorna a formula que será utilizada
    //aqui é utilizada a api BigDecimal para formatar os numeros melhor e para evitar burrices do java como 1x1.2 =  1.19999999999999999999999999999999999999999999

    BigDecimal selecionarformula() {
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
        //adicionar(vezes,Double.parseDouble(espaco1.getText().toString()));
        return vezes;

    }

    //teclado

    @Override
    public void onClick(View v) {


        Teclado.adicionarnatela(espaco1,v);


    }
}