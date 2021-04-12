package com.example.assurepayroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PayslipAdapter extends RecyclerView.Adapter<PayslipAdapter.payslipviewholder> {
    private String[] data;
    public PayslipAdapter(String[] data){
        this.data=data;
    }
    @NonNull
    @Override
    public payslipviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.payslip_list_item_layout,parent,false);
        return new payslipviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull payslipviewholder holder, int position) {
        /*String name=data[position];
        holder.name.setText(name);
        String email=data[position];
        holder.email.setText(email);*/
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class payslipviewholder extends RecyclerView.ViewHolder{
        TextView name,email;
        public payslipviewholder(@NonNull View itemView) {
            super(itemView);
            /*name=(TextView) itemView.findViewById(R.id.name);
            email=(TextView) itemView.findViewById(R.id.mail);*/
        }
    }

}
