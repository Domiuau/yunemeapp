package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

            }
        });
        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Double[] a = {Double.parseDouble(espaco1.getText().toString()),
                            Double.parseDouble(espaco2.getText().toString().substring(0,espaco2.getText().length()-1))
                    };


                    inicial.setText(a[0].toString());
                    novo.setText(String.format("%.3f",a[0] - (a[0] / 100) * a[1]));
                    if (a[1] < 101){
                        diferenca.setText(String.format("%.3f", (a[0] / 100) * a[1] ));
                    } else {
                        diferenca.setText(String.format("%.3f", ((a[0] / 100) * a[1]) * -1 ));
                    }

                    SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
                    DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_coisas (Ferramenta VARCHAR(20),Entrada VARCHAR,Saida VARCHAR,Data VARCHAR,Icone INT)");
                    DB_hist.execSQL("INSERT INTO TB_coisas (Ferramenta, Entrada, Saida, Data, Icone) VALUES ('Desconto'," +
                            " '" + "Valor = " + espaco1.getText().toString() + " / Desconto = " + espaco2.getText().toString() + "'," +
                            "'"+ "Novo valor = " + novo.getText().toString() + " / Difereça = " + diferenca.getText().toString() +"'," +
                            "'"+ Data.dataatual() +"', '"+ R.drawable.hist_desconto +"' )");

                } catch (NumberFormatException exception){
                    inicial.setText("Preencha todos os campos");
                    novo.setText("Preencha todos os campos");
                    diferenca.setText("Preencha todos os campos");
                }
                catch (Exception e){

                }




            }
        });


    }

    @Override
    public void onClick(View desconto) {

        inicial.setText("");
        novo.setText("");
        diferenca.setText("");

        if (desconto.getId() == R.id.nc) {
            espaco1.setText("");
            espaco2.setText("");
        }

        if (Teclado.espaco(desconto) == 2) {

           if(
            desconto.getId() != R.id.espaco1 && desconto.getId() != R.id.espaco2){

               if(!espaco2.getText().toString().contains("%")){
                   espaco2.setText(Teclado.teclado(desconto, espaco2.getText().toString())+"%");
               } else {
                   String a = "";
                   String[] z = espaco2.getText().toString().split("");
                   for (int i = 0;i< z.length-1;++i){
                       a += z[i];


                   }
                   espaco2.setText(Teclado.teclado(desconto, a)+"%");


               }

           }

           if (espaco2.getText().toString().equals("%")){
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