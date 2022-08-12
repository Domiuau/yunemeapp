package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import android.widget.TextView;
import android.widget.Toast;

public class Bhaskara extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nbackspace, nok, nmenos, espaco1, espaco2, espaco3;
    TextView espacox1, espacox2, espacodelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bhaskara);
        this.getSupportActionBar().hide();
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
        espaco3 = findViewById(R.id.espaco3);
        espacox1 = findViewById(R.id.espacox1);
        espacox2 = findViewById(R.id.espacox2);
        espacodelta = findViewById(R.id.espacodelta);

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
        espaco3.setOnClickListener(this);
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                espaco1.setText("");
                espaco2.setText("");
                espaco3.setText("");
                espacodelta.setText("");
                espacox1.setText("");
                espacox2.setText("");

            }
        });

        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
                //delta = 0 -> "1 -10 25"

                try {

                    Float[] a = {
                            Float.parseFloat(espaco1.getText().toString()),
                            Float.parseFloat(espaco2.getText().toString()),
                            Float.parseFloat(espaco3.getText().toString())
                    };

                    if ((a[1] * a[1]) - 4 * a[0] * a[2] < 0) {
                        espacodelta.setText((a[1] * a[1]) - 4 * a[0] * a[2] + "");
                        espacox1.setText("sem resultados reais");
                        espacox2.setText("sem resultados reais");
                    } else if ((a[1] * a[1]) - 4 * a[0] * a[2] == 0) {
                        espacodelta.setText((a[1] * a[1]) - 4 * a[0] * a[2] + "");
                        espacox1.setText((-a[1] + Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");
                        espacox2.setText((-a[1] - Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");
                    } else {
                        espacodelta.setText((a[1] * a[1]) - 4 * a[0] * a[2] + "");
                        espacox1.setText((-a[1] + Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");
                        espacox2.setText((-a[1] - Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");

                    }


                    /* Float[] a = {Float.parseFloat(espaco1.getText().toString()),
                            Float.parseFloat(espaco2.getText().toString()),
                            Float.parseFloat(espaco3.getText().toString())
                    };

                    Float b = (a[1] * a[1]) - 4 * a[0] * a[2];
                    Double[] c = {((-a[1] + Math.sqrt(b)) / (2 * a[0])),
                            ((-a[1] - Math.sqrt(b)) / (2 * a[0]))
                    };

                    if (b < 0) {
                        espacodelta.setText(b.toString());
                        espacox1.setText("sem resultados reais");
                        espacox2.setText("sem resultados reais");
                    } else if (b == 0) {
                        espacodelta.setText(b.toString());
                        espacox1.setText(c[0].toString());
                        espacox2.setText(c[1].toString());
                    } else {
                        espacodelta.setText(b.toString());
                        espacox1.setText(c[0].toString());
                        espacox2.setText(c[1].toString());
                    } */


                } catch (NumberFormatException exception) {
                    espacodelta.setText("Preencha todos os campos");
                    espacox1.setText("Preencha todos os campos");
                    espacox2.setText("Preencha todos os campos");


                } catch (Exception e) {

                }

            }

        });

    }

    @Override
    public void onClick(View bhaskara) {

        espacodelta.setText("");
        espacox1.setText("");
        espacox2.setText("");

        //O código abaixo identifica o botão apertado e atrubui um valor, verifica se
        //o valor pode ser adicionado, backspace e identifica aonde o valor será atribuido


        if (Teclado.espaco(bhaskara) == 2) {

            espaco2.setText(Teclado.teclado(bhaskara, espaco2.getText().toString()));

        } else if (Teclado.espaco(bhaskara) == 3) {

            espaco3.setText(Teclado.teclado(bhaskara, espaco3.getText().toString()));

        } else {

            espaco1.setText(Teclado.teclado(bhaskara, espaco1.getText().toString()));

        }

    }
}




























