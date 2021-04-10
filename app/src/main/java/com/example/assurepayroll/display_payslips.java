package com.example.assurepayroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class display_payslips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payslips);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView rv=(RecyclerView) findViewById(R.id.payslip_list);
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(getApplicationContext(), payslip_details.class);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Admin","Admin"};
        rv.setAdapter(new DisplayPayslipAdapter(lang));
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}