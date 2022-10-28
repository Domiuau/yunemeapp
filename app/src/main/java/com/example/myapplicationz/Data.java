package com.example.myapplicationz;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Data {

    static DateFormat formatardata = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    static Date data = new Date();
    static ImageView imagemnova;
    static int n = 0;
    static long i;
    static String entrada;
    static boolean at, hist;
    static String[] vetor;
    static String fluxo = "";
    static String valorentrada,valorsaida;




    //classe que eu criei so para pegar a data atual, mas acabei usando ela para varias outros metodos


    //retorna a data atual

    public static String dataatual() {
        return formatardata.format(data);

    }

    //retorna um intent para trocar de tela

    public static Intent a (Context naoseiqueissokkk, Class tela) {
        Intent trocar;
        Teclado.espaco = 1;
        return trocar = new Intent(naoseiqueissokkk, tela);


    }

    //pega o usuario logado no momento e adiciona +1 nas conversões realizadas na conta

    static void atualizaradicionadas(){
        try {
            at = true;
            DocumentReference referencia = FirebaseFirestore.getInstance().collection("Usuarios").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            referencia.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value != null && at == true){
                        DocumentReference referencia = FirebaseFirestore.getInstance().collection("Usuarios").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        referencia.update("realizadas",value.getLong("realizadas")+1);
                        at = false;

                    }
                }
            });

        } catch (Exception e){

        }

    }

    //muda a cor da statusbar

    static void mudarcorstatusbar(Window window,Context context, int cor){
        window.setStatusBarColor(ContextCompat.getColor(context, cor));

    }










    static void atualizardata() {
        at = true;
        DocumentReference referencia = FirebaseFirestore.getInstance().collection("Usuarios").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        referencia.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && at == true) {
                    Data.entrada = value.getString("ultimaentrada");
                    referencia.update("ultimaentrada", Data.dataatual());
                    at = false;

                }
            }
        });

    }

    static void spinner (Spinner spinner1,Spinner spinner2, int array, Context context){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context, array ,R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinnerdropdown);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }








}



