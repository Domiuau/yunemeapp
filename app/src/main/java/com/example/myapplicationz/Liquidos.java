package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.widget.TextViewCompat;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class Liquidos extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, espaco1, espaco2;
    TextView formula;
    Spinner spinner1, spinner2;
    LinearLayout linear;
    AppCompatImageButton voltar, nbackspace, inverter;

    //é literalmente a mesma coisa da distancia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidos);
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
        espaco1.setOnClickListener(this);
        espaco2.setOnClickListener(this);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!espaco1.getText().toString().isEmpty()){
                    calcular(DB_hist);
                    formula.setText(espaco1.getText().toString() + " ÷ " + selenarformular().toString() + " = " + espaco2.getText().toString());
                }

            }
        });
        inverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = spinner1.getSelectedItemPosition();
                spinner1.setSelection(spinner2.getSelectedItemPosition());
                spinner2.setSelection(a);
                calcular(DB_hist);

                if (espaco1.getText().toString().isEmpty()){
                    limpar();
                } else {
                    calcular(DB_hist);
                }
                if (!formula.getText().toString().isEmpty()){

                    formula.setText(espaco1.getText().toString() + " ÷ " + selenarformular().toString() + " = " + espaco2.getText().toString());
                }
            }
        });
        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                formula.setText("");
                espaco2.setText("");
                calcular(DB_hist);


            }
        });


    }

    void limpar (){
        espaco1.setText("");
        espaco2.setText("");
        formula.setText("");
        espaco1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(espaco1, AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        espaco1.setTextSize(16);
        espaco2.setTextSize(16);
        Data.fluxo = "";
    }

    public BigDecimal selenarformular() {
        return new BigDecimal(Math.pow(10, spinner2.getSelectedItemPosition() - spinner1.getSelectedItemPosition()) + "");
    }

    void calcular(SQLiteDatabase banco) {

        String comp = espaco1.getText().toString() + spinner1.getSelectedItem().toString() + spinner2.getSelectedItem().toString();

        if (!espaco1.getText().toString().isEmpty() && !comp.equals(Data.fluxo)) {

            try {
                if (spinner2.getSelectedItemPosition() < spinner1.getSelectedItemPosition()) {

                    espaco2.setText(Double.parseDouble(espaco1.getText().toString()) / Double.parseDouble(selenarformular() + "") + "");

                } else {

                    espaco2.setText(new BigDecimal(espaco1.getText().toString()).divide(selenarformular()).toString());
                }
                Teclado.adicionarhist(banco, "Liquidos", espaco1.getText().toString() + spinner1.getSelectedItem().toString() + " = " + espaco2.getText().toString() + spinner2.getSelectedItem().toString(),
                        espaco1.getText().toString() + " ÷ " + selenarformular().toString() + " = " + espaco2.getText().toString(), R.drawable.hist_liquidos, comp);
            } catch (Exception e) {

                Toast.makeText(Liquidos.this,"Erro ao caluclar",Toast.LENGTH_SHORT).show();

            }


        } else if (espaco1.getText().toString().isEmpty()){
            Toast.makeText(Liquidos.this,"Insira um número",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View v) {

        Teclado.adicionarnatela(espaco1, v);

    }
}