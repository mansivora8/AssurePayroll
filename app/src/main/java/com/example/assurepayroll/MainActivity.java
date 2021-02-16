package com.example.assurepayroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etEid,etPassword;
    private String eid,password;
   //private final String URL="http://192.168.43.231:80/SDP_Payroll/login.php"; // mansi
    private String URL="http://192.168.0.157:7071/SDP_Payroll/login.php"; //maitri's URL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        getSupportActionBar().hide();
        eid=password="";
        etEid=findViewById(R.id.etEid);
        etPassword=findViewById(R.id.etPassword);
    }
    public void login(View view)
    {
        eid=etEid.getText().toString().trim();
        password=etPassword.getText().toString().trim();
        if(eid.isEmpty())
        {
            etEid.setError("Please enter Employee Id");
            etEid.requestFocus();
        }
        else if(password.isEmpty())
        {
            etPassword.setError("Please enter Password");
            etPassword.requestFocus();
        }

        else if(!eid.equals("") && !password.equals(""))
        {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("admin")) {

                        Intent intent = new Intent(MainActivity.this, admin_dashboard.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Admin login successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if (response.equals("failure")) {
                        Toast.makeText(MainActivity.this, "Invalid Employee Id/Password", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent = new Intent(MainActivity.this, Success.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Employee login successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    /*else if (response.equals("employee")) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }*/

                   /* else
                    {
                        //employee login
                      //  Toast.makeText(MainActivity.this,response.toString(), Toast.LENGTH_SHORT).show();Intent intent = new Intent(MainActivity.this, Success.class);
                        //                        startActivity(intent);
                        //                        finish();

                    }*/
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.toString().trim(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> data=new HashMap<String,String>();
                    data.put("eid",eid);
                    data.put("password",password);
                    return data;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(this,"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
        }
    }

    public void visible(View view) {
        if(view.getId()==R.id.show_pass_btn){

            if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hide_password);

                //Show Password
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.show_password);

                //Hide Password
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
  /*  public void register(View view)
    {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
        finish();
    }*/
}
