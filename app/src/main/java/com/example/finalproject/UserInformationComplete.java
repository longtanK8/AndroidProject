package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInformationComplete extends AppCompatActivity {
    EditText fullName, phone, email, birthdate;
    Button confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account_information);

        fullName = (EditText) findViewById(R.id.edt_infor_fullname);
        phone = (EditText) findViewById(R.id.edt_infor_phone);
        email = (EditText) findViewById(R.id.edt_infor_email);
        birthdate = (EditText) findViewById(R.id.edt_infor_birthdate);
        confirm = (Button) findViewById(R.id.btn_infor_confirm);

        Bundle bundle = getIntent().getExtras();
        Customer customer = (Customer) bundle.get("account");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fullName.getText().toString();
                String phoneNum = phone.getText().toString();
                String mail = email.getText().toString();
                String tempDate = birthdate.getText().toString();
                Date birth = new Date();

                try {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    birth = format.parse(tempDate);
                } catch (Exception e) {
                    Toast.makeText(UserInformationComplete.this, "Wrong date format input!", Toast.LENGTH_SHORT).show();
                }

                if (fname.equals("") || phoneNum.equals("") || mail.equals("") || tempDate.equals("")) {
                    Toast.makeText(UserInformationComplete.this, "Please fill in all information!", Toast.LENGTH_SHORT).show();
                } else {
                    customer.setId("cus" + phoneNum + tempDate.split("/")[0]);
                    customer.setBirthDate(birth);
                    customer.setEmail(mail);
                    customer.setName(fname);
                    customer.setPhoneNum(phoneNum);
                    Toast.makeText(UserInformationComplete.this, "Information filled in successfully!", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("customer", customer);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }

            }
        });

    }
}
