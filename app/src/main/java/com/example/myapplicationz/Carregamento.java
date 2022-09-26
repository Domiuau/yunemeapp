package com.example.myapplicationz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

        try {
            Thread.sleep(50);

        } catch (Exception e) {
        }


        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Data.at = true;
            Data.atualizardata();
            startActivity(Data.a(Carregamento.this, ferramentastela.class));
        } else {
            startActivity(Data.a(Carregamento.this, inicial.class));
        }

        finish();


    }

}
