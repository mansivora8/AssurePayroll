package com.example.assurepayroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  DisplayPayslipAdapter extends RecyclerView.Adapter<DisplayPayslipAdapter.payslipviewholder> {
   /* private String[] data;
    public DisplayPayslipAdapter(String[] data){
        this.data=data;
    }*/
   List<PayslipData> payslipDataList;
    private Context context;
    LayoutInflater inflater;

    public DisplayPayslipAdapter(Context context, List<PayslipData> payslipDataList) {
        this.inflater=LayoutInflater.from(context);
       // this.context=context;
        this.payslipDataList=payslipDataList;
    }

    @NonNull
    @Override
    public payslipviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.display_payslip_list_item_layout,parent,false);
        return new payslipviewholder(view);*/
        View view=inflater.inflate(R.layout.display_payslip_list_item_layout,parent,false);
        return new payslipviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull payslipviewholder holder, int position) {
        /*String name=data[position];
        holder.name.setText(name);
        String date=data[position];
        holder.date.setText(date);
        String amount=data[position];
        holder.amount.setText(amount);*/
        String name = payslipDataList.get(position).getName();
        String date = payslipDataList.get(position).getPay_date();
        Integer salary = payslipDataList.get(position).getTotalSalary();

        holder.name.setText(name);
        holder.date.setText(date);
        holder.salary.setText(salary.toString());

    }

    @Override
    public int getItemCount() {
        return payslipDataList.size();
    }

    public class payslipviewholder extends RecyclerView.ViewHolder{
        TextView name,date,salary;
        public payslipviewholder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            date= itemView.findViewById(R.id.date);
            salary= itemView.findViewById(R.id.salary);
        }
    }

}
