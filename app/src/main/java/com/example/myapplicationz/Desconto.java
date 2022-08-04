package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Desconto extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nbackspace, nok, nmenos, espaco1, espaco2;
    TextView inicial, novo, diferenca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desconto);
        this.getSupportActionBar().hide();

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
                            Double.parseDouble(espaco2.getText().toString())
                    };

                    inicial.setText(a[0].toString());
                    novo.setText(String.valueOf(a[0] - (a[0] / 100) * a[1]));
                    diferenca.setText(String.valueOf(((a[0] / 100) * a[1]) * -1));
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

            espaco2.setText(Teclado.teclado(desconto, espaco2.getText().toString()));

        } else {

            espaco1.setText(Teclado.teclado(desconto, espaco1.getText().toString()));

        }

    }

}