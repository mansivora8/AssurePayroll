package com.example.assurepayroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class empAdapter extends RecyclerView.Adapter<empAdapter.empviewholder> {
 //   private String[] data;
   /* public empAdapter(String[] data){
        this.data=data;
    }*/

    List<EmployeeListData> employee_list;
    private Context context;
    LayoutInflater inflater;
    public empAdapter(Context context, List<EmployeeListData> employee_list) {
         this.inflater=LayoutInflater.from(context);
        this.context=context;
        this.employee_list=employee_list;
    }
    @NonNull
    @Override
    public empviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.emp_list_item_layout,parent,false);
        return new empviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull empviewholder holder, int position) {
        String name = employee_list.get(position).getName();
        String email = employee_list.get(position).getEmail_id();

        holder.name.setText(name);
        holder.email.setText(email);
    }

    @Override
    public int getItemCount() {
        return employee_list.size();
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
