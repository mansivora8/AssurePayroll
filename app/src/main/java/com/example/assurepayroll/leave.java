package com.example.assurepayroll;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link leave#newInstance} factory method to
 * create an instance of this fragment.
 */
public class leave extends Fragment {

    private String URL="http://192.168.0.157:80/SDP_Payroll/leave_admin.php"; //maitri's URL
    static final String TAG = "Register";
    List<LeavesData> leavesDataList;
    RecyclerView recyclerView;
    LeaveAdapter leaveAdapter;
    View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public leave() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment leave.
     */
    // TODO: Rename and change types and number of parameters
    public static leave newInstance(String param1, String param2) {
        leave fragment = new leave();
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
        view= inflater.inflate(R.layout.fragment_leave, container, false);
        recyclerView=view.findViewById(R.id.leave_list);
        leavesDataList=new ArrayList<>();

        //extract data


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)  {
                //Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                for(int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);
                        LeavesData leavesData=new LeavesData();
                        leavesData.setId(jsonObject.getString("id").toString());
                        leavesData.setDate(jsonObject.getString("date").toString());
                        leavesData.setReson(jsonObject.getString("reason").toString());
                        leavesData.setLeave_type_id(jsonObject.getString("leave_type_id").toString());
                        leavesData.setEmp_id(jsonObject.getString("emp_id").toString());
                        leavesData.setStatus(jsonObject.getString("status").toString());

                        leavesDataList.add(leavesData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
              //  Toast.makeText(getActivity(), (CharSequence) leavesDataList,Toast.LENGTH_SHORT).show();

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                leaveAdapter=new LeaveAdapter(getActivity(),leavesDataList);
                recyclerView.setAdapter(leaveAdapter);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        /*StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                //Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });*/

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);



        /*RecyclerView rv=(RecyclerView) root.findViewById(R.id.leave_list);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] lang={"mar", "6"};
        rv.setAdapter(new LeaveAdapter(lang));*/
        return view;
    }
}