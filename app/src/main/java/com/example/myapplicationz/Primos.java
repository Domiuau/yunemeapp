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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Primos extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton n1, n2, n3, n4, n5, n6, n7, n8, n9, n0, nvirgula, nc, nok, nmenos;
    AppCompatImageButton nbackspace;
    TextView diferencamenor, diferencamaior, menorque, maiorque, resultado, botao, diferencamenor1, diferencamaior1;
    AppCompatImageButton voltar;
    RelativeLayout espaco1;
    int me, b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerosprimos);
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
        diferencamenor = findViewById(R.id.diferencamenor);
        diferencamenor1 = findViewById(R.id.diferencamenor1);
        diferencamaior = findViewById(R.id.diferencamaior);
        diferencamaior1 = findViewById(R.id.diferencamaior1);
        menorque = findViewById(R.id.menorque);
        maiorque = findViewById(R.id.maiorque);
        resultado = findViewById(R.id.resultado);
        voltar = findViewById(R.id.voltar);
        botao = findViewById(R.id.botao);


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

        //exibi os resultados na tela, mecanismo para não poluir o historico e adicionar os valores no historico.

        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Data.fluxo.equals(botao.getText().toString()) && !botao.getText().toString().equals("")) {

                    try {

                        if (primo(Integer.parseInt(botao.getText().toString()))) {
                            resultado.setText(botao.getText().toString() + " é primo!");
                        } else {
                            resultado.setText(botao.getText().toString() + " não é primo!");
                        }

                        me = Integer.parseInt(botao.getText().toString());

                        System.out.println(me + "aaa");

                        while (!primo(me - 1)) {
                            me--;
                        }
                        menorque.setText(me - 1 + "");
                        diferencamenor.setText(" " + ((Integer.parseInt(botao.getText().toString()) - (me - 1)) * -1 + " "));
                        me = Integer.parseInt(botao.getText().toString());

                        while (!primo(me + 1)) {
                            me++;
                        }
                        maiorque.setText(me + 1 + "");
                        diferencamaior.setText(" " + ((me + 1) - Integer.parseInt(botao.getText().toString())) + " ");

                        Teclado.adicionarhist(DB_hist,"Números primos", "O número " + resultado.getText().toString(),
                                "Mais proximos: " + menorque.getText().toString() + " < " + botao.getText().toString() + " > " + maiorque.getText().toString(),
                                R.drawable.hist_primos,botao.getText().toString()  );

                        diferencamenor1.setText("Diferença para o menor");
                        diferencamaior1.setText("Diferença para o maior");

                    } catch (Exception e) {
                        Toast.makeText(Primos.this, "Erro ao calcular", Toast.LENGTH_SHORT).show();
                        limpar();

                    }
                } else if (botao.getText().toString().equals("")) {
                    Toast.makeText(Primos.this, "Insira um número", Toast.LENGTH_SHORT).show();
                    limpar();

                }


            }
        });

    }

    void limpar() {
        diferencamaior.setText("");
        diferencamenor.setText("");
        maiorque.setText("");
        menorque.setText("");
        botao.setText("");
        resultado.setText("");
        diferencamenor1.setText("");
        diferencamaior1.setText("");
        Data.fluxo = "";
        botao.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(botao,AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
        Data.fluxo = "";
    }

    //retorna se o número é primo ou não

    boolean primo(int a) {
        b = 0;
        for (int i = a - 1; i > 1; --i) {
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

        //teclado

    @Override
    public void onClick(View v) {

        botao.setText(Teclado.teclado(v, botao.getText().toString()));


    }

}