package com.example.marketofsecondhandmaterials;

        import android.Manifest;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.net.Uri;
        import android.os.Bundle;

        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
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

public class Electronicview1 extends AppCompatActivity implements ImageAdapter1.onItemclickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter1 mAdapter;

    private DatabaseReference mDatabaseRef;
    private FirebaseStorage mStorage;
    private ValueEventListener mDRListener;
    private List<upload> muploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronicview1);
        mRecyclerView=findViewById(R.id.myrecycleview1);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        muploads=new ArrayList<>();
        mAdapter = new ImageAdapter1(Electronicview1.this,muploads);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(Electronicview1.this);

        mStorage =FirebaseStorage.getInstance();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("electronic");

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
                Toast.makeText(Electronicview1.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();


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
        startActivity(new Intent(Electronicview1.this,ordering.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDRListener);
    }
}

