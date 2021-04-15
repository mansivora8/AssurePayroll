package com.example.assurepayroll;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class display_payslips extends AppCompatActivity {
    private final String URL="http://192.168.29.195:80/SDP_Payroll/display_payslip.php"; //maitri's URL
    //private final String URL="http://192.168.43.231:80/SDP_Payroll/employee_list.php";
    static final String TAG = "Register";
    List<PayslipData> payslipDataList;
    RecyclerView recyclerView;
    DisplayPayslipAdapter displayPayslipAdapter;
  //  View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payslips);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.displayPayslip_list);
        payslipDataList=new ArrayList<>();

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                //Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                for(int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);


                        PayslipData payslipData=new PayslipData();
                        payslipData.setPid(jsonObject.getString("pid").toString());
                        payslipData.setPay_date(jsonObject.getString("pay_date").toString());
                        payslipData.setEmpId(jsonObject.getString("emp_id").toString());
                        payslipData.setSalaryPM(Integer.parseInt(jsonObject.getString("salaryPM")));
                        payslipData.setFull(Integer.parseInt(jsonObject.getString("full")));
                        payslipData.setHalf(Integer.parseInt(jsonObject.getString("half")));
                        payslipData.setSick(Integer.parseInt(jsonObject.getString("sick")));
                        payslipData.setDfull(Integer.parseInt(jsonObject.getString("dfull")));
                        payslipData.setDhalf(Integer.parseInt(jsonObject.getString("dhalf")));
                        payslipData.setDsick(Integer.parseInt(jsonObject.getString("dsick")));
                        payslipData.setTotalSalary(Integer.parseInt(jsonObject.getString("totalSalary")));
                        payslipData.setTotalDeduction(Integer.parseInt(jsonObject.getString("totalDeduction")));
                        payslipData.setName(jsonObject.getString("emp_name").toString());

                        payslipDataList.add(payslipData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
               // recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
                displayPayslipAdapter=new DisplayPayslipAdapter(getApplicationContext(),payslipDataList);
                recyclerView.setAdapter(displayPayslipAdapter);
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
                        //Intent i=new Intent(getApplicationContext(), payslip_details.class);
                        //startActivity(i);
                        Intent i=new Intent(getApplicationContext(),payslip_details.class);
                        i.putExtra("emp_name",payslipDataList.get(position).getName());
                        i.putExtra("emp_id",payslipDataList.get(position).getEmpId());
                        Log.d(TAG, payslipDataList.get(position).getEmpId());
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
        /*rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(this));
        String[] lang={"Admin","Admin"};
        rv.setAdapter(new DisplayPayslipAdapter(lang));*/
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}