package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
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

public class Historico extends AppCompatActivity {


    public static SQLiteDatabase DB_hist;
    HistAdapter histAdapter;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        histAdapter = new HistAdapter(new ArrayList<>(Coisas_hist.coisashist()));
        rv = findViewById(R.id.recycler);
        rv.setAdapter(histAdapter);
        rv.scrollToPosition(0);
        histAdapter.notifyItemInserted(0);

        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);


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


    }


}




