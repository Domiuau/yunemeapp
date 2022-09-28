package com.example.myapplicationz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

    AppCompatButton deslogar, testes;
    ImageView imagem;
    TextView usuario, email, realizadas,ultimaentrada,dataregistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        deslogar = findViewById(R.id.desligar);
        imagem = findViewById(R.id.imagem);
        usuario = findViewById(R.id.usuario);
        email = findViewById(R.id.email);
        testes = findViewById(R.id.testes);
        realizadas = findViewById(R.id.realizadas);
        ultimaentrada = findViewById(R.id.ultimaentrada);
        dataregistro = findViewById(R.id.dataregistro);


        deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(Data.a(Perfil.this, inicial.class));

            }
        });

        testes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Data.atualizaradicionadas();




            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        ultimaentrada.setText("Ultima entrada: " +  Data.entrada);



        DocumentReference referencia = FirebaseFirestore.getInstance().collection("Usuarios").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        referencia.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null){
                    usuario.setText("Nome de usuário: " + value.getString("nome"));
                    email.setText("Email: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    dataregistro.setText("Data de registro: " + value.getString("dataderegistro"));
                    realizadas.setText("Conversões realizadas na conta: " + value.getLong("realizadas").toString());


                }
            }
        });



    }

}