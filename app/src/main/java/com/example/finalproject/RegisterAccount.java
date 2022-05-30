package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterAccount extends AppCompatActivity {
    EditText userName, password, repeatPass;
    Button btn_register, btn_cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_account);

        userName = (EditText) findViewById(R.id.edt_register_user);
        password = (EditText) findViewById(R.id.edt_register_pass);
        repeatPass = (EditText) findViewById(R.id.edt_register_repeat);
        btn_register = (Button) findViewById(R.id.btn_create_account);
        btn_cancel = (Button) findViewById(R.id.btn_register_return);

        Bundle bundle = getIntent().getExtras();
        DBSimulator dbSimulator = (DBSimulator) bundle.get("customerList");
        List<Customer> list = dbSimulator.customerList;

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String repeat = repeatPass.getText().toString();

                if (user.equals("")) {
                    Toast.makeText(RegisterAccount.this, "Username required!", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals("")) {
                        Toast.makeText(RegisterAccount.this, "Password required!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!pass.equals(repeat)) {
                            Toast.makeText(RegisterAccount.this, "Password and repeated password must be the same!", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean availableAccount = true;
                            for (Customer c : list) {
                                if (c.getUserName().equalsIgnoreCase(user)) {
                                    availableAccount = false;
                                    break;
                                }
                            }
                            if (!availableAccount) {
                                Toast.makeText(RegisterAccount.this, "User Name exists!", Toast.LENGTH_SHORT).show();
                            } else {
                                Customer customer = new Customer(user, pass);
                                Toast.makeText(RegisterAccount.this, "Account registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterAccount.this, UserInformationComplete.class);
                                intent.putExtra("account", customer);
                                startActivityForResult(intent, 1);
                            }
                        }
                    }
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                Customer customer = (Customer) data.getExtras().get("customer");
                Intent returnIntent = new Intent();
                returnIntent.putExtra("customer", customer);
                setResult(RESULT_OK, returnIntent);
                finish();

            }

        }
    }
}
