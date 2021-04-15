package com.example.assurepayroll;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.assurepayroll.LeaveAdapter.TAG;

public class PayslipAdapter extends RecyclerView.Adapter<PayslipAdapter.payslipviewholder> {
 /*   private String[] data;
    public PayslipAdapter(String[] data){
        this.data=data;
    }*/
 private final String URL="http://192.168.29.195:80/SDP_Payroll/generate_payslip.php"; //maitri's URL
    List<EmployeeListData> employee_list;
    private Context context;
    LayoutInflater inflater;

    public PayslipAdapter(Context context, List<EmployeeListData> employee_list) {
        this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.employee_list=employee_list;
    }
    @NonNull
    @Override
    public payslipviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    /*    LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.payslip_list_item_layout,parent,false);
        return new payslipviewholder(view);*/
        View view=inflater.inflate(R.layout.payslip_list_item_layout,parent,false);
        return new PayslipAdapter.payslipviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull payslipviewholder holder, int position) {
        /*String name=data[position];
        holder.name.setText(name);
        String email=data[position];
        holder.email.setText(email);*/
        String name = employee_list.get(position).getName();
        String email = employee_list.get(position).getEmail_id();

        holder.name.setText(name);
        holder.email.setText(email);

        //send mail when genetrate payslip button is clicked
        holder.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        if(response.equals("insert Success"))
                        {
                           // GeneratePayslip(position);
                            Toast.makeText(context, "Payslip Generated Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,display_payslips.class);
                            context.startActivity(intent);
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
                        data.put("empId",employee_list.get(position).getEmp_id());
                        data.put("email_id",employee_list.get(position).getEmail_id());
                        data.put("name",employee_list.get(position).getName());
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
        return employee_list.size();
    }

    public class payslipviewholder extends RecyclerView.ViewHolder{
        TextView name,email;
        Button btnGenerate;
        public payslipviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            email=(TextView) itemView.findViewById(R.id.mail);
            btnGenerate=itemView.findViewById(R.id.btnGenerate);
        }
    }
    public void GeneratePayslip(int item)
    {
        employee_list.remove(item);
        notifyItemRemoved(item);
    }

}
