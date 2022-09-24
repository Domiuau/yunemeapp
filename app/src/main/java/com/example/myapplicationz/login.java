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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    AppCompatImageButton voltar;
    EditText email,senha;
    AppCompatButton login;
    FirebaseAuth autenticar = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(login.this,R.color.verdeouazulsla));

        voltar = findViewById(R.id.voltar);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty() ||
                senha.getText().toString().isEmpty()){
                    toask("Preencha todos as campos");


                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(Data.a(login.this,ferramentastela.class));
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toask("Usuário não encontrado");

                        }
                    });



                }



            }
        });




    }

    void toask(String msg) {

        Toast.makeText(login.this, msg, Toast.LENGTH_SHORT).show();

    }
}