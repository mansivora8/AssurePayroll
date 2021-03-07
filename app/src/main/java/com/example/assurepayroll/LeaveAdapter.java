package com.example.assurepayroll;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.leaveviewholder> {

    private final String Grant_URL="http://192.168.43.231:80/SDP_Payroll/grant_leave.php";
    private final String Deny_URL="http://192.168.43.231:80/SDP_Payroll/deny_leave.php";
    //private final String Grant_URL="http://192.168.0.157:80/SDP_Payroll/grant_leave.php"; //maitri's URL
    //private final String Deny_URL="http://192.168.0.157:80/SDP_Payroll/deny_leave.php"; //maitri's URL
    static final String TAG = "Register";
    List<LeavesData> leavesData;
    private Context context;
    public LeaveAdapter(Context context,List<LeavesData> leavesData) {
this.context=context;
        this.leavesData=leavesData;
    }


    @NonNull
    @Override
    public leaveviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return new leaveviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull leaveviewholder holder, int position) {

        //extract date
       String date = leavesData.get(position).getDate();
        String month="";
        String day="";
        String year="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            month = checkDigit(cal.get(Calendar.MONTH)+1);
            day = checkDigit(cal.get(Calendar.DATE));
            year = checkDigit(cal.get(Calendar.YEAR));

        } catch (Exception e) {
            e.printStackTrace();
        }




        holder.month.setText(month);

        holder.day.setText(day);

        holder.year.setText(year);

        String leave_type=leavesData.get(position).getLeave_type_id();
        String leave_name="";
        switch (leave_type) {
            case "1":
                leave_name = "Full";
                break;
            case "2":
                leave_name = "Half";
                break;
            case "3":
                leave_name = "Sick";
                break;
        }
        holder.type_data.setText(leave_name);
        holder.reason_data.setText(leavesData.get(position).getReson());

        //button grant
        holder.btnGrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest=new StringRequest(Request.Method.POST, Grant_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        if(response.equals("Grant Success"))
                        {
                            Grant(position);
                            Toast.makeText(context, "Leave Request Granted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data=new HashMap<String, String>();
                        data.put("id",leavesData.get(position).getId());
                        return data;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));
                RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());
                requestQueue.add(stringRequest);
            }
        });

        holder.btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest=new StringRequest(Request.Method.POST, Deny_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        if(response.equals("Deny Success"))
                        {
                            Grant(position);
                            Toast.makeText(context, "Leave Request Denied", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data=new HashMap<String, String>();
                        data.put("id",leavesData.get(position).getId());
                        return data;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                ));
                RequestQueue requestQueue= Volley.newRequestQueue(v.getContext());
                requestQueue.add(stringRequest);
            }
        });


    }

    @Override
    public int getItemCount() {
        //return data.length;
        return leavesData.size();
    }

    public static class leaveviewholder extends RecyclerView.ViewHolder{
        TextView month,day,year,type,type_data,reason,reason_data;
        Button btnGrant,btnDeny;
        public leaveviewholder(@NonNull View itemView) {
            super(itemView);
            month=(TextView) itemView.findViewById(R.id.month);
            day=(TextView) itemView.findViewById(R.id.day);
            year=(TextView) itemView.findViewById(R.id.year);
            month=(TextView) itemView.findViewById(R.id.month);
            type=(TextView) itemView.findViewById(R.id.type);
            type_data=(TextView) itemView.findViewById(R.id.type_data);
            reason=(TextView) itemView.findViewById(R.id.reason);
            reason_data=(TextView) itemView.findViewById(R.id.reason_data);
            btnGrant=itemView.findViewById(R.id.grant);
            btnDeny=itemView.findViewById(R.id.deny);
        }
    }
    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public void Grant(int item)
    {
        leavesData.remove(item);
        notifyItemRemoved(item);
    }
    public void Deny(int item)
    {
        leavesData.remove(item);
        notifyItemRemoved(item);
    }
}
