package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class cadastrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        getWindow().setStatusBarColor(ContextCompat.getColor(cadastrar.this,R.color.verdeouazulsla));
    }
}