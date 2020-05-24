package com.example.enterpriseaccountingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Admin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText mEntAmt;
    Button subt,clear,ic;
    Spinner choiceUser;
    TextView DispName;
    TextView DispDebt;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private DatabaseReference mDatabase;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mEntAmt = findViewById(R.id.EnterAmt);
        subt = findViewById(R.id.Subtract);
        fAuth = FirebaseAuth.getInstance();
        clear = findViewById(R.id.Clear);
        choiceUser=findViewById(R.id.spinner);
        ic=findViewById(R.id.InvenCont);
        choiceUser.setOnItemSelectedListener(this);
        DispName=findViewById(R.id.DispName);
        DispDebt=findViewById(R.id.DispDebt);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        spinnerDataList= new ArrayList<>();
        adapter=new ArrayAdapter<String>(Admin.this,
                android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        choiceUser.setAdapter(adapter);
        retrieveDataForSpinner();

        subt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(mEntAmt.getText().toString())) subtactivity(Double.parseDouble(mEntAmt.getText().toString()));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtactivity(0);
            }
        });
        ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InventoryControl.class));
            }
        });
   }
   public void subtactivity(final double value){
       mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot db:dataSnapshot.getChildren()){
                   if(DispName.getText().toString().equals(db.child("Email :").getValue().toString())) {
                       double t=Double.parseDouble(db.child("Debt :").getValue().toString());
                       if(value==0)db.child("Debt :").getRef().setValue(0);
                       else db.child("Debt :").getRef().setValue(t-value);
                   }
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

   }
   public void retrieveDataForSpinner(){
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    spinnerDataList.add(item.child("Name :").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        final String item = parent.getItemAtPosition(position).toString();
        listener= mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot db:dataSnapshot.getChildren()){
                    if(item.equals(db.child("Name :").getValue().toString())) {
                        DispName.setText(db.child("Email :").getValue().toString());
                        DispDebt.setText(db.child("Debt :").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

   public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
