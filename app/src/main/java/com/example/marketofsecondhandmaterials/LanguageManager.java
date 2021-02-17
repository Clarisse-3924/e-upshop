package com.example.marketofsecondhandmaterials;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class LanguageManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String HAS_LANG = "HAS_LANG";
    public static final String LANG = "LANG";
    public static final  String SPLASH = "SPLASH";
    public static final String AUTHORIZE_SPLASH = "AUTHORIZE_SPLASH";

    public LanguageManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setDisplayLanguage(String language) {
        editor.putBoolean(HAS_LANG, true);
        editor.putString(LANG, language);
        editor.apply();
    }

    public boolean hasLanguage() {
        return sharedPreferences.getBoolean(HAS_LANG, false);
    }

    public HashMap<String, String> getAppLanguage() {
        HashMap<String, String> lang = new HashMap<>();
        lang.put(LANG, sharedPreferences.getString(LANG, null));

        return lang;
    }

    public void setSplash(String splashAuthorize) {
        editor.putBoolean(AUTHORIZE_SPLASH, true);
        editor.putString(SPLASH, splashAuthorize);
        editor.apply();
    }

    public boolean authorizeSplash() {
        return sharedPreferences.getBoolean(AUTHORIZE_SPLASH, false);
    }
}
