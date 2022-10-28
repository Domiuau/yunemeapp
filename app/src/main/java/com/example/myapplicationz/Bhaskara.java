package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import model.Coisas_hist;
import model.Hist;

public class Bhaskara extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2, espaco3;
    AppCompatImageButton nbackspace;
    TextView espacox1, espacox2, espacodelta;
    AppCompatImageButton voltar;
    RelativeLayout c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bhaskara);
        Data.mudarcorstatusbar(getWindow(), Bhaskara.this, R.color.preto);
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
        c1 = findViewById(R.id.relative1);
        c2 = findViewById(R.id.relative2);
        c3 = findViewById(R.id.relative3);
        espaco1 = findViewById(R.id.espaco1);
        espaco2 = findViewById(R.id.espaco2);
        espaco3 = findViewById(R.id.espaco3);
        espacox1 = findViewById(R.id.espacox1);
        espacox2 = findViewById(R.id.espacox2);
        espacodelta = findViewById(R.id.espacodelta);
        voltar = findViewById(R.id.voltar);
        Data.fluxo = "";

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
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
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);

        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                limpar();


            }
        });


        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //delta = 0 -> "1 -10 25"
                //mecanismo para não poluir o historico caso o usuario aperte para calcular varias vezes, calculo de bhaskara e adicionar os valores no historico

                String comp = espaco1.getText().toString() + espaco2.getText().toString() + espaco3.getText().toString();

                if (!comp.equals(Data.fluxo) && !espaco1.getText().toString().isEmpty()
                        && !espaco2.getText().toString().isEmpty()
                        && !espaco3.getText().toString().isEmpty()) {


                    try {

                        Float[] a = {
                                Float.parseFloat(espaco1.getText().toString()),
                                Float.parseFloat(espaco2.getText().toString()),
                                Float.parseFloat(espaco3.getText().toString())
                        };

                        if ((a[1] * a[1]) - 4 * a[0] * a[2] < 0) {
                            espacodelta.setText((a[1] * a[1]) - 4 * a[0] * a[2] + "");
                            espacox1.setText("NaN");
                            espacox2.setText("NaN");
                        } else if ((a[1] * a[1]) - 4 * a[0] * a[2] == 0) {
                            espacodelta.setText((a[1] * a[1]) - 4 * a[0] * a[2] + "");
                            espacox1.setText((-a[1] + Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");
                            espacox2.setText((-a[1] - Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");
                        } else {
                            espacodelta.setText((a[1] * a[1]) - 4 * a[0] * a[2] + "");
                            espacox1.setText((-a[1] + Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");
                            espacox2.setText((-a[1] - Math.sqrt((a[1] * a[1]) - 4 * a[0] * a[2])) / (2 * a[0]) + "");

                        }

                        Teclado.adicionarhist(DB_hist, "Bhaskara", "A = " + a[0] + " B = " + a[1] + " C = " + a[2],
                                "Delta = " + String.format("%.2f", Double.parseDouble(espacodelta.getText().toString())) +
                                        " / X1 = " + String.format("%.5f", Double.parseDouble(espacox1.getText().toString())) +
                                        " / X2 = " + String.format("%.5f", Double.parseDouble(espacox2.getText().toString())),
                                R.drawable.hist_bhaskara, comp
                        );


                    } catch (Exception e) {
                        Toast.makeText(Bhaskara.this, "Não foi possivel calcular", Toast.LENGTH_SHORT).show();
                        espacodelta.setText("");
                        espacox1.setText("");
                        espacox2.setText("");
                        Data.fluxo = "";


                    }
                } else if (espaco1.getText().toString().isEmpty() || espaco2.getText().toString().isEmpty() || espaco3.getText().toString().isEmpty()) {
                    Toast.makeText(Bhaskara.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
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


            }

        });


    }

    //limpa tudo

    void limpar() {
        espaco1.setText("");
        espaco2.setText("");
        espaco3.setText("");
        espacodelta.setText("");
        espacox1.setText("");
        espacox2.setText("");
        espaco2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        espaco1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        espaco3.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco1, AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco2, AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco3, AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        espaco1.setTextSize(16);
        espaco2.setTextSize(16);
        espaco3.setTextSize(16);
        Data.fluxo = "";
    }


    @Override
    public void onClick(View bhaskara) {


        //chamando os metodos responsaveis pelo teclado


        if (Teclado.espaco(bhaskara) == 2) {

            Teclado.adicionarnatela(espaco2, bhaskara);

        } else if (Teclado.espaco(bhaskara) == 3) {

            Teclado.adicionarnatela(espaco3, bhaskara);

        } else {

            Teclado.adicionarnatela(espaco1, bhaskara);

        }


    }

    public void onStart() {
        super.onStart();


    }
}































