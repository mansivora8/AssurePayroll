package com.example.assurepayroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class employee_list extends AppCompatActivity {
    RecyclerView recyclerView;
    empAdapter empAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView rv=(RecyclerView) findViewById(R.id.emp_list_recycler_view);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Mansi Vora","Maitri Maniya"};
        rv.setAdapter(new empAdapter(lang));
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}