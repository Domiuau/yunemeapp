package com.example.myapplicationz;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.contract.ActivityResultContracts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    static DateFormat formatardata = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    static Date data = new Date();

    public static String dataatual(){
        return formatardata.format(data);

    }

    public static Intent a (Context naoseiqueissokkk,Class tela){
        Intent trocar;
        Teclado.espaco = 1;
        return trocar = new Intent(naoseiqueissokkk,tela);



    }

}
