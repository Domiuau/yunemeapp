package com.example.myapplicationz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.math.BigDecimal;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class calculadora extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton c, divisao, porcentagem, n1, n2, n3, n4, n5, n6, n7, n8, n9, vezes, menos, mais, iguala, virgula, n0,nfatorial;
    AppCompatImageButton backspace;
    AppCompatTextView expressao, resultado;
    AppCompatImageView voltar;
    String[] z, q;
    Double ultimonumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        c = findViewById(R.id.c);
        divisao = findViewById(R.id.divisao);
        porcentagem = findViewById(R.id.porcentagem);
        n0 = findViewById(R.id.n0);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        n6 = findViewById(R.id.n6);
        n7 = findViewById(R.id.n7);
        n8 = findViewById(R.id.n8);
        n9 = findViewById(R.id.n9);
        vezes = findViewById(R.id.vezes);
        menos = findViewById(R.id.menos);
        mais = findViewById(R.id.mais);
        iguala = findViewById(R.id.iguala);
        virgula = findViewById(R.id.virgula);
        backspace = findViewById(R.id.backspace);
        expressao = findViewById(R.id.expressao);
        resultado = findViewById(R.id.resultado);
        nfatorial = findViewById(R.id.nfatorial);
        voltar = findViewById(R.id.voltar);

        c.setOnClickListener(this);
        divisao.setOnClickListener(this);
        porcentagem.setOnClickListener(this);
        n0.setOnClickListener(this);
        n1.setOnClickListener(this);
        n2.setOnClickListener(this);
        n3.setOnClickListener(this);
        n4.setOnClickListener(this);
        n5.setOnClickListener(this);
        n6.setOnClickListener(this);
        n7.setOnClickListener(this);
        n8.setOnClickListener(this);
        n9.setOnClickListener(this);
        vezes.setOnClickListener(this);
        menos.setOnClickListener(this);
        mais.setOnClickListener(this);
        iguala.setOnClickListener(this);
        virgula.setOnClickListener(this);
        backspace.setOnClickListener(this);
        nfatorial.setOnClickListener(this);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void adicionar(String valor) {
        expressao.append(valor.replace("*","x"));
        resultado.setText("");
    }

    public void adicionarvetor() {
        z = expressao.getText().toString().split("");
        //for (int i = 0;i< z.length;i++){
        //System.out.println(z[i]);
        //}

    }

    public void backspace() {
        adicionarvetor();
        expressao.setText("");
        for (int i = 0; i < z.length - 1; ++i) {
            adicionar(z[i]);
        }

    }

    public void separarporoperador() {
        q = expressao.getText().toString()
                .replace("-", " ")
                .replace("+", " ")
                .replace("÷", " ")
                .replace("x", " ")
                .split(" ");
    }

    public void compararoperador(String operador) {

        if (!z[z.length - 1].equals("-") &&
                !z[z.length - 1].equals("+") &&
                !z[z.length - 1].equals("x") &&
                !z[z.length - 1].equals("÷") &&
                !z[z.length - 1].equals("-") &&
                !z[z.length - 1].equals(".") &&
                !expressao.getText().toString().isEmpty()) {
            adicionar(operador);
            System.out.println(operador);
            System.out.println(z[z.length - 1]);
        }

    }

    @Override
    public void onClick(View teclado) {

        switch (teclado.getId()) {
            case R.id.c:
                expressao.setText("");
                resultado.setText("");
                break;
            case R.id.porcentagem:
                separarporoperador();
                try{

                ultimonumero = Double.parseDouble(q[q.length - 1]);

                    if (ultimonumero > 0.099999) {

                        if (!expressao.getText().toString().endsWith("+") &&
                                !expressao.getText().toString().endsWith("-") &&
                                !expressao.getText().toString().endsWith("÷") &&
                                !expressao.getText().toString().endsWith("x")) {



                            if (expressao.getText().toString().contains("+") ||
                                    expressao.getText().toString().contains("-") ||
                                    expressao.getText().toString().contains("÷") ||
                                    expressao.getText().toString().contains("x")) {

                                while (!expressao.getText().toString().endsWith("x") &&
                                        !expressao.getText().toString().endsWith("-") &&
                                        !expressao.getText().toString().endsWith("+") &&
                                        !expressao.getText().toString().endsWith("÷")) {
                                    backspace();
                                }


                            } else {
                                expressao.setText("");

                            }


                            ultimonumero = Double.parseDouble(q[q.length - 1]);
                            adicionar(String.valueOf(ultimonumero / 100));



                        }

                    }

                }catch (Exception e) {

                }

                break;
            case R.id.n0:
                adicionar("0");
                break;
            case R.id.n1:
                adicionar("1");
                break;
            case R.id.n2:
                adicionar("2");
                break;
            case R.id.n3:
                adicionar("3");
                break;
            case R.id.n4:
                adicionar("4");
                break;
            case R.id.n5:
                adicionar("5");
                break;
            case R.id.n6:
                adicionar("6");
                break;
            case R.id.n7:
                adicionar("7");
                break;
            case R.id.n8:
                adicionar("8");
                ;
                break;
            case R.id.n9:
                adicionar("9");
                break;
            case R.id.vezes:
                adicionarvetor();
                compararoperador("x");
                break;
            case R.id.menos:
                adicionarvetor();
                if (!z[z.length - 1].equals("-") &&
                        !z[z.length - 1].equals(".")) {
                    adicionar("-");
                }
                break;
            case R.id.mais:
                adicionarvetor();
                compararoperador("+");
                break;
            case R.id.divisao:
                adicionarvetor();
                compararoperador("÷");
                break;
            case R.id.virgula:
                adicionarvetor();
                separarporoperador();

                try {
                    if (!q[q.length - 1].contains(".") &&
                            !expressao.getText().toString().isEmpty() &&
                            !expressao.getText().toString().endsWith("+") &&
                            !expressao.getText().toString().endsWith("-") &&
                            !expressao.getText().toString().endsWith("÷") &&
                            !expressao.getText().toString().endsWith("x")
                    ) {
                        adicionar(".");
                    }
                } catch (Exception E) {
                }

                break;


            case R.id.backspace:
                backspace();
                break;

            case R.id.iguala:

                if (!expressao.getText().toString().contains("E")){

                    try {
                        Expression resolver = new ExpressionBuilder(expressao.getText().toString()
                                .replace("x","*")
                                .replace("÷","/")
                                .replace(",","."))
                                .build();
                        double resultadofinalreal = resolver.evaluate();
                        long resultadofinal = (long) resultadofinalreal;


                        if (resultadofinalreal == (double) resultadofinal) {
                            resultado.setText(String.valueOf(resultadofinal));
                        } else {
                            resultado.setText(String.valueOf(resultadofinalreal));
                        }


                    } catch (ArithmeticException exception) {
                        resultado.setText("Não é possível dividir por zero");
                    } catch (Exception e) {

                    }

                } else {

                    resultado.setText("Infinity");

                }

                break;

            case R.id.nfatorial:
                separarporoperador();

                try {
                    BigDecimal ultimo;
                    BigDecimal fatorialresultado = new BigDecimal((q[q.length - 1]));


                    if (expressao.getText().toString().contains("+") ||
                            expressao.getText().toString().contains("-") ||
                            expressao.getText().toString().contains("÷") ||
                            expressao.getText().toString().contains("x")) {

                        while (!expressao.getText().toString().endsWith("x") &&
                                !expressao.getText().toString().endsWith("-") &&
                                !expressao.getText().toString().endsWith("+") &&
                                !expressao.getText().toString().endsWith("÷")) {
                            backspace();
                        }


                    } else {
                        expressao.setText("");

                    }

                    for (int i = Integer.parseInt((q[q.length - 1]))-1;i>1;i--){
                        ultimo = new BigDecimal(i);
                        fatorialresultado = fatorialresultado.multiply(ultimo);


                    }

                   q = fatorialresultado.toString().split("");

                    if (fatorialresultado.toString().length() > 9){

                        expressao.append(fatorialresultado.toString().substring(0,1) + ","
                                + fatorialresultado.toString().substring(1,7) + "E"
                                + (fatorialresultado.toString().length() - 1));

                    } else {

                        expressao.append(fatorialresultado.toString());

                    }




                } catch (Exception e) {}






                break;


        }


    }


}