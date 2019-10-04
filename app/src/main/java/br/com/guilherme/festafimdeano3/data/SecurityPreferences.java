package br.com.guilherme.festafimdeano3.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    //Construtor para inicializar o SharedPreferences
    public SecurityPreferences(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("FestaFimAno", context.MODE_PRIVATE);
    }

    //Salva dados no SharedPreferences
    public void storeString(String chave, String valor) {
        this.mSharedPreferences.edit().putString(chave, valor).apply();
    }

    //Recupera dados no SharedPreferences
    public String getStoreString(String chave) {
        return this.mSharedPreferences.getString(chave, "");
    }

}
