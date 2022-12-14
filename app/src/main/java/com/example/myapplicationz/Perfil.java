package com.example.myapplicationz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;


public class Perfil extends AppCompatActivity {

    AppCompatButton deslogar, trocarconta;
    ImageView imagem;
    TextView nome, email, realizadas, ultimaentrada, dataregistro;
    AppCompatImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        deslogar = findViewById(R.id.deslogar);
        trocarconta = findViewById(R.id.alterarconta);
        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        realizadas = findViewById(R.id.realizadas);
        ultimaentrada = findViewById(R.id.ultimoacesso);
        dataregistro = findViewById(R.id.dataregistro);
        voltar = findViewById(R.id.voltar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(Data.a(Perfil.this, inicial.class));

            }
        });

        trocarconta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(Data.a(Perfil.this, login.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //recupera os valores do banco de dados do usuario e exibi na tela

        DocumentReference referencia = FirebaseFirestore.getInstance().collection("Usuarios").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        referencia.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {

                    nome.setText(value.getString("nome"));
                    email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    dataregistro.setText(value.getString("dataderegistro"));
                    realizadas.setText(value.getLong("realizadas").toString());
                    ultimaentrada.setText(Data.entrada);


                }
            }
        });


    }

}