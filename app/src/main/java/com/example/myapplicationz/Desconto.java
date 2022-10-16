package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.TextViewCompat;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Desconto extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2;
    AppCompatImageButton nbackspace;
    TextView novo, diferenca;
    AppCompatImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desconto);
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
        novo = findViewById(R.id.aplicado);
        diferenca = findViewById(R.id.diferenca);
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

        //limpa tudo

        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espaco1.setText("");
                espaco2.setText("");
                novo.setText("");
                diferenca.setText("");
                espaco2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                espaco1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco1,AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
                TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco2,AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
                espaco1.setTextSize(16);
                espaco2.setTextSize(16);
                Data.fluxo = "";

            }
        });

        //mecanismo para não poluir o historico, calculo de desconto e adicionar os valores no historico

        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comp = espaco1.getText().toString() + espaco2.getText().toString();

                if (!comp.equals(Data.fluxo) && !espaco1.getText().toString().isEmpty()
                        && !espaco2.getText().toString().isEmpty()) {

                    try {


                        if ((Double.parseDouble(espaco1.getText().toString()) - (Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))) % 1 == 0) {

                            novo.setText(String.format("%.0f", Double.parseDouble(espaco1.getText().toString()) - (Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))));

                        } else {
                            novo.setText(String.format("%.2f", Double.parseDouble(espaco1.getText().toString()) - (Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))));
                        }
                            diferenca.setText(String.format("%.2f", (Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))* -1));

                        if (diferenca.getText().toString().contains(",00")) {
                            diferenca.setText(String.format("%.0f", Double.parseDouble(diferenca.getText().toString().replace(",", "."))));
                        }

                        if (Double.parseDouble(espaco2.getText().toString().replace("%", "")) > 100){
                            diferenca.setText("+" + diferenca.getText().toString().replace("-",""));
                        }

                        Teclado.adicionarhist(DB_hist, "Desconto", "Valor = " + espaco1.getText().toString() + " / Desconto = " + espaco2.getText().toString(),
                                "Novo valor = " + novo.getText().toString() + " / Difereça = " + diferenca.getText().toString(), R.drawable.hist_desconto, comp);


                    } catch (Exception e) {
                        Toast.makeText(Desconto.this, "Erro ao calcular", Toast.LENGTH_SHORT).show();
                    }
                } else if (espaco1.getText().toString().isEmpty()
                        || espaco2.getText().toString().isEmpty()) {
                    Toast.makeText(Desconto.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    //teclado

    @Override
    public void onClick(View desconto) {

        if (Teclado.espaco(desconto) == 2) {

            Teclado.adicionarnatela(espaco2, desconto);
            espaco2.setText(espaco2.getText().toString().replace("%",""));
            if (!espaco2.getText().toString().isEmpty()){
                espaco2.append("%");
            }


        } else {

            Teclado.adicionarnatela(espaco1, desconto);


        }

    }

}