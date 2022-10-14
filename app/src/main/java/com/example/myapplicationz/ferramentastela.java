package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ferramentastela extends AppCompatActivity implements View.OnClickListener {

    ImageButton quadradoestranho, calculadora, botaobhaskara, botaodesconto, casinha, botaonumerosprimos, botaovelocidade, historico, perfil, temperatura,interrogacao,distancia,liquidos;
    TextView bhaskara1, desconto1, numerosprimos1, velocidade1, temperatura1,perfil1,distancia1,liquidos1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ferramentastela);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getWindow().setStatusBarColor(ContextCompat.getColor(ferramentastela.this, R.color.preto));
        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
        DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_coisas (Ferramenta VARCHAR(20),Entrada VARCHAR,Saida VARCHAR,Data VARCHAR,Icone INT)");

        botaobhaskara = findViewById(R.id.bhaskara);
        botaodesconto = findViewById(R.id.desconto);
        casinha = findViewById(R.id.casinha);
        quadradoestranho = findViewById(R.id.quadradoestranho);
        calculadora = findViewById(R.id.calculadora);
        botaonumerosprimos = findViewById(R.id.numerosprimos);
        botaovelocidade = findViewById(R.id.velocidade);
        bhaskara1 = findViewById(R.id.bhaskara1);
        desconto1 = findViewById(R.id.desconto1);
        numerosprimos1 = findViewById(R.id.numerosprimos1);
        velocidade1 = findViewById(R.id.velocidade1);
        historico = findViewById(R.id.historico);
        temperatura = findViewById(R.id.temperatura);
        temperatura1 = findViewById(R.id.temperatura1);
        perfil = findViewById(R.id.perfil);
        perfil1 = findViewById(R.id.perfil1);
        interrogacao = findViewById(R.id.interrogacao);
        distancia = findViewById(R.id.tamanho);
        distancia1 = findViewById(R.id.tamanho1);
        liquidos = findViewById(R.id.liquidos);
        liquidos1 = findViewById(R.id.liquidos1);


        botaobhaskara.setOnClickListener(this);
        historico.setOnClickListener(this);
        botaodesconto.setOnClickListener(this);
        casinha.setOnClickListener(this);
        quadradoestranho.setOnClickListener(this);
        calculadora.setOnClickListener(this);
        botaonumerosprimos.setOnClickListener(this);
        botaovelocidade.setOnClickListener(this);
        bhaskara1.setOnClickListener(this);
        desconto1.setOnClickListener(this);
        numerosprimos1.setOnClickListener(this);
        velocidade1.setOnClickListener(this);
        distancia.setOnClickListener(this);
        distancia1.setOnClickListener(this);
        temperatura1.setOnClickListener(this);
        temperatura.setOnClickListener(this);
        liquidos.setOnClickListener(this);
        liquidos1.setOnClickListener(this);
        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Data.a(ferramentastela.this,Historico.class));
            }
        });
        perfil.setOnClickListener(this);
        perfil1.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {

    }


    @Override
    public void onClick(View v) {
        inicial trocar = new inicial();

        switch (v.getId()) {

            case R.id.casinha:
                startActivity(Data.a(this, ferramentastela.class));
                break;

            case R.id.calculadora:
                startActivity(Data.a(this, calculadora.class));
                break;

            case R.id.quadradoestranho:
                startActivity(Data.a(this, pacotes.class));
                break;

            case R.id.bhaskara:
            case R.id.bhaskara1:
                startActivity(Data.a(this, Bhaskara.class));
                break;

            case R.id.desconto:
            case R.id.desconto1:
                startActivity(Data.a(this, Desconto.class));
                break;

            case R.id.numerosprimos:
            case R.id.numerosprimos1:
                startActivity(Data.a(this, Numerosprimos.class));
                break;

            case R.id.velocidade:
            case R.id.velocidade1:
                startActivity(Data.a(this, Velocidade.class));
                break;

            case R.id.temperatura:
            case R.id.temperatura1:
                startActivity(Data.a(this, Temperatura.class));
                break;


            case R.id.historico:
                startActivity(Data.a(this, Historico.class));
                break;

            case R.id.tamanho:
            case R.id.tamanho1:
                startActivity(Data.a(this, Distancia.class));
                break;

            case R.id.liquidos:
            case R.id.liquidos1:
                startActivity(Data.a(this, Liquidos.class));
                break;


            case R.id.perfil:
            case R.id.perfil1:

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(Data.a(ferramentastela.this, Perfil.class));
                } else {
                    Toast.makeText(ferramentastela.this,"Você precisa estar logado para entrar em seu perfil.",Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }


    }


}


