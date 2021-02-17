package com.example.marketofsecondhandmaterials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SpashThree extends Activity {

    TextView next, previous;
    LanguageManager languageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_three);

        languageManager = new LanguageManager(this);

        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                languageManager.setSplash("splashUnAuthorized");

                startActivity(new Intent(SpashThree.this, signup.class));
                Animatoo.animateSlideLeft(SpashThree.this);
                finish();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SpashThree.this, SplashTwo.class));
                Animatoo.animateSlideRight(SpashThree.this);
                finish();
            }
        });
    }
}
