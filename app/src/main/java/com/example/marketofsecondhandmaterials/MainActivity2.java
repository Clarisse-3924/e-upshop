package com.example.marketofsecondhandmaterials;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements ImageAdapter1.onItemclickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter1 mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private ValueEventListener mDRListener;
    private List<upload> muploads;
    DrawerLayout drawerLayout;
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        search=findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
        drawerLayout = findViewById(R.id.drawer_layout);
        mRecyclerView=findViewById(R.id.myrecycleview1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        muploads=new ArrayList<>();
        mAdapter = new ImageAdapter1(MainActivity2.this,muploads);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(MainActivity2.this);

        mStorage =FirebaseStorage.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Furniture");

        mDRListener= mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                muploads.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                    upload Upload=postSnapshot.getValue(upload.class);
                    Upload.setKey(postSnapshot.getKey());
                    muploads.add(Upload);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity2.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();


            }
        });
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        /*Clothes= findViewById(R.id.cloth);
        choe=findViewById(R.id.choe);
        fun=findViewById(R.id.fun);
        eclecto=findViewById(R.id.electro);


        Clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity2.this,selling2.class));

            }
        });

        choe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity2.this,Choesview1.class));

            }
        });
        fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity2.this,FurnitureView1.class));

            }
        });
        eclecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity2.this,Electronicview1.class));

            }
        });*/


    }
    private  void filter(String text){
        ArrayList<upload>filteredlist= new ArrayList<>();
        for(upload item: muploads){
            if(item.getPname().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(item);
            }
        }
        mAdapter.filterList(filteredlist);
    }
    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"normal click at position:" +position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this,"whatever click at position:" +position,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteClick(int position) {
        startActivity(new Intent(MainActivity2.this,ordering.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDRListener);
    }
    public void ClickMenu(View view){
        //open drawe
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        // open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawe
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void Clickcontact(View view){
        //redirect about us
        MainActivity2.redirectActivity(this,contact.class);
    }
    public void ClickSignUp(View view){
        //redirect about us
        MainActivity2.redirectActivity(this,AdminLogin.class);
    }
    public void ClickLogin(View view){
        //redirect about us
        MainActivity2.redirectActivity(this,login.class);
    }
    public void ClickSettings(View view){
        //redirect about us
        MainActivity2.redirectActivity(this,LanguageActivity.class);
    }

    public void ClickLogout(View view){
        //close app
        logout(this);
    }

    public static void logout(final Activity activity) {
        //initialize dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Lougout");
        //set meassage
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //exit app
                System.exit(0);
            }
        });
        //negative button
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aclass) {
        //Initialize intent
        Intent intent = new Intent(activity,aclass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }

}