package com.example.assurepayroll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.assurepayroll.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.attendance);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.attendance:
                        return true;

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeFragment.class ) );
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.payslip:
                        startActivity(new Intent(getApplicationContext(),payslip.class ) );
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}