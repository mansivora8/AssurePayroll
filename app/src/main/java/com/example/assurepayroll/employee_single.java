package com.example.assurepayroll;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class employee_single extends AppCompatActivity {
    String URL="http://192.168.29.195:80/SDP_Payroll/employee_single.php"; //maitri
    //String URL="http://192.168.43.231:80/SDP_Payroll/employee_single.php";
    String empId;
    TextView tname,temail,tcontact,tjoining,tdob,tacc,tcity,tState;
    //String name,email,contact,joiningDate,dob,accNo,status,state,city;
    View view;
    static final String TAG = "Register";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_single);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        empId=getIntent().getStringExtra("empId");
        tname=findViewById(R.id.name);
        temail=findViewById(R.id.email);
        tcontact=findViewById(R.id.contact);
        tjoining=findViewById(R.id.joining_date);
        tdob=findViewById(R.id.dob);
        //tacc=view.findViewById(R.id.ename);
        tcity=findViewById(R.id.city);
        tState=findViewById(R.id.state);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONArray j = new JSONArray(response);
                    for(int i=0;i<j.length();i++)
                    {
                        try {

                            JSONObject jsonObject=j.getJSONObject(i);
                            Log.d(TAG, jsonObject.toString());
                        //Log.d(TAG,jsonObject.getString("name").toString());
                            tname.setText(jsonObject.getString("name").toString());
                            temail.setText(jsonObject.getString("email_id").toString());
                            tcontact.setText(jsonObject.getString("contact").toString());
                            tjoining.setText(jsonObject.getString("joining_date").toString());
                            tdob.setText(jsonObject.getString("dob").toString());
                            tcity.setText(jsonObject.getString("city").toString());
                            tState.setText(jsonObject.getString("state").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
               /* Log.d(TAG, response);
                if(response.equals("Grant Success"))
                {
                    Log.d(TAG,response.toString());
                  //  Grant(position);
                    Toast.makeText(getApplicationContext(), "Leave Request Granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }*/
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<String, String>();
                data.put("empId",empId);
                return data;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void generatepayslip(View view) {
        Intent i=new Intent(this, payslip_details.class);
        startActivity(i);
    }
}
