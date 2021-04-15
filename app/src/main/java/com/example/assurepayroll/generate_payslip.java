package com.example.assurepayroll;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link generate_payslip#newInstance} factory method to
 * create an instance of this fragment.
 */

public class generate_payslip extends Fragment {
    private final String URL="http://192.168.29.195:80/SDP_Payroll/employee_list.php";//maitri
    static final String TAG = "Register";
    List<EmployeeListData> employeeListDataList;
    RecyclerView recyclerView;
    PayslipAdapter payslipAdapter;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public generate_payslip() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment generate_payslip.
     */
    // TODO: Rename and change types and number of parameters
    public static generate_payslip newInstance(String param1, String param2) {
        generate_payslip fragment = new generate_payslip();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* View root= inflater.from(getParentFragment().getContext()).inflate(R.layout.fragment_generate_payslip, container, false);
        RecyclerView rv=(RecyclerView) root.findViewById(R.id.payslip_list);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] lang={"Admin","Employee"};
        rv.setAdapter(new PayslipAdapter(lang));
        return root;*/
        view= inflater.from(getParentFragment().getContext()).inflate(R.layout.fragment_generate_payslip, container, false);
        recyclerView= view.findViewById(R.id.payslip_list);
        employeeListDataList=new ArrayList<>();

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
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                payslipAdapter=new PayslipAdapter(getContext(),employeeListDataList);
                recyclerView.setAdapter(payslipAdapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });



        /*recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent i=new Intent(getContext(),employee_single.class);
                        i.putExtra("empId",employeeListDataList.get(position).getEmp_id());
                        Log.d(TAG, employeeListDataList.get(position).getEmp_id());
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    return view;
    }
}