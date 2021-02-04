package com.example.assurepayroll;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Addemp extends Fragment {
   // private EditText eteid,etname,etcontact,etdob,etJoiningdate,etaccno,etcity,etstate,etemailId;
  //  private EditText eid,ename,contact,dob,joiningdate,accno,city,state,emailId;
  //  private Button btnRegister;
    private AddempViewModel mViewModel;

   // private String URL="http://192.168.0.157:7071/SDP_Payroll/register.php"; // maitri
  //  private String eid,ename,contact,dob,joiningdate,accno,city,state,emailId;
  //  private static final String TAG = "Register";
    public static Addemp newInstance() {
        return new Addemp();
    }
  //  DatePickerDialog.OnDateSetListener setListener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.addemp_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // setContentView(R.layout.register);
       // eteid=findViewById(R.id.eid);

        mViewModel = new ViewModelProvider(this).get(AddempViewModel.class);
        // TODO: Use the ViewModel
    }

}