package com.example.marketofsecondhandmaterials;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Admin extends AppCompatActivity {
    CardView furniture, order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        furniture= findViewById(R.id.furniture);
        order= findViewById(R.id.sold);


        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this,furnitureActivity.class));

            }
        });





        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Admin.this, orderingview.class));

            }
        });

    }
}