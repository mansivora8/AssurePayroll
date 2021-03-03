package com.example.assurepayroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.leaveviewholder> {
    private String[] data;
    public LeaveAdapter(String[] data){
        this.data=data;
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
        String month=data[position];
        holder.month.setText(month);
        String day=data[position];
        holder.day.setText(day);
        String year=data[position];
        holder.year.setText(year);
        String date=data[position];
        holder.date.setText(date);
        String date_data=data[position];
        holder.date_data.setText(date_data);
        String type=data[position];
        holder.type.setText(type);
        String type_data=data[position];
        holder.type_data.setText(type_data);
        String reason=data[position];
        holder.reason.setText(reason);
        String reason_data=data[position];
        holder.reason_data.setText(reason_data);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class leaveviewholder extends RecyclerView.ViewHolder{
        TextView month,day,year,date,date_data,type,type_data,reason,reason_data;
        public leaveviewholder(@NonNull View itemView) {
            super(itemView);
            month=(TextView) itemView.findViewById(R.id.month);
            day=(TextView) itemView.findViewById(R.id.day);
            year=(TextView) itemView.findViewById(R.id.year);
            month=(TextView) itemView.findViewById(R.id.month);
            date=(TextView) itemView.findViewById(R.id.date);
            date_data=(TextView) itemView.findViewById(R.id.date_data);
            type=(TextView) itemView.findViewById(R.id.type);
            type_data=(TextView) itemView.findViewById(R.id.type_data);
            reason=(TextView) itemView.findViewById(R.id.reason);
            reason_data=(TextView) itemView.findViewById(R.id.reason_data);
        }
    }

}
