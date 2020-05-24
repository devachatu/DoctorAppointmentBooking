package com.example.enterpriseaccountingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Options extends AppCompatActivity {
    public Button DebtCal,signout;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        DebtCal = (Button)findViewById(R.id.DebtCalculation);
        signout= (Button)findViewById(R.id.Signout);
        fAuth = FirebaseAuth.getInstance();


        DebtCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, DebtCalculation.class);
                startActivity(intent);
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent intent = new Intent(Options.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


}