package com.example.marketofsecondhandmaterials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SplashTwo extends Activity {

    TextView next, previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_two);


        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashTwo.this, SpashThree.class));
                Animatoo.animateSlideLeft(SplashTwo.this);
                finish();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashTwo.this, SplashOne.class));
                Animatoo.animateSlideRight(SplashTwo.this);
                finish();
            }
        });
    }
}
