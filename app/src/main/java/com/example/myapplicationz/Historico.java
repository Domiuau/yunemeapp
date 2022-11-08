package com.example.myapplicationz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import model.Coisas_hist;
import model.Hist;

public class Historico extends AppCompatActivity implements View.OnClickListener {


    HistAdapter histAdapter;
    RecyclerView rv;
    AppCompatImageButton limpar, voltar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        HistAdapter.u = true;
        limpar = findViewById(R.id.limpar);
        voltar = findViewById(R.id.voltar);

        histAdapter = new HistAdapter(new ArrayList<>(Coisas_hist.coisashist()));
        rv = findViewById(R.id.recycler);
        rv.setAdapter(histAdapter);
        rv.scrollToPosition(0);
        histAdapter.notifyItemInserted(0);



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DB_hist.execSQL("DELETE FROM TB_coisas");
                finish();
                startActivity(Data.a(Historico.this,Historico.class));


                }


        });

        //quando a tela é iniciada os valores do bando de dados SQLite são recuperados e exibidos na tela


        try {

            String consulta = "SELECT Ferramenta, Entrada, Saida, Data, Icone FROM TB_coisas";
            Cursor cursor = DB_hist.rawQuery(consulta, null);
            cursor.moveToFirst();

            int[] icoisas = {cursor.getColumnIndex("Ferramenta"),
                    cursor.getColumnIndex("Entrada"),
                    cursor.getColumnIndex("Saida"),
                    cursor.getColumnIndex("Data"),
                    cursor.getColumnIndex("Icone")
            };

            while (cursor != null) {

                String[] aa = {
                        cursor.getString(icoisas[0]),
                        cursor.getString(icoisas[1]),
                        cursor.getString(icoisas[2]),
                        cursor.getString(icoisas[3]),
                        cursor.getString(icoisas[4])


                };

                histAdapter.getHists().add(0, Hist.histBuilder.builder()
                        .setFerramenta(aa[0])
                        .setDadosentrada(aa[1])
                        .setDadossaida(aa[2])
                        .setData(aa[3])
                        .setIcone(Integer.parseInt(aa[4]))
                        .build()

                );
                cursor.moveToNext();
            }
        } catch (Exception e) {
        }



       ItemTouchHelper helper = new ItemTouchHelper(
               new ItemTouchHandler(ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
       );

        helper.attachToRecyclerView(rv);
    }

    @Override
    public void onClick(View v) {

    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback{

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
            DB_hist.execSQL("DELETE FROM TB_coisas WHERE Data = ('" + histAdapter.getHists().get(viewHolder.getAdapterPosition()).getData() + "') AND Saida = ('" + histAdapter.getHists().get(viewHolder.getAdapterPosition()).getDadossaida() + "') ");
            System.out.println(histAdapter.getHists().get(viewHolder.getAdapterPosition()).getFerramenta());
            histAdapter.getHists().remove(viewHolder.getAdapterPosition());
            histAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            //System.out.println(histAdapter.getHists().get(viewHolder.getAdapterPosition()).getFerramenta());


            System.out.println(viewHolder.getAdapterPosition());


        }
    }


}




