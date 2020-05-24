package com.example.enterpriseaccountingsystem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DebtCalculation extends AppCompatActivity {

    String ProductAQty ="";
    String ProductBQty ="";
    String ProductCQty ="";
    ArrayList<String> data = new ArrayList<String>();
    ArrayList<String> data1 = new ArrayList<String>();
    ArrayList<String> data2 = new ArrayList<String>();
    ArrayList<String> data3 = new ArrayList<String>();

    EditText editText;
    CheckBox ProductA, ProductB, ProductC;
    Button  Add, Refresh;
    private DatabaseReference mDatabase;
    private TableLayout table;
    TextView debt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_debt_calculation);
        } catch (NullPointerException ignored) {

        }



        ProductA = findViewById(R.id.ProductA);
        ProductB = findViewById(R.id.ProductB);
        ProductC = findViewById(R.id.ProductC);
        editText = findViewById(R.id.editText);
        Add = findViewById(R.id.Add);
        Refresh = findViewById(R.id.Refresh);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        debt=findViewById(R.id.Debt);
        getDebt();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sales();

            }
        });
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = 0;
                for (int a = 0; a < data3.size(); a++) {
                    sum = sum + Integer.parseInt(data3.get(a).toString());
                }
                editText.setText(String.valueOf(sum));
                addToDatabase(Double.parseDouble(editText.getText().toString()));
            }
        });



    }

    public void Sales( ){
        if(ProductA.isChecked()){
            final TableRow row = new TableRow(this);
            final TextView t1 = new TextView(this);
            final TextView t2 = new TextView(this);
            final TextView t3 = new TextView(this);
            final TextView t4 = new TextView(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter the Qty");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String A = ProductA.getText().toString();
                    int Aprice = 20;
                    ProductAQty = input.getText().toString();
                    int totA = Aprice*Integer.parseInt(ProductAQty);
                    data.add(A);
                    data1.add(String.valueOf(Aprice));
                    data2.add(String.valueOf(ProductAQty));
                    data3.add(String.valueOf(totA));
                    TableLayout table = (TableLayout)findViewById(R.id.tableLayout2);

                    String totalA;
                    for(int i=0; i<data.size();i++){
                        String PnameA= data.get(i);
                        String prcA = data1.get(i);
                        String qtyyA = data2.get(i);
                        totalA = data3.get(i);

                        t1.setText(PnameA);
                        t2.setText(prcA);
                        t3.setText(qtyyA);
                        t4.setText(totalA);

                    }
                    row.addView(t1);
                    row.addView(t2);
                    row.addView(t3);
                    row.addView(t4);

                    table.addView(row);
                    removeQtyDatabase("Product #A",Double.parseDouble(ProductAQty));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        else if(ProductB.isChecked()){
            final TableRow row = new TableRow(this);
            final TextView t1 = new TextView(this);
            final TextView t2 = new TextView(this);
            final TextView t3 = new TextView(this);
            final TextView t4 = new TextView(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter the Qty");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String B = ProductB.getText().toString();
                    int Bprice = 30;
                    ProductBQty = input.getText().toString();
                    int totB = Bprice*Integer.parseInt(ProductBQty);
                    data.add(B);
                    data1.add(String.valueOf(Bprice));
                    data2.add(String.valueOf(ProductBQty));
                    data3.add(String.valueOf(totB));
                    TableLayout table = (TableLayout)findViewById(R.id.tableLayout2);

                    String totalB;
                    for(int i=0; i<data.size();i++){
                        String PnameB = data.get(i);
                        String prcB = data1.get(i);
                        String qtyyB = data2.get(i);
                        totalB = data3.get(i);

                        t1.setText(PnameB);
                        t2.setText(prcB);
                        t3.setText(qtyyB);
                        t4.setText(totalB);

                    }
                    row.addView(t1);
                    row.addView(t2);
                    row.addView(t3);
                    row.addView(t4);

                    table.addView(row);
                    removeQtyDatabase("Product #B",Double.parseDouble(ProductBQty));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        else if(ProductC.isChecked()){
            final TableRow row = new TableRow(this);
            final TextView t1 = new TextView(this);
            final TextView t2 = new TextView(this);
            final TextView t3 = new TextView(this);
            final TextView t4 = new TextView(this);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter the Qty");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String C = ProductC.getText().toString();
                    int Cprice = 40;
                    ProductCQty = input.getText().toString();
                    int totC = Cprice*Integer.parseInt(ProductCQty);
                    data.add(C);
                    data1.add(String.valueOf(Cprice));
                    data2.add(String.valueOf(ProductCQty));
                    data3.add(String.valueOf(totC));
                    TableLayout table = (TableLayout)findViewById(R.id.tableLayout2);

                    String totalC;
                    for(int i=0; i<data.size();i++){
                        String PnameC = data.get(i);
                        String prcC = data1.get(i);
                        String qtyyC = data2.get(i);
                        totalC = data3.get(i);

                        t1.setText(PnameC);
                        t2.setText(prcC);
                        t3.setText(qtyyC);
                        t4.setText(totalC);

                    }
                    row.addView(t1);
                    row.addView(t2);
                    row.addView(t3);
                    row.addView(t4);

                    table.addView(row);
                    removeQtyDatabase("Product #C",Double.parseDouble(ProductCQty));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
    public void addToDatabase(final double value){
        mDatabase.child("Users").child(Login.userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double t=Double.parseDouble(dataSnapshot.child("Debt :").getValue().toString());
                System.out.println(dataSnapshot.child("Debt :").getValue().toString());
                dataSnapshot.child("Debt :").getRef().setValue(t+value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void removeQtyDatabase(final String pdname,final double value){
        mDatabase.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot db:dataSnapshot.getChildren()){
                    System.out.println(db.child("Name :"));
                    if(pdname.equals(db.child("Name :").getValue())) {
                        double t=Double.parseDouble(db.child("Items :").getValue().toString());
                        db.child("Items :").getRef().setValue(t-value);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getDebt(){
        mDatabase.child("Users").child(Login.userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                debt.setText("Total Debt: "+dataSnapshot.child("Debt :").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}