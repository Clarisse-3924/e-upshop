package com.example.marketofsecondhandmaterials;

/*import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class orderingview extends AppCompatActivity {
    EditText Name1,Code,Location,phone1;
    TextView textView5;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    Booking upload1;
    String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderingview);
        Name1 = (EditText)findViewById(R.id.Name1);
        Code = (EditText)findViewById(R.id.code);
     Location= (EditText)findViewById(R.id.location);
        phone1= (EditText) findViewById(R.id.phone1);
        upload1=new Booking();

        recyclerView = findViewById(R.id.myrecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference().child("orders");

    }

}*/

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class orderingview extends AppCompatActivity implements ImageAdapter2.onItemclickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter2 mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private ValueEventListener mDRListener;
    private List<Booking> muploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderingview);
        mRecyclerView=findViewById(R.id.myrecycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        muploads=new ArrayList<>();
        mAdapter = new ImageAdapter2(orderingview.this,muploads);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(orderingview.this);

        mStorage =FirebaseStorage.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("orders");

        mDRListener= mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                muploads.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {
                    Booking Upload=postSnapshot.getValue(Booking.class);
                    Booking.setKey(postSnapshot.getKey());
                    muploads.add(Upload);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(orderingview.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();


            }
        });


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
        Booking selectedItem = muploads.get(position);
        final String selectedKey = selectedItem.getKey();
        mDatabaseRef.child(selectedKey).removeValue();
        Toast.makeText(orderingview.this,"Item deleted",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDRListener);
    }
}