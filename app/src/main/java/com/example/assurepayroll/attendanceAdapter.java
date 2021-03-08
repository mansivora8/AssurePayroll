package com.example.assurepayroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class attendanceAdapter extends RecyclerView.Adapter<attendanceAdapter.empviewholder> {
    private String[] data;
    public attendanceAdapter(String[] data){
        this.data=data;
    }
    @NonNull
    @Override
    public empviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.attendance_list_item_layout,parent,false);
        return new empviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull empviewholder holder, int position) {
        String name=data[position];
        holder.name.setText(name);
        String email=data[position];
        holder.email.setText(email);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class empviewholder extends RecyclerView.ViewHolder{
        TextView name,email;
        public empviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            email=(TextView) itemView.findViewById(R.id.mail);
        }
    }

}
