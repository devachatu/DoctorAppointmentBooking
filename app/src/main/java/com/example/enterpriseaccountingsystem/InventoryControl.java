package com.example.enterpriseaccountingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InventoryControl extends AppCompatActivity {
    TextView Product[] = new TextView[3];
    TextView Items[] = new TextView[3];
    Button back;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_control);
        back=findViewById(R.id.back);
        Product[0] = findViewById(R.id.ProductA);
        Product[1] = findViewById(R.id.ProductB);
        Product[2] = findViewById(R.id.ProductC);
        Items[0] = findViewById(R.id.ItemsA);
        Items[1] = findViewById(R.id.ItemsB);
        Items[2] = findViewById(R.id.ItemsC);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Admin");
        retrieveData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admin.class));
            }
        });
    }


    public void retrieveData() {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    Product[i].setText(item.child("Name :").getValue().toString());
                    Items[i].setText(item.child("Items :").getValue().toString());
                    i=i+1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
