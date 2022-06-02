package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CustomerPreviewEdit extends AppCompatActivity {
    ListView customerView;
    List<Customer> customerList;
    TextView title;
    CustomerAdapter adapter;
    Button btnDelete;
    DBSimulator dbSimulator;
    private final int edit_customer = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_all_room_view);

        title = (TextView) findViewById(R.id.txt_view_title);
        Bundle extras = getIntent().getExtras();
        dbSimulator = (DBSimulator) extras.get("package");
        customerList = dbSimulator.customerList;
        adapter = new CustomerAdapter(CustomerPreviewEdit.this, customerList);
        customerView = (ListView) findViewById(R.id.lv_all_room);
        customerView.setAdapter(adapter);
        btnDelete = (Button) findViewById(R.id.btn_all_delete);
        title.setText("ALL CUSTOMERS");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHandle();
            }
        });

        customerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CustomerPreviewEdit.this, AllRoomView.class);
                intent.putExtra("package", dbSimulator);
                startActivityForResult(intent, edit_customer);
            }
        });

        Button returnBtn = (Button) findViewById(R.id.btn_all_return);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("package", dbSimulator);
                setResult(RESULT_OK, intent);
                dbSimulator.setCustomerList(customerList);
                finish();
            }
        });
    }
    public void deleteHandle() {
        Toast.makeText(this, "You cannot delete customers from here!", Toast.LENGTH_SHORT).show();
        for (int i = customerView.getChildCount() - 1; i >=0; i--) {
            View v = customerView.getChildAt(i);
            CheckBox chk_delete = (CheckBox) v.findViewById(R.id.chk_customer_delete);
            if(chk_delete.isChecked()){
                chk_delete.setChecked(false);
//                customerList.remove(i);
//                adapter.notifyDataSetChanged();
            }
            chk_delete.setEnabled(false);
        }

    }

}
