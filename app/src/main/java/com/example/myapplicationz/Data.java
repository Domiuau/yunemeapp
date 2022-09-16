package com.example.myapplicationz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    static DateFormat formatardata = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    static Date data = new Date();

    public static String dataatual(){
        return formatardata.format(data);

    }

}
