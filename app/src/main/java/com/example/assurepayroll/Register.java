package com.example.assurepayroll;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText etEmail,etPassword,etReenterPassword,etName,etPhone,etAddress,etDate;

    private TextView tvStatus;
    private Button btnRegister;
   // private String URL="http://192.168.43.231:80/SDP_Payroll/register.php";//mansi's URL
    private String URL="http://192.168.0.157:7071/SDP_Payroll/register.php"; //maitri's URL
    private String email,password,reenterPassword,name,phone,dob,address;
    private static final String TAG = "Register";

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etDate=findViewById(R.id.etDate);
        etAddress=findViewById(R.id.etAddress);
      etEmail=findViewById(R.id.etEmail);
      etPassword=findViewById(R.id.etPassword);
      etReenterPassword=findViewById(R.id.etReenterPassword);
      tvStatus=findViewById(R.id.tvStatus);
      btnRegister=findViewById(R.id.btnRegister);
      email=password=reenterPassword=name=phone=address=dob="";

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    public  void save(View view)
    {
        name=etName.getText().toString().trim();
        phone=etPhone.getText().toString().trim();
        address=etAddress.getText().toString().trim();
        dob=etDate.getText().toString().trim();
        email=etEmail.getText().toString().trim();
        password=etPassword.getText().toString().trim();
        reenterPassword=etReenterPassword.getText().toString().trim();
     /*   if(!password.equals(reenterPassword)){
            Toast.makeText(this,"Password Mismatch",Toast.LENGTH_SHORT).show();

        }*/
        if(name.isEmpty())
        {
            etName.setError("Please enter Full Name");
            etName.requestFocus();
        }
        else if(!name.matches("[a-z,A-Z]*"))
        {
            etName.setError("Name must contains only Characters");
            etName.requestFocus();
        }
        else if(phone.isEmpty())
        {
            etPhone.setError("Please enter Mobile No.");
            etPhone.requestFocus();
        }
        else if(!phone.matches("[0-9]{10}"))
        {
            etPhone.setError("Please enter valid 10 Digit Mobile Number");
            etPhone.requestFocus();
        }
        else if(address.isEmpty())
        {
            etAddress.setError("Please enter Address");
            etAddress.requestFocus();
        }
        else if(dob.isEmpty())
        {
            etDate.setError("Please enter Date of Birth");
            etDate.requestFocus();
        }

        else if(email.isEmpty())
        {
            etEmail.setError("Please enter Email Id");
            etEmail.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etEmail.setError("Please enter Valid Email Id");
            etEmail.requestFocus();
        }

        else if(password.isEmpty())
        {
            etPassword.setError("Please enter Password");
            etPassword.requestFocus();
        }
        else if(password.length()<10)
        {
            etPassword.setError("Password must be of atleast 10 Characters");
            etPassword.requestFocus();
        }
        else if(reenterPassword.isEmpty())
        {
            etReenterPassword.setError("Please Reenter Password");
            etReenterPassword.requestFocus();
        }
        else if(!password.equals(reenterPassword))
        {


            etReenterPassword.setError("Password does not match");

            etReenterPassword.requestFocus();
        }
        /*else if(reenterPassword.length()<10)
        {
            etPassword.setError("Password must be of atleast 10 Characters");
            etPassword.requestFocus();
        }*/
        else  if(!address.equals("") && !dob.equals("") && !name.equals("") && !phone.equals("") && !email.equals("") && !password.equals("")){
            Log.i(TAG, "Password in not null");
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, response);
                    //tvStatus.setText(response.toString());
                    if (response.equals("success")) {
                       tvStatus.setText("Successfully registered.");
                       btnRegister.setClickable(false);
                    } else if (response.equals("failure")) {
                        tvStatus.setText("Something went wrong!");
                    }
                    else
                    {
                        tvStatus.setText(response.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> data=new HashMap<String, String>();
                    data.put("name",name);
                    data.put("phone",phone);
                    data.put("address",address);
                    data.put("dob",dob);
                    data.put("email",email);
                    data.put("password",password);
                    Log.d(TAG, data.toString());
                    return data;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    public void login(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
