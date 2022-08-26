package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Numerosprimos extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos;
    AppCompatImageButton nbackspace;
    TextView diferencamenor, diferencamaior, numeroinicial, menorque, maiorque, espaco1, resultado;
    int me, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerosprimos);
        getSupportActionBar().hide();
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
        diferencamenor = findViewById(R.id.diferencamenor);
        diferencamaior = findViewById(R.id.diferencamaior);
        numeroinicial = findViewById(R.id.numeroinicial);
        menorque = findViewById(R.id.menorque);
        maiorque = findViewById(R.id.maiorque);
        resultado = findViewById(R.id.resultado);


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
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diferencamaior.setText("");
                diferencamenor.setText("");
                maiorque.setText("");
                menorque.setText("");
                numeroinicial.setText("");
                espaco1.setText("");
                resultado.setText("");

            }
        });
        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (primo(Integer.parseInt(espaco1.getText().toString()))) {
                    resultado.setText(espaco1.getText().toString() + " é primo!");
                } else {
                    resultado.setText(espaco1.getText().toString() + " não é primo!");
                }

                me = Integer.parseInt(espaco1.getText().toString());

                System.out.println(me +"aaa");

                while (!primo(me -1)) {
                    me--;
                }
                menorque.setText(me - 1 + "");
                diferencamenor.setText(((Integer.parseInt(espaco1.getText().toString()) - (me - 1)) * -1 + ""));
                me = Integer.parseInt(espaco1.getText().toString());

                while (!primo(me + 1)) {
                    me++;
                }
                maiorque.setText(me + 1 + "");
                numeroinicial.setText(espaco1.getText().toString());
                diferencamaior.setText((me + 1) - Integer.parseInt(espaco1.getText().toString())+"");
            }
        });

    }

    boolean primo(int a) {
        b = 0;
        for (int i = a-1; i > 1; --i) {
            if (a % i == 0) {
                b++;
            }
        }


        if (b == 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        espaco1.setText(Teclado.teclado(v, espaco1.getText().toString()));


    }
}