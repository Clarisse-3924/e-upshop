package com.example.marketofsecondhandmaterials;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends Activity {

    LanguageManager languageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        languageManager = new LanguageManager(this);

        if (languageManager.hasLanguage()) {
            HashMap<String, String> setLang = languageManager.getAppLanguage();
            String lang = setLang.get(languageManager.LANG);

            setLocale(lang);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (languageManager.hasLanguage()) {
                    if (languageManager.authorizeSplash()) {
                        startActivity(new Intent(MainActivity.this, signup.class));
                        Animatoo.animateSlideLeft(MainActivity.this);
                        finish();
                    }

                    else {
                        startActivity(new Intent(MainActivity.this, SplashOne.class));
                        Animatoo.animateSlideLeft(MainActivity.this);
                        finish();
                    }
                }
                else {
                    startActivity(new Intent(MainActivity.this, LanguageActivity.class));
                    Animatoo.animateSlideLeft(MainActivity.this);
                    finish();
                }
            }
        }, 1500);
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
