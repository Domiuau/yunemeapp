package com.example.myapplicationz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class cadastrar extends AppCompatActivity {

    AppCompatImageButton voltar;
    EditText usuario, email, senha, confirmarsenha;
    AppCompatButton cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getWindow().setStatusBarColor(ContextCompat.getColor(cadastrar.this, R.color.verdeouazulsla));

        voltar = findViewById(R.id.voltar);
        usuario = findViewById(R.id.usuario);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        confirmarsenha = findViewById(R.id.confirmarsenha);
        cadastrar = findViewById(R.id.cadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuario.getText().toString().isEmpty() ||
                        email.getText().toString().isEmpty() ||
                        senha.getText().toString().isEmpty() ||
                        confirmarsenha.getText().toString().isEmpty()
                ) {
                    toask("Preencha todos os campos");


                } else if (!senha.getText().toString().equals(confirmarsenha.getText().toString())) {
                    toask("Senhas diferentes");

                } else if (senha.getText().toString().length() < 6 ||
                        confirmarsenha.getText().toString().length() < 6) {
                    toask("A senha deve ter pelo menos 6 caracteres");

                } else {


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                toask("Cadastrado com sucesso!");
                                startActivity(Data.a(cadastrar.this, ferramentastela.class));

                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            toask("Erro");


                        }
                    });


                }


            }
        });


    }

    void toask(String msg) {

        Toast.makeText(cadastrar.this, msg, Toast.LENGTH_SHORT).show();

    }



}