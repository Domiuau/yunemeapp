package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Desconto extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2;
    AppCompatImageButton nbackspace;
    TextView inicial, novo, diferenca;
    AppCompatImageView voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desconto);

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
        inicial = findViewById(R.id.inicial);
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
        inicial.setOnClickListener(this);
        novo.setOnClickListener(this);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        diferenca.setOnClickListener(this);
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                espaco1.setText("");
                espaco2.setText("");
                inicial.setText("");
                novo.setText("");
                diferenca.setText("");
                Data.fluxo = "";

            }
        });
        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comp = espaco1.getText().toString() + espaco2.getText().toString();

                if (!comp.equals(Data.fluxo) && !espaco1.getText().toString().isEmpty()
                        && !espaco2.getText().toString().isEmpty()) {

                    try {
                        inicial.setText(espaco1.getText().toString());
                        novo.setText(String.format("%.3f", Double.parseDouble(espaco1.getText().toString()) - (Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))));
                        if (Double.parseDouble(espaco2.getText().toString().replace("%", "")) < 101) {
                            System.out.println(Double.parseDouble(espaco2.getText().toString().replace("%", "")));
                            diferenca.setText(String.format("%.3f", (Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))));
                        } else {
                            diferenca.setText(String.format("%.3f", ((Double.parseDouble(espaco1.getText().toString()) / 100) * Double.parseDouble(espaco2.getText().toString().replace("%", ""))) * -1));
                        }

                        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
                        DB_hist.execSQL("INSERT INTO TB_coisas (Ferramenta, Entrada, Saida, Data, Icone) VALUES ('Desconto'," +
                                " '" + "Valor = " + espaco1.getText().toString() + " / Desconto = " + espaco2.getText().toString() + "'," +
                                "'" + "Novo valor = " + novo.getText().toString() + " / Difereça = " + diferenca.getText().toString() + "'," +
                                "'" + Data.dataatual() + "', '" + R.drawable.hist_desconto + "' )");
                        Data.fluxo = comp;
                    } catch (Exception e) {
                        Toast.makeText(Desconto.this, "Erro ao calcular", Toast.LENGTH_SHORT).show();
                    }
                } else if (espaco1.getText().toString().isEmpty()
                        || espaco2.getText().toString().isEmpty())  {
                    Toast.makeText(Desconto.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    @Override
    public void onClick(View desconto) {



        if (desconto.getId() == R.id.nc) {
            espaco1.setText("");
            espaco2.setText("");
            inicial.setText("");
            novo.setText("");
            diferenca.setText("");
        }

        if (Teclado.espaco(desconto) == 2) {

            if (
                    desconto.getId() != R.id.espaco1 && desconto.getId() != R.id.espaco2) {

                if (!espaco2.getText().toString().contains("%")) {
                    espaco2.setText(Teclado.teclado(desconto, espaco2.getText().toString()) + "%");
                } else {
                    String a = "";
                    String[] z = espaco2.getText().toString().split("");
                    for (int i = 0; i < z.length - 1; ++i) {
                        a += z[i];


                    }
                    espaco2.setText(Teclado.teclado(desconto, a) + "%");


                }

            }

            if (espaco2.getText().toString().equals("%")) {
                espaco2.setText("");
            }

            espaco2.setBackgroundResource(R.drawable.valorselecionado);
            espaco1.setBackgroundResource(R.drawable.valoresnovo);


        } else {

            espaco1.setText(Teclado.teclado(desconto, espaco1.getText().toString()));
            espaco1.setBackgroundResource(R.drawable.valorselecionado);
            espaco2.setBackgroundResource(R.drawable.valoresnovo);


        }

    }

}