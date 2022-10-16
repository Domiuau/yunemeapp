package com.example.myapplicationz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

public class Carregamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SQLiteDatabase DB_hist = openOrCreateDatabase("DB_historico", MODE_PRIVATE, null);
        DB_hist.execSQL("CREATE TABLE IF NOT EXISTS TB_coisas (Ferramenta VARCHAR(20),Entrada VARCHAR,Saida VARCHAR,Data VARCHAR,Icone INT)");

        try {
            Thread.sleep(500);

        } catch (Exception e) {
        }

        //caso o usuario esteja logado, ele vai para a tela de ferramentas normal, mas se não estiver, vai para a tela inicial, pode poderá logar, criar conta ou entrar como visitante

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Data.atualizardata();
            startActivity(Data.a(Carregamento.this, ferramentastela.class));
        } else {
            startActivity(Data.a(Carregamento.this, inicial.class));
        }

        finish();


    }

}
