
package br.ufrpe.cardapioru.model;

import java.util.ArrayList;

public class Dia {

    private ArrayList<String> mAlmocoPratos;
    private ArrayList<String> mJantarPratos;

    public Dia(ArrayList<String> almocoPratos, ArrayList<String> jantarPratos) {
        mAlmocoPratos = almocoPratos;
        mJantarPratos = jantarPratos;
    }

    public ArrayList<String> getAlmocoPratos() {
        return mAlmocoPratos;
    }

    public void setAlmocoPratos(ArrayList<String> almocoPratos) {
        mAlmocoPratos = almocoPratos;
    }

    public ArrayList<String> getJantarPratos() {
        return mJantarPratos;
    }

    public void setJantarPratos(ArrayList<String> jantarPratos) {
        mJantarPratos = jantarPratos;
    }

}
