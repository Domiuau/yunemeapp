package com.example.myapplicationz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.checkerframework.checker.units.qual.C;

public class login extends AppCompatActivity {

    AppCompatImageButton voltar;
    EditText email, senha;
    AppCompatButton login;
    FirebaseAuth autenticar = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplicationz);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Data.mudarcorstatusbar(getWindow(),login.this,R.color.verdeouazulsla);

        voltar = findViewById(R.id.voltar);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            //procura pelo usuario no banco de dados e entra na conta caso ela for encontrada

            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty() ||
                        senha.getText().toString().isEmpty()) {
                    toask("Preencha todos as campos");


                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Data.atualizardata();
                                startActivity(Data.a(login.this, ferramentastela.class));



                            } else {
                                try {
                                    throw task.getException();

                                } catch (FirebaseNetworkException e) {
                                    toask("Sem acesso a internet");
                                } catch (Exception e) {
                                    toask("Conta não encontrada");

                                }
                            }

                        }
                    });


                }


            }
        });


    }

    void w (){
        startActivity(Data.a(login.this, ferramentastela.class));
    }

    void toask(String msg) {

        Toast.makeText(login.this, msg, Toast.LENGTH_SHORT).show();

    }
}