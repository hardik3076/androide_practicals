package com.example.p22;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
EditText name,email,phone,password;
Button insert,view,update,delete;

DBHelper db;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    name=findViewById(R.id.etName);
    email=findViewById(R.id.etEmail);
    phone=findViewById(R.id.etPhone);
    password=findViewById(R.id.etPassword);

    insert=findViewById(R.id.btnInsert);
    view=findViewById(R.id.btnView);
    update=findViewById(R.id.btnUpdate);
    delete=findViewById(R.id.btnDelete);

    db=new DBHelper(this);

    insert.setOnClickListener(v -> {

        try{
            boolean result=db.insertData(
                    name.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString(),
                    password.getText().toString()
            );

            if(result)
                Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this,"Insert Failed",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(MainActivity.this,"Error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });

    view.setOnClickListener(v -> {

        Cursor res=db.getData();

        if(res.getCount()==0){
            showMessage("Error","No Data Found");
            return;
        }

        StringBuffer buffer=new StringBuffer();

        while(res.moveToNext()){

            buffer.append("ID:"+res.getString(0)+"\n");
            buffer.append("Name:"+res.getString(1)+"\n");
            buffer.append("Email:"+res.getString(2)+"\n");
            buffer.append("Phone:"+res.getString(3)+"\n\n");
        }

        showMessage("Data",buffer.toString());
    });
}

public void showMessage(String title,String message){

    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}
}

// app
//  └── java
//       └── com.example.p22
//             ├── MainActivity.java
//             └── DBHelper.java

//  └── res
//       └── layout
//             └── activity_main.xml
