package com.example.assurepayroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView rv=(RecyclerView) findViewById(R.id.attendance_list);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Mansi Vora","Maitri Maniya"};
        rv.setAdapter(new attendanceAdapter(lang));
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}