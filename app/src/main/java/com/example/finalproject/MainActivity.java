package com.example.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login, register;
    DBSimulator dbSimulator;
    private static final int code = 0, customer_login = 2;
    private static final int register_code = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbSimulator = new DBSimulator();
        userName = (EditText) findViewById(R.id.edt_user_name);
        password = (EditText) findViewById(R.id.edt_password);
        login = (Button) findViewById(R.id.btn_login);
        register = (Button) findViewById(R.id.btn_signup);

        dbSimulator.updateBooking();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill in login information", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isAdmin = checkAdmin();
                    if (isAdmin) {
                        Intent adminMain = new Intent(MainActivity.this, AdminActivity.class);
                        adminMain.putExtra("package", dbSimulator);
                        startActivityForResult(adminMain, code);
                    } else {
                        int index = checkAccount();
                        if(index > -1){
                            Intent userIntent = new Intent(MainActivity.this, Homepage.class);
                            userIntent.putExtra("package", dbSimulator);
                            userIntent.putExtra("customer", dbSimulator.customerList.get(index));
                            startActivityForResult(userIntent, customer_login);
                        }else
                            Toast.makeText(MainActivity.this, "Wrong User name or Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterAccount.class);
                registerIntent.putExtra("customerList", dbSimulator);
                startActivityForResult(registerIntent, register_code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                dbSimulator = (DBSimulator) data.getExtras().get("package");

            }

        }
        if(requestCode == register_code){
            if(resultCode == RESULT_OK) {
                Customer customer = (Customer) data.getExtras().get("customer");
                dbSimulator.customerList.add(customer);
                System.out.println(dbSimulator.customerList.get(0).getName());
            }
        }
        if(requestCode == customer_login){
            if(resultCode == RESULT_OK){
                dbSimulator = (DBSimulator) data.getExtras().get("package");
            }
        }

        dbSimulator.updateBooking();
    }

    int checkAccount(){
        String temp = this.password.getText().toString();
        for(int i = 0; i < dbSimulator.customerList.size(); i++){
            Customer c = dbSimulator.customerList.get(i);
            String userInput = c.getUserName();
            String passInput = c.getPassword();
            if(this.userName.getText().toString().equalsIgnoreCase(userInput)
                    && temp.equals(passInput)){
                return i;
            }
        }
        return -1;
    }

    boolean checkAdmin() {
        String temp = this.password.getText().toString();
        if (this.userName.getText().toString().equalsIgnoreCase("admin")
                && this.password.getText().toString().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_out, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_shutdown) {
            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("Alert");
            ad.setMessage("Are you sure want to quit?");
            ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            ad.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}
