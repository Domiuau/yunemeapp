package com.example.myapplicationz;

import android.view.View;
import android.webkit.WebSettings;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

public class Teclado extends AppCompatActivity {
    //teclado universal para todas as ferramentas

    static byte espaco = 1;

    static byte espaco(View q) {


        switch (q.getId()) {


            case R.id.espaco1:
                espaco = 1;
                break;

            case R.id.espaco2:
                espaco = 2;
                break;

            case R.id.espaco3:
                espaco = 3;
                break;

        }

        return espaco;
    }


    static String teclado(View tecladouniversal, String valores) {
        String valor;

        switch (tecladouniversal.getId()) {

            case R.id.n1:
                valor = valores+="1";
                break;

            case R.id.n2:
                valor = valores+="2";
                break;

            case R.id.n3:
                valor = valores+="3";
                break;

            case R.id.n4:
                valor = valores+="4";
                break;

            case R.id.n5:
                valor = valores+="5";
                break;

            case R.id.n6:
                valor = valores+="6";
                break;

            case R.id.n7:
                valor = valores+="7";
                break;

            case R.id.n8:
                valor = valores+="8";
                break;

            case R.id.n9:
                valor = valores+="9";
                break;

            case R.id.n0:
                valor = valores+="0";
                break;

            case R.id.nvirgula:
                if (!valores.contains(".") && !valores.isEmpty() && !valores.endsWith("-")) {
                    System.out.println("aaafd");
                    valor = valores+=".";
                } else {
                    valor = valores+="";
                    System.out.println("afd");
                }
                break;

            case R.id.nmenos:
                if (valores.isEmpty()) {
                    valor = valores+="-";
                } else {
                    valor = valores+="";
                }
                break;

            case R.id.nbackspace:
                String[] q = valores.split("");
                String z = "";
                for (int i = 0; i < q.length - 1; ++i) {
                    z += q[i];

                }


                valor = z;
                break;


            default:
                valor = valores+="";


        }

        return valor;

    }

    static int tamanhofonte (boolean vazio){
        if (vazio == true){
            return 16;
        } else {
            return 18;
        }
    }




}


























