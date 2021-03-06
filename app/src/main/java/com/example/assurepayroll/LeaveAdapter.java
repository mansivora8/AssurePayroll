package com.example.assurepayroll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LeaveAdapter extends RecyclerView.Adapter<LeaveAdapter.leaveviewholder> {
   // LayoutInflater inflater;

    List<LeavesData> leavesData;

    public LeaveAdapter(Context context,List<LeavesData> leavesData) {
      //  this.inflater = LayoutInflater.from(context);
        // this.data=data;
        this.leavesData=leavesData;
    }
   // private String[] data;
    /*public LeaveAdapter(String[] data){
        this.data=data;
    }*/

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



        // String month=leavesData.get(position);
        holder.month.setText(month);
      //  String day=data[position];
        holder.day.setText(day);
       // String year=data[position];
        holder.year.setText(year);
      /*  String date=data[position];
       holder.date.setText(date);
        String date_data=data[position];
        holder.date_data.setText(date_data);*/
        //String type=data[position];
       // holder.type.setText(leavesData.get(position).getLeave_type_id());
      //  String type_data=data[position];
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
       // String reason=data[position];
       // holder.reason.setText(leavesData.get(position));
       // String reason_data=data[position];
        holder.reason_data.setText(leavesData.get(position).getReson());
    }

    @Override
    public int getItemCount() {
        //return data.length;
        return leavesData.size();
    }

    public class leaveviewholder extends RecyclerView.ViewHolder{
        TextView month,day,year,date,date_data,type,type_data,reason,reason_data;
        public leaveviewholder(@NonNull View itemView) {
            super(itemView);
            month=(TextView) itemView.findViewById(R.id.month);
            day=(TextView) itemView.findViewById(R.id.day);
            year=(TextView) itemView.findViewById(R.id.year);
            month=(TextView) itemView.findViewById(R.id.month);
       //     date=(TextView) itemView.findViewById(R.id.date);
           // date_data=(TextView) itemView.findViewById(R.id.date_data);
            type=(TextView) itemView.findViewById(R.id.type);
            type_data=(TextView) itemView.findViewById(R.id.type_data);
            reason=(TextView) itemView.findViewById(R.id.reason);
            reason_data=(TextView) itemView.findViewById(R.id.reason_data);
        }
    }
    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
