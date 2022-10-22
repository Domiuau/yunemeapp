package com.example.myapplicationz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import model.Coisas_hist;
import model.Hist;

public class ferramentastela extends AppCompatActivity implements View.OnClickListener {


    SQLiteDatabase DB_hist;
    ImageButton botaobhaskara, botaodesconto, botaonumerosprimos, botaovelocidade, historico, perfil, temperatura, interrogacao, distancia, liquidos, regradetres;
    TextView bhaskara1, desconto1, numerosprimos1, velocidade1, temperatura1, perfil1, distancia1, liquidos1, regradetres1;
    View quadradoestranho, casinha, calculadora;
    static HistAdapter histAdapter;
    RecyclerView rv;
    FloatingActionButton botaofavoritos;




    public void setDeleteferramenta(String deleteferramenta) {
        this.deleteferramenta = deleteferramenta;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;
    String deleteferramenta;
    boolean fav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ferramentastela);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getWindow().setStatusBarColor(ContextCompat.getColor(ferramentastela.this, R.color.preto));
        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
        DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_coisas (Ferramenta VARCHAR(20),Entrada VARCHAR,Saida VARCHAR,Data VARCHAR,Icone INT)");
        DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_fav (Ferramenta VARCHAR UNIQUE, Icone INT UNIQUE) ");

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
        regradetres = findViewById(R.id.regradetres);
        regradetres1 = findViewById(R.id.regradetres1);
        botaofavoritos = findViewById(R.id.botaofavoritos);
        rv = findViewById(R.id.teste);

        histAdapter = new HistAdapter(new ArrayList<>(Coisas_hist.coisashist()));
        rv.setAdapter(histAdapter);
        rv.scrollToPosition(0);
        histAdapter.notifyItemInserted(0);

        LinearLayoutManager teste = new LinearLayoutManager(ferramentastela.this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(teste);




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
        regradetres.setOnClickListener(this);
        regradetres1.setOnClickListener(this);
        temperatura.setOnClickListener(this);
        liquidos.setOnClickListener(this);
        liquidos1.setOnClickListener(this);
        interrogacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB_hist.execSQL("DELETE FROM TB_fav");
            }
        });
        botaofavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rv.getVisibility() == View.GONE) {
                    fav = true;
                    rv.setVisibility(View.VISIBLE);

                } else {
                    fav = false;
                    rv.setVisibility(View.GONE);
                }


            }
        });
        historico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Data.a(ferramentastela.this, Historico.class));
            }
        });
        perfil.setOnClickListener(this);
        perfil1.setOnClickListener(this);



        try {

            String consulta = "SELECT Ferramenta, Icone FROM TB_fav";
            Cursor cursor = DB_hist.rawQuery(consulta, null);
            cursor.moveToFirst();

            int[] icoisas = {cursor.getColumnIndex("Ferramenta"),
                    cursor.getColumnIndex("Icone")
            };

            while (cursor != null) {

                String[] aa = {
                        cursor.getString(icoisas[0]),
                        cursor.getString(icoisas[1])


                };

                histAdapter.getHists().add(0, Hist.histBuilder.builder()
                        .setFerramenta(aa[0])
                        .setIcone(Integer.parseInt(aa[1]))
                        .build()

                );
                cursor.moveToNext();
            }
        } catch (Exception e) {
        }

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHandler(ItemTouchHelper.DOWN, ItemTouchHelper.UP)
        );

        helper.attachToRecyclerView(rv);



    }

    protected void onStart() {
        super.onStart();

        HistAdapter.u = false;




    }

    @Override
    public void onBackPressed() {

        fav = false;
        rv.setVisibility(View.GONE);

    }

    //pega qual botão foi pressionado e manda para a tela certa


    @Override
    public void onClick(View v) {
        inicial trocar = new inicial();

        switch (v.getId()) {

            case R.id.calculadora:
                startActivity(Data.a(this, pacotes.class));
                break;

            case R.id.quadradoestranho:
                startActivity(Data.a(this, pacotes.class));
                break;

            case R.id.bhaskara:
            case R.id.bhaskara1:
                favoritosoutrocar(Data.a(this, Bhaskara.class), R.drawable.hist_bhaskara, bhaskara1);
                break;

            case R.id.desconto:
            case R.id.desconto1:
                favoritosoutrocar(Data.a(this, Desconto.class), R.drawable.hist_desconto, desconto1);
                break;

            case R.id.numerosprimos:
            case R.id.numerosprimos1:
                favoritosoutrocar(Data.a(this, Numerosprimos.class), R.drawable.hist_primos, numerosprimos1);
                break;

            case R.id.velocidade:
            case R.id.velocidade1:
                favoritosoutrocar(Data.a(this, Velocidade.class), R.drawable.hist_velocidade, velocidade1);
                break;

            case R.id.temperatura:
            case R.id.temperatura1:
                favoritosoutrocar(Data.a(this, Temperatura.class), R.drawable.hist_temperatura, temperatura1);
                break;


            case R.id.historico:
                startActivity(Data.a(this, Historico.class));
                break;

            case R.id.tamanho:
            case R.id.tamanho1:
                favoritosoutrocar(Data.a(this, Distancia.class), R.drawable.ic_distancia, distancia1);
                break;

            case R.id.liquidos:
            case R.id.liquidos1:
                favoritosoutrocar(Data.a(this, Liquidos.class), R.drawable.hist_liquidos, liquidos1);
                break;

            case R.id.regradetres1:
            case R.id.regradetres:
                favoritosoutrocar(Data.a(this, Regradetres.class), R.drawable.hist_regradetres, regradetres1);
                break;


            case R.id.perfil:
            case R.id.perfil1:

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(Data.a(ferramentastela.this, Perfil.class));
                } else {
                    Toast.makeText(ferramentastela.this, "Você precisa estar logado para entrar em seu perfil.", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }


    }


    void favoritosoutrocar(Intent intent, int icone, TextView texto) {

        if (fav == true) {

            SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
            DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_fav (Ferramenta VARCHAR UNIQUE, Icone INT UNIQUE) ");



            try {

                DB_hist.execSQL("INSERT INTO TB_fav (Ferramenta,Icone) VALUES ('" + texto.getText().toString() + "'," + icone + ")");

                histAdapter.getHists().add(0, Hist.histBuilder.builder()
                        .setFerramenta(texto.getText().toString())
                        .setIcone(icone)
                        .build()

                );

                rv.scrollToPosition(0);
                histAdapter.notifyItemInserted(0);

            } catch (Exception e) {


            }


        } else {

            startActivity(intent);
        }

    }

     void deletarbanco(){
         System.out.println(position);
        histAdapter.getHists().remove(position);
        histAdapter.notifyItemRemoved(position);
        DB_hist.execSQL("DELETE FROM TB_fav WHERE Ferramenta = ('" + histAdapter.getHists().get(position).getFerramenta() + "')");




    }

    SQLiteDatabase pegarbanco(){


        return DB_hist;

    }

    void mudatela(){
        //Data.a(ferramentastela.this,Bhaskara.class);
    }


    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {

        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
            DB_hist.execSQL("DELETE FROM TB_fav WHERE Ferramenta = ('" + histAdapter.getHists().get(viewHolder.getAdapterPosition()).getFerramenta() + "')");
            System.out.println(histAdapter.getHists().get(viewHolder.getAdapterPosition()).getFerramenta());
            histAdapter.getHists().remove(viewHolder.getAdapterPosition());
            histAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            //System.out.println(histAdapter.getHists().get(viewHolder.getAdapterPosition()).getFerramenta());


            System.out.println(viewHolder.getAdapterPosition());


        }
    }




}


