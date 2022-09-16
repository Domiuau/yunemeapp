package model;

import android.graphics.drawable.Drawable;

public class Hist {

    private String ferramenta;
    private String dadosentrada;
    private String dadossaida;
    private String data;
    private boolean selecionado;
    private int icone;


    public String getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    public String getDadosentrada() {
        return dadosentrada;
    }

    public void setDadosentrada(String dadosentrada) {
        this.dadosentrada = dadosentrada;
    }

    public String getDadossaida() {
        return dadossaida;
    }

    public void setDadossaida(String dadossaida) {
        this.dadossaida = dadossaida;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    public static class histBuilder {

        private String ferramenta;
        private String dadosentrada;
        private String dadossaida;
        private String data;
        private boolean selecionado;
        private int icone;

        private histBuilder(){

        }

        public histBuilder setFerramenta(String ferramenta) {
            this.ferramenta = ferramenta;
            return this;
        }

        public histBuilder setDadosentrada(String dadosentrada) {
            this.dadosentrada = dadosentrada;
            return this;
        }

        public histBuilder setDadossaida(String dadossaida) {
            this.dadossaida = dadossaida;
            return this;
        }

        public histBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public histBuilder setSelecionado(boolean selecionado) {
            this.selecionado = selecionado;
            return this;
        }

        public histBuilder setIcone(int icone) {
            this.icone = icone;
            return this;
        }

        public static histBuilder builder(){
            return new histBuilder();
        }

        public Hist build() {
            Hist hist = new Hist();
            hist.ferramenta = ferramenta;
            hist.dadosentrada = dadosentrada;
            hist.dadossaida = dadossaida;
            hist.data = data;
            hist.selecionado = selecionado;
            hist.icone = icone;
            return hist;
        }

    }

}
