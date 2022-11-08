package com.example.myapplicationz;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.widget.TextViewCompat;

public class Teclado extends AppCompatActivity {
    //teclado universal para todas as ferramentas

    static byte espaco = 1;

    //retorna em qual espaco o numero deverá ser exibido na tela

    static byte espaco(View q) {


        switch (q.getId()) {


            case R.id.espaco1:
            case R.id.relative1:
                espaco = 1;
                break;

            case R.id.espaco2:
            case R.id.relative2:
                espaco = 2;
                break;

            case R.id.espaco3:
            case R.id.relative3:
                espaco = 3;
                break;

        }

        return espaco;
    }

    //retorna o valor atribuido para o botão pressionado, backspace e se um operador pode ser adicionado ou não

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

    //retorna um tamanho de fonte

    static int tamanhofonte (boolean vazio){
        if (vazio == true){
            return 16;
        } else {
            return 18;
        }
    }

    //esse metodo foi criado para resolver o bug do hint, onde ele quebrava totalmente quando um texto mudava de tamanho


    static void adicionarnatela (AppCompatButton botao, View view){

        botao.setText(Teclado.teclado(view, botao.getText().toString().replace("%","")));
        botao.setTextSize(Teclado.tamanhofonte(botao.getText().toString().isEmpty()));
        botao.setHintTextColor(Color.parseColor("#C09B33"));

        if(botao.getText().toString().isEmpty()){

            botao.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            TextViewCompat.setAutoSizeTextTypeWithDefaults(botao,AppCompatButton.AUTO_SIZE_TEXT_TYPE_NONE);
            botao.setTextSize(16);


        }else{

            botao.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            TextViewCompat.setAutoSizeTextTypeWithDefaults(botao,AppCompatButton.AUTO_SIZE_TEXT_TYPE_UNIFORM);
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(botao,6,18,1,2);

        }
    }

    //pega os valores passados ao fazer uma operação e adiciona ao banco de dados do SQLite, os dados serão usados para serem exibidos no historico

    static void adicionarhist (SQLiteDatabase banco,String ferramenta,String entrada, String saida, int icone,String comp){

        banco.execSQL("INSERT INTO TB_coisas (Ferramenta, Entrada, Saida, Data, Icone) VALUES ('" + ferramenta + "', '" + entrada + "','" + saida + "', '" + Data.dataatual() + "' ," + icone + ")");
        Data.atualizaradicionadas();
        Data.fluxo = comp;
    }





}


























