package com.example.assurepayroll;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class employee_list extends AppCompatActivity {
    //private final String URL="http://192.168.29.195:80/SDP_Payroll/employee_list.php"; //maitri's URL
    private final String URL="http://192.168.43.231:80/SDP_Payroll/employee_list.php";
    static final String TAG = "Register";
    List<EmployeeListData> employeeListDataList;
    RecyclerView recyclerView;
    empAdapter empAdapter;
View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.emp_list_recycler_view);
        employeeListDataList=new ArrayList<>();




        //extract data

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);


                        EmployeeListData employeeListData=new EmployeeListData();
                        employeeListData.setEmp_id(jsonObject.getString("emp_id").toString());
                        employeeListData.setName(jsonObject.getString("name").toString());
                        employeeListData.setContact(jsonObject.getString("contact").toString());
                        employeeListData.setDob(jsonObject.getString("dob").toString());
                        employeeListData.setJoining_date(jsonObject.getString("joining_date").toString());
                        employeeListData.setAcc_no(jsonObject.getString("city").toString());
                        employeeListData.setCity(jsonObject.getString("city").toString());
                        employeeListData.setState(jsonObject.getString("state").toString());
                        employeeListData.setEmail_id(jsonObject.getString("email_id").toString());

                        employeeListDataList.add(employeeListData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                empAdapter=new empAdapter(getApplicationContext(),employeeListDataList);
                recyclerView.setAdapter(empAdapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });



        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(getApplicationContext(),employee_single.class);
                        i.putExtra("empId",employeeListDataList.get(position).getEmp_id());
                        Log.d(TAG, employeeListDataList.get(position).getEmp_id());
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


       /* rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Mansi Vora","Maitri Maniya"};
        rv.setAdapter(new empAdapter(lang));*/

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}