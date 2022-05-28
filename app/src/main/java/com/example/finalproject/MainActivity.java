package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login, register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.edt_user_name);
        password = (EditText) findViewById(R.id.edt_password);
        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userName.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please fill in login information", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isAdmin = checkAdmin();
                    if(isAdmin){
                        Intent adminMain = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(adminMain);
                    }else{
                        Toast.makeText(MainActivity.this, password.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    boolean checkAdmin(){
        String temp = this.password.getText().toString();
        if(this.userName.getText().toString().equalsIgnoreCase("admin")
                && this.password.getText().toString().equals("admin")){
            return true;
        }else{
            return false;
        }
    }
}
