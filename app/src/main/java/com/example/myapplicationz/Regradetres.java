package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.widget.TextViewCompat;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Regradetres extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos, espaco1, espaco2, espaco3;
    TextView resultado;
    Spinner spinner1;
    AppCompatImageButton nbackspace;
    AppCompatImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regradetres);
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
        espaco3 = findViewById(R.id.espaco3);
        voltar = findViewById(R.id.voltar);
        spinner1 = findViewById(R.id.spinner1);
        resultado = findViewById(R.id.resultado);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(Regradetres.this, R.array.regradetres ,R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinnerdropdown);
        spinner1.setAdapter(adapter);

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
        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calcular(DB_hist);
            }
        });
        nc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                espaco1.setText("");
                espaco2.setText("");
                espaco3.setText("");
                resultado.setText("");
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
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!espaco1.getText().toString().isEmpty() && !espaco2.getText().toString().isEmpty() && !espaco3.getText().toString().isEmpty()){

                    calcular(DB_hist);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
    }

    void calcular(SQLiteDatabase banco){
        String comp = espaco1.getText().toString() + espaco2.getText().toString() + espaco3.getText().toString() + spinner1.getSelectedItemPosition();

        if (!comp.equals(Data.fluxo) && !espaco1.getText().toString().isEmpty()
                && !espaco2.getText().toString().isEmpty()
                && !espaco3.getText().toString().isEmpty()) {

            try {

                if(spinner1.getSelectedItemPosition() == 0){

                    resultado.setText(String.format("%.2f",Double.parseDouble(espaco3.getText().toString()) * Double.parseDouble(espaco2.getText().toString())/Double.parseDouble(espaco1.getText().toString())));

                } else {

                    resultado.setText(String.format("%.2f", Double.parseDouble(espaco1.getText().toString()) * Double.parseDouble(espaco2.getText().toString())/Double.parseDouble(espaco3.getText().toString())));
                }

                Teclado.adicionarhist(banco, "Regra de três", spinner1.getSelectedItem().toString(), "se " + espaco1.getText().toString() + " = " + espaco2.getText().toString() +
                        " então " + espaco3.getText().toString() + " = " + resultado.getText().toString(),R.drawable.hist_regradetres, comp);

            } catch (Exception e){

                Toast.makeText(Regradetres.this, "Não foi possivel calcular", Toast.LENGTH_SHORT).show();
                resultado.setText("");
                Data.fluxo = "";

            }

        } else if (espaco1.getText().toString().isEmpty() || espaco2.getText().toString().isEmpty() || espaco3.getText().toString().isEmpty()) {
            Toast.makeText(Regradetres.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {

        espaco1.setHintTextColor(Color.parseColor("#66737B"));
        espaco2.setHintTextColor(Color.parseColor("#66737B"));
        espaco3.setHintTextColor(Color.parseColor("#66737B"));

        if (Teclado.espaco(v) == 2){

            Teclado.adicionarnatela(espaco2,v);

        } else if (Teclado.espaco(v) == 3){

            Teclado.adicionarnatela(espaco3,v);

        } else {

            Teclado.adicionarnatela(espaco1,v);

        }
    }

}