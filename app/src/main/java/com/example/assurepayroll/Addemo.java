package com.example.assurepayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Addemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public  void save(View view)
    {
        Toast.makeText(getApplicationContext(),"inside Register".trim(),Toast.LENGTH_SHORT).show();
    }
}