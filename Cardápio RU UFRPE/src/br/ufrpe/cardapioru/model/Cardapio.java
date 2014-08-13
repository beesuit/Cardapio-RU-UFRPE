
package br.ufrpe.cardapioru.model;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Cardapio {

    private static Cardapio sCardapio;
    private Context mAppContext;
    private JSONData mData;
    private ArrayList<String> mAlmocoNomes;
    private ArrayList<String> mJantarNomes;
    private ArrayList<Dia> mDias;

    public Cardapio(Context appContext) {
        mAppContext = appContext;
        mAlmocoNomes = new ArrayList<String>();
        mJantarNomes = new ArrayList<String>();
        mDias = new ArrayList<Dia>();
    }

    public static Cardapio get(Context appContext) {
        if (sCardapio == null) {
            sCardapio = new Cardapio(appContext.getApplicationContext());
        }
        return sCardapio;
    }

    public void writeToFile(String json) {
        SharedPreferences sharedPref = mAppContext.getSharedPreferences("appData",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("json", json);
        prefEditor.commit();
    }

    public String readFromFile() {
        String result;
        SharedPreferences sharedPref = mAppContext.getSharedPreferences("appData",
                Context.MODE_PRIVATE);
        result = sharedPref.getString("json", "Erro");
        return result;
    }

    public void loadFromJSON() {
        Gson gson = new Gson();
        if (!readFromFile().equals("Erro")) {
            mData = gson.fromJson(readFromFile(), JSONData.class);
            setUp();
        }
    }

    private void setUp() {
        mDias = new ArrayList<Dia>();

        mDias.add(new Dia(mData.almocoSegunda, mData.jantarSegunda));
        mDias.add(new Dia(mData.almocoTerca, mData.jantarTerca));
        mDias.add(new Dia(mData.almocoQuarta, mData.jantarQuarta));
        mDias.add(new Dia(mData.almocoQuinta, mData.jantarQuinta));
        mDias.add(new Dia(mData.almocoSexta, mData.jantarSexta));

        mAlmocoNomes = mData.almocoNomes;
        mJantarNomes = mData.jantarNomes;
    }

    public ArrayList<String> getAlmocoNomes() {
        return mAlmocoNomes;
    }

    public void setAlmocoNomes(ArrayList<String> almocoNomes) {
        mAlmocoNomes = almocoNomes;
    }

    public ArrayList<String> getJantarNomes() {
        return mJantarNomes;
    }

    public void setJantarNomes(ArrayList<String> jantarNomes) {
        mJantarNomes = jantarNomes;
    }
    
    public ArrayList<String> getAlmocoPratos(int pos){
        return mDias.get(pos).getAlmocoPratos();
    }
    
    public ArrayList<String> getJantarPratos(int pos){
        return mDias.get(pos).getJantarPratos();
    }
    
    public ArrayList<Dia> getDias() {
        return mDias;
    }

    public void setDias(ArrayList<Dia> dias) {
        mDias = dias;
    }

}
