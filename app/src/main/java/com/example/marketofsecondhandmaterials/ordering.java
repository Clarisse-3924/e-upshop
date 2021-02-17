package com.example.marketofsecondhandmaterials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ordering extends AppCompatActivity {
        EditText Name1,Location,Code,Phone1;
        Button Submit;
        TextView textView6;
        DatabaseReference reff;
        Booking upload1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ordering);
            Name1 = (EditText)findViewById(R.id.Name1);
            Location = (EditText)findViewById(R.id.location);
            Phone1= (EditText) findViewById(R.id.phone1);

            reff = FirebaseDatabase.getInstance().getReference().child("orders");
            Submit = (Button) findViewById(R.id.button3);
            upload1 = new Booking();


            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upload1.setName(Name1.getText().toString().trim());
                    upload1.setPhone(Phone1.getText().toString().trim());
                    upload1.setLocation(Location.getText().toString().trim());

                    String name= Name1.getText().toString().trim();
                    String phone= Phone1.getText().toString().trim();
                    String location= Location.getText().toString().trim();


                    if(TextUtils.isEmpty(name)){
                        Name1.setError(" required");
                        return;
                    }
                    if(TextUtils.isEmpty(phone)){
                        Phone1.setError(" required");
                        return;
                    }
                    if(phone.length()!=10){
                        Phone1.setError("Phone number must be ten");
                        return;
                    }

                    if(TextUtils.isEmpty(location)){
                        Location.setError("required");
                        return;
                    }

                    String id = reff.push().getKey();
                    reff.child(id).setValue(upload1);
                    Toast.makeText(ordering.this, "Data saved", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ordering.this, "THANK YOU FOR Ordering ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ordering.this, "We will deliver it as soon as posible!! ", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

