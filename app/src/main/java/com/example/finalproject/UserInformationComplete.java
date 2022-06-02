package com.example.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserInformationComplete extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
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

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fullName.getText().toString();
                String phoneNum = phone.getText().toString();
                String mail = email.getText().toString();
                String tempDate = birthdate.getText().toString();
                Date birth;

                try {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                    birth = format.parse(tempDate);
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
                } catch (Exception e) {
                    Toast.makeText(UserInformationComplete.this, "Wrong date format input!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        birthdate.setText(dateFormat.format(calendar.getTime()));
    }

}
