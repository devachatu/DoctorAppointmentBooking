package com.example.enterpriseaccountingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ALogin extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_login);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        mLoginBtn = findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                try{
                    validate(email);
                }catch(Exception m){System.out.println("Email: "+m);
                    Toast toast=Toast.makeText(getApplicationContext(),"Email: "+m,Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();  }

                try{
                    validate(password);
                }catch(Exception m){System.out.println("Password: "+m);
                    Toast toast=Toast.makeText(getApplicationContext(),"Password: "+m,Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show(); }


                progressBar.setVisibility(View.VISIBLE);
            if(email.equals("admin")&&password.equals("admin"))startActivity(new Intent(getApplicationContext(),Admin.class));
            else progressBar.setVisibility(View.GONE);
            }
                });
    }
    static void validate(String text) throws InvalidInputException {
        if (TextUtils.isEmpty(text))
            throw new InvalidInputException("Text is required");
    }
}
class InvalidInputException extends Exception{
    InvalidInputException(String s){
        super(s);
    }
}

