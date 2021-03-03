package com.example.assurepayroll;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_employee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_employee extends Fragment {
View view;
//fields
EditText etname,etemail,etcontact,etdob,etjoining,etacc;
TextView tvstatus;
Button btnRegister;

//url
//String URL="http://192.168.43.231:80/SDP_Payroll/register.php";
String URL="http://192.168.0.157:80/SDP_Payroll/register.php";

//strings
String name,email,dob,joiningDate,accNo,status,state,city,contact;


static final String TAG = "Register";

//spinner
Spinner sp_state,sp_city;
//arraylist state
ArrayList<String> arrayList_state;
//array adapter
ArrayAdapter<String> arrayAdapter_state;

//array list cities
ArrayList<String> arrayList_Andaman_And_Nicobar_Islands ; //0
ArrayList<String> arrayList_Andhra_Pradesh;//1
ArrayList<String> arrayList_Arunachal_Pradesh;//2
ArrayList<String> arrayList_Assam;//3
ArrayList<String> arrayList_Bihar;//4
ArrayList<String> arrayList_Chandigarh;//5
ArrayList<String> arrayList_Chhattisgarh;//6
ArrayList<String> arrayList_Dadra_And_Nagar_Haveli;//7
ArrayList<String> arrayList_Daman_And_Diu;//8
ArrayList<String> arrayList_Delhi;//9
ArrayList<String> arrayList_Goa;//10
ArrayList<String> arrayList_Gujarat;//11
ArrayList<String> arrayList_Haryana;//12
ArrayList<String> arrayList_Himachal_Pradesh;//13
ArrayList<String> arrayList_Jammu_And_Kashmir;//14
ArrayList<String> arrayList_Jharkhand;//15
ArrayList<String> arrayList_Karnataka;//16
ArrayList<String> arrayList_Kerala;//17
ArrayList<String> arrayList_Madhya_Pradesh;//18
ArrayList<String> arrayList_Maharashtra;//19
ArrayList<String> arrayList_Manipur;//20
ArrayList<String> arrayList_Meghalaya;//21
ArrayList<String> arrayList_Mizoram;//22
ArrayList<String> arrayList_Nagaland;//23
ArrayList<String> arrayList_Odisha;//24
ArrayList<String> arrayList_Puducherry;//25
ArrayList<String> arrayList_Punjab;//26
ArrayList<String> arrayList_Rajasthan;//27
ArrayList<String> arrayList_Sikkim;//28
ArrayList<String> arrayList_Tamil_Nadu;//29
ArrayList<String> arrayList_Telangana;//30
ArrayList<String> arrayList_Tripura;//31
ArrayList<String> arrayList_Uttar_Pradesh;//32
ArrayList<String> arrayList_Uttarakhand;//33
ArrayList<String> arrayList_West_Bengal;//34

    //aray adapter
ArrayAdapter<String> arrayAdapter_city;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_employee() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_employee.
     */
    // TODO: Rename and change types and number of parameters
    public static add_employee newInstance(String param1, String param2) {
        add_employee fragment = new add_employee();
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

        view= inflater.inflate(R.layout.fragment_add_employee, container, false);

        //spinner
        sp_state=(Spinner)view.findViewById(R.id.state);
        sp_city=(Spinner)view.findViewById(R.id.city);

        //array list
        arrayList_state=new ArrayList<>();
        arrayList_state.add("Andaman And Nicobar Islands");
        arrayList_state.add("Andhra Pradesh");
        arrayList_state.add("Arunachal Pradesh");
        arrayList_state.add("Assam");
        arrayList_state.add("Bihar");
        arrayList_state.add("Chandigarh");
        arrayList_state.add("Chhattisgarh");
        arrayList_state.add("Dadra And Nagar Haveli");
        arrayList_state.add("Daman And Diu ");
        arrayList_state.add("Delhi");
        arrayList_state.add("Goa");
        arrayList_state.add("Gujarat");
        arrayList_state.add("Haryana");
        arrayList_state.add("Himachal Pradesh");
        arrayList_state.add("Jammu And Kashmir");
        arrayList_state.add("Jharkhand");
        arrayList_state.add("Karnataka");
        arrayList_state.add("Kerala");
        arrayList_state.add("Madhya Pradesh");
        arrayList_state.add("Maharashtra");
        arrayList_state.add("Manipur");
        arrayList_state.add("Meghalaya");
        arrayList_state.add("Mizoram");
        arrayList_state.add("Nagaland");
        arrayList_state.add("Odisha");
        arrayList_state.add("Puducherry");
        arrayList_state.add("Punjab");
        arrayList_state.add("Rajasthan");
        arrayList_state.add("Sikkim");
        arrayList_state.add("Tamil Nadu");
        arrayList_state.add("Telangana");
        arrayList_state.add("Tripura");
        arrayList_state.add("Uttar Pradesh");
        arrayList_state.add("Uttarakhand");
        arrayList_state.add("West Bengal");





        //adapter
        arrayAdapter_state=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_state);
        sp_state.setAdapter(arrayAdapter_state);

        //city spinner process starts

        //Andaman And Nicobar Islands"
        arrayList_Andaman_And_Nicobar_Islands=new ArrayList<>();
        arrayList_Andaman_And_Nicobar_Islands.add("Port Blair");

        //Andhra Pradesh
        arrayList_Andhra_Pradesh=new ArrayList<>();
        arrayList_Andhra_Pradesh.add("Adoni");
        arrayList_Andhra_Pradesh.add("Amaravati");
        arrayList_Andhra_Pradesh.add("Anantapur");
        arrayList_Andhra_Pradesh.add("Chandragiri");
        arrayList_Andhra_Pradesh.add("Chittoor");
        arrayList_Andhra_Pradesh.add("Dowlaiswaram");
        arrayList_Andhra_Pradesh.add("Eluru");
        arrayList_Andhra_Pradesh.add("Guntur");
        arrayList_Andhra_Pradesh.add("Kadapa");
        arrayList_Andhra_Pradesh.add("Kakinada");
        arrayList_Andhra_Pradesh.add("Kurnool");
        arrayList_Andhra_Pradesh.add("Machilipatnam");
        arrayList_Andhra_Pradesh.add("Nagarjunakoṇḍa");
        arrayList_Andhra_Pradesh.add("Rajahmundry");
        arrayList_Andhra_Pradesh.add("Srikakulam");
        arrayList_Andhra_Pradesh.add("Tirupati");
        arrayList_Andhra_Pradesh.add("Vijayawada");
        arrayList_Andhra_Pradesh.add("Visakhapatnam");
        arrayList_Andhra_Pradesh.add("Vizianagaram");
        arrayList_Andhra_Pradesh.add("Yemmiganur");

        //Arunachal Pradesh
        arrayList_Arunachal_Pradesh=new ArrayList<>();
        arrayList_Arunachal_Pradesh.add("Itanagar");

        //Assam;
        arrayList_Assam=new ArrayList<>();
        arrayList_Assam.add("Dhuburi");
        arrayList_Assam.add("Dibrugarh");
        arrayList_Assam.add("Dispur");
        arrayList_Assam.add("Guwahati");
        arrayList_Assam.add("Jorhat");
        arrayList_Assam.add("Nagaon");
        arrayList_Assam.add("Sibsagar");
        arrayList_Assam.add("Silchar");
        arrayList_Assam.add("Tezpur");
        arrayList_Assam.add("Tinsukia");

        //Bihar
        arrayList_Bihar=new ArrayList<>();
        arrayList_Bihar.add("Ara");
        arrayList_Bihar.add("Baruni");
        arrayList_Bihar.add("Begusarai");
        arrayList_Bihar.add("Bettiah");
        arrayList_Bihar.add("Bhagalpur");
        arrayList_Bihar.add("Bihar Sharif");
        arrayList_Bihar.add("Bodh Gaya");
        arrayList_Bihar.add("Buxar");
        arrayList_Bihar.add("Chapra");
        arrayList_Bihar.add("Darbhanga");
        arrayList_Bihar.add("Dehri");
        arrayList_Bihar.add("Dinapur Nizamat");
        arrayList_Bihar.add("Gaya");
        arrayList_Bihar.add("Hajipur");
        arrayList_Bihar.add("Jamalpur");
        arrayList_Bihar.add("Katihar");
        arrayList_Bihar.add("Madhubani");
        arrayList_Bihar.add("Motihari");
        arrayList_Bihar.add("Munger");
        arrayList_Bihar.add("Muzaffarpur");
        arrayList_Bihar.add("Patna");
        arrayList_Bihar.add("Purnia");
        arrayList_Bihar.add("Saharsa");
        arrayList_Bihar.add("Samastipur");
        arrayList_Bihar.add("Sasaram");
        arrayList_Bihar.add("Sitamarhi");
        arrayList_Bihar.add("Siwan");

        //Chandigarh
        arrayList_Chandigarh=new ArrayList<>();
        arrayList_Chandigarh.add("Chandigarh");

        //Chhattisgarh
        arrayList_Chhattisgarh=new ArrayList<>();
        arrayList_Chhattisgarh.add("Ambikapur");
        arrayList_Chhattisgarh.add("Bhilai");
        arrayList_Chhattisgarh.add("Bilaspur");
        arrayList_Chhattisgarh.add("Dhamtari");
        arrayList_Chhattisgarh.add("Durg");
        arrayList_Chhattisgarh.add("Jagdalpur");
        arrayList_Chhattisgarh.add("Raipur");
        arrayList_Chhattisgarh.add("Rajnandgaon");

        //Dadra And Nagar Haveli
        arrayList_Dadra_And_Nagar_Haveli=new ArrayList<>();
        arrayList_Dadra_And_Nagar_Haveli.add("Silvassa");

        //Daman And Diu
        arrayList_Daman_And_Diu=new ArrayList<>();
        arrayList_Daman_And_Diu.add("Daman");
        arrayList_Daman_And_Diu.add("Diu");

        //Delhi
        arrayList_Delhi=new ArrayList<>();
        arrayList_Delhi.add("Delhi");
        arrayList_Delhi.add("New Delhi");

        //Goa
        arrayList_Goa=new ArrayList<>();
        arrayList_Goa.add("Madgaon");
        arrayList_Goa.add("Panaji");

        //Gujarat
        arrayList_Gujarat=new ArrayList<>();
        arrayList_Gujarat.add("Ahmadabad");
        arrayList_Gujarat.add("Amreli");
        arrayList_Gujarat.add("Bharuch");
        arrayList_Gujarat.add("Bhavnagar");
        arrayList_Gujarat.add("Bhuj");
        arrayList_Gujarat.add("Dwarka");
        arrayList_Gujarat.add("Gandhinagar");
        arrayList_Gujarat.add("Godhra");
        arrayList_Gujarat.add("Jamnagar");
        arrayList_Gujarat.add("Junagadh");
        arrayList_Gujarat.add("Kandla");
        arrayList_Gujarat.add("Khambhat");
        arrayList_Gujarat.add("Kheda");
        arrayList_Gujarat.add("Mahesana");
        arrayList_Gujarat.add("Morvi");
        arrayList_Gujarat.add("Nadiad");
        arrayList_Gujarat.add("Navsari");
        arrayList_Gujarat.add("Okha");
        arrayList_Gujarat.add("Palanpur");
        arrayList_Gujarat.add("Patan");
        arrayList_Gujarat.add("Porbandar");
        arrayList_Gujarat.add("Rajkot");
        arrayList_Gujarat.add("Surat");
        arrayList_Gujarat.add("Surendranagar");
        arrayList_Gujarat.add("Valsad");
        arrayList_Gujarat.add("Veraval");

        //Haryana
        arrayList_Haryana=new ArrayList<>();
        arrayList_Haryana.add("Ambala");
        arrayList_Haryana.add("Bhiwani");
        arrayList_Haryana.add("Chandigarh");
        arrayList_Haryana.add("Faridabad");
        arrayList_Haryana.add("Firozpur Jhirka");
        arrayList_Haryana.add("Gurgaon");
        arrayList_Haryana.add("Hansi");
        arrayList_Haryana.add("Hisar");
        arrayList_Haryana.add("Jind");
        arrayList_Haryana.add("Kaithal");
        arrayList_Haryana.add("Karnal");
        arrayList_Haryana.add("Kurukshetra");
        arrayList_Haryana.add("Panipat");
        arrayList_Haryana.add("Pehowa");
        arrayList_Haryana.add("Rewari");
        arrayList_Haryana.add("Rohtak");
        arrayList_Haryana.add("Sirsa");
        arrayList_Haryana.add("Sonipat");

        //Himachal Pradesh
        arrayList_Himachal_Pradesh=new ArrayList<>();
        arrayList_Himachal_Pradesh.add("Bilaspur");
        arrayList_Himachal_Pradesh.add("Chamba");
        arrayList_Himachal_Pradesh.add("Dalhousie");
        arrayList_Himachal_Pradesh.add("Dharmshala");
        arrayList_Himachal_Pradesh.add("Hamirpur");
        arrayList_Himachal_Pradesh.add("Kangra");
        arrayList_Himachal_Pradesh.add("Kullu");
        arrayList_Himachal_Pradesh.add("Mandi");
        arrayList_Himachal_Pradesh.add("Nahan");
        arrayList_Himachal_Pradesh.add("Shimla");
        arrayList_Himachal_Pradesh.add("Una");

        //Jammu And Kashmir
        arrayList_Jammu_And_Kashmir=new ArrayList<>();
        arrayList_Jammu_And_Kashmir.add("Anantnag");
        arrayList_Jammu_And_Kashmir.add("Baramula");
        arrayList_Jammu_And_Kashmir.add("Doda");
        arrayList_Jammu_And_Kashmir.add("Gulmarg");
        arrayList_Jammu_And_Kashmir.add("Jammu");
        arrayList_Jammu_And_Kashmir.add("Kathua");
        arrayList_Jammu_And_Kashmir.add("Leh");
        arrayList_Jammu_And_Kashmir.add("Punch");
        arrayList_Jammu_And_Kashmir.add("Rajauri");
        arrayList_Jammu_And_Kashmir.add("Srinagar");
        arrayList_Jammu_And_Kashmir.add("Udhampur");

        //Jharkhand
        arrayList_Jharkhand=new ArrayList<>();
        arrayList_Jharkhand.add("Bokaro");
        arrayList_Jharkhand.add("Chaibasa");
        arrayList_Jharkhand.add("Deoghar");
        arrayList_Jharkhand.add("Dhanbad");
        arrayList_Jharkhand.add("Dumka");
        arrayList_Jharkhand.add("Giridih");
        arrayList_Jharkhand.add("Hazaribag");
        arrayList_Jharkhand.add("Jamshedpur");
        arrayList_Jharkhand.add("Jharia");
        arrayList_Jharkhand.add("Rajmahal");
        arrayList_Jharkhand.add("Ranchi");
        arrayList_Jharkhand.add("Saraikela");

        //Karnataka
        arrayList_Karnataka=new ArrayList<>();
        arrayList_Karnataka.add("Badami");
        arrayList_Karnataka.add("Ballari");
        arrayList_Karnataka.add("Bangalore");
        arrayList_Karnataka.add("Belgavi");
        arrayList_Karnataka.add("Bhadravati");
        arrayList_Karnataka.add("Bidar");
        arrayList_Karnataka.add("Chikkamagaluru");
        arrayList_Karnataka.add("Chitradurga");
        arrayList_Karnataka.add("Davangere");
        arrayList_Karnataka.add("Halebid");
        arrayList_Karnataka.add("Hassan");
        arrayList_Karnataka.add("Hubballi-Dharwad");
        arrayList_Karnataka.add("Kalaburagi");
        arrayList_Karnataka.add("Kolar");
        arrayList_Karnataka.add("Madikeri");
        arrayList_Karnataka.add("Mandya");
        arrayList_Karnataka.add("Mangaluru");
        arrayList_Karnataka.add("Mysuru");
        arrayList_Karnataka.add("Raichur");
        arrayList_Karnataka.add("Shivamogga");
        arrayList_Karnataka.add("Shravanabelagola");
        arrayList_Karnataka.add("Shrirangapattana");
        arrayList_Karnataka.add("Tumkuru");

        //Kerala
        arrayList_Kerala=new ArrayList<>();
        arrayList_Kerala.add("Alappuzha");
        arrayList_Kerala.add("Badagara");
        arrayList_Kerala.add("Idukki");
        arrayList_Kerala.add("Kannur");
        arrayList_Kerala.add("Kochi");
        arrayList_Kerala.add("Kollam");
        arrayList_Kerala.add("Kottayam");
        arrayList_Kerala.add("Kozhikode");
        arrayList_Kerala.add("Mattancheri");
        arrayList_Kerala.add("Palakkad");
        arrayList_Kerala.add("Thalassery");
        arrayList_Kerala.add("Thiruvananthapuram");
        arrayList_Kerala.add("Thrissur");

        //Madhya Pradesh
        arrayList_Madhya_Pradesh=new ArrayList<>();
        arrayList_Madhya_Pradesh.add("Balaghat");
        arrayList_Madhya_Pradesh.add("Barwani");
        arrayList_Madhya_Pradesh.add("Betul");
        arrayList_Madhya_Pradesh.add("Bharhut");
        arrayList_Madhya_Pradesh.add("Bhind");
        arrayList_Madhya_Pradesh.add("Bhojpur");
        arrayList_Madhya_Pradesh.add("Bhopal");
        arrayList_Madhya_Pradesh.add("Burhanpur");
        arrayList_Madhya_Pradesh.add("Chhatarpur");
        arrayList_Madhya_Pradesh.add("Chhindwara");
        arrayList_Madhya_Pradesh.add("Damoh");
        arrayList_Madhya_Pradesh.add("Datia");
        arrayList_Madhya_Pradesh.add("Dewas");
        arrayList_Madhya_Pradesh.add("Dhar");
        arrayList_Madhya_Pradesh.add("Guna");
        arrayList_Madhya_Pradesh.add("Gwalior");
        arrayList_Madhya_Pradesh.add("Hoshangabad");
        arrayList_Madhya_Pradesh.add("Indore");
        arrayList_Madhya_Pradesh.add("Itarsi");
        arrayList_Madhya_Pradesh.add("Jabalpur");
        arrayList_Madhya_Pradesh.add("Jhabua");
        arrayList_Madhya_Pradesh.add("Khajuraho");
        arrayList_Madhya_Pradesh.add("Khandwa");
        arrayList_Madhya_Pradesh.add("Khargon");
        arrayList_Madhya_Pradesh.add("Maheshwar");
        arrayList_Madhya_Pradesh.add("Mandla");
        arrayList_Madhya_Pradesh.add("Mandsaur");
        arrayList_Madhya_Pradesh.add("Mhow");
        arrayList_Madhya_Pradesh.add("Morena");
        arrayList_Madhya_Pradesh.add("Murwara");
        arrayList_Madhya_Pradesh.add("Narsimhapur");
        arrayList_Madhya_Pradesh.add("Narsinghgarh");
        arrayList_Madhya_Pradesh.add("Narwar");
        arrayList_Madhya_Pradesh.add("Neemuch");
        arrayList_Madhya_Pradesh.add("Nowgong");
        arrayList_Madhya_Pradesh.add("Orchha");
        arrayList_Madhya_Pradesh.add("Panna");
        arrayList_Madhya_Pradesh.add("Raisen");
        arrayList_Madhya_Pradesh.add("Rajgarh");
        arrayList_Madhya_Pradesh.add("Ratlam");
        arrayList_Madhya_Pradesh.add("Rewa");
        arrayList_Madhya_Pradesh.add("Sagar");
        arrayList_Madhya_Pradesh.add("Sarangpur");
        arrayList_Madhya_Pradesh.add("Satna");
        arrayList_Madhya_Pradesh.add("Sehore");
        arrayList_Madhya_Pradesh.add("Seoni");
        arrayList_Madhya_Pradesh.add("Shahdol");
        arrayList_Madhya_Pradesh.add("Shajapur");
        arrayList_Madhya_Pradesh.add("Sheopur");
        arrayList_Madhya_Pradesh.add("Shivpuri");
        arrayList_Madhya_Pradesh.add("Ujjain");
        arrayList_Madhya_Pradesh.add("Vidisha");

        //Maharashtra
        arrayList_Maharashtra=new ArrayList<>();
        arrayList_Maharashtra.add("Ahmadnagar");
        arrayList_Maharashtra.add("Akola");
        arrayList_Maharashtra.add("Amravati");
        arrayList_Maharashtra.add("Aurangabad");
        arrayList_Maharashtra.add("Bhandara");
        arrayList_Maharashtra.add("Bid");
        arrayList_Maharashtra.add("Buldana");
        arrayList_Maharashtra.add("Chandrapur");
        arrayList_Maharashtra.add("Daulatabad");
        arrayList_Maharashtra.add("Dhule");
        arrayList_Maharashtra.add("Jalgaon");
        arrayList_Maharashtra.add("Kalyan");
        arrayList_Maharashtra.add("Karli");
        arrayList_Maharashtra.add("Kolhapur");
        arrayList_Maharashtra.add("Mahabaleshwar");
        arrayList_Maharashtra.add("Malegaon");
        arrayList_Maharashtra.add("Matheran");
        arrayList_Maharashtra.add("Mumbai");
        arrayList_Maharashtra.add("Nagpur");
        arrayList_Maharashtra.add("Nanded");
        arrayList_Maharashtra.add("Nashik");
        arrayList_Maharashtra.add("Osmanabad");
        arrayList_Maharashtra.add("Pandharpur");
        arrayList_Maharashtra.add("Parbhani");
        arrayList_Maharashtra.add("Pune");
        arrayList_Maharashtra.add("Ratnagiri");
        arrayList_Maharashtra.add("Sangli");
        arrayList_Maharashtra.add("Satara");
        arrayList_Maharashtra.add("Sevagram");
        arrayList_Maharashtra.add("Solapur");
        arrayList_Maharashtra.add("Thane");
        arrayList_Maharashtra.add("Ulhasnagar");
        arrayList_Maharashtra.add("Vasai-Virar");
        arrayList_Maharashtra.add("Wardha");
        arrayList_Maharashtra.add("Yavatmal");

        //Manipur
        arrayList_Manipur=new ArrayList<>();
        arrayList_Manipur.add("Imphal");

        //Meghalaya
        arrayList_Meghalaya=new ArrayList<>();
        arrayList_Meghalaya.add("Cherrapunji");
        arrayList_Meghalaya.add("Shillong");

        //Mizoram
        arrayList_Mizoram=new ArrayList<>();
        arrayList_Mizoram.add("Aizawl");
        arrayList_Mizoram.add("Lunglei");

        //Nagaland
        arrayList_Nagaland=new ArrayList<>();
        arrayList_Nagaland.add("Kohima");
        arrayList_Nagaland.add("Mon");
        arrayList_Nagaland.add("Phek");
        arrayList_Nagaland.add("Wokha");
        arrayList_Nagaland.add("Zunheboto");

        //Odisha
        arrayList_Odisha=new ArrayList<>();
        arrayList_Odisha.add("Balangir");
        arrayList_Odisha.add("Baleshwar");
        arrayList_Odisha.add("Baripada");
        arrayList_Odisha.add("Bhubaneshwar");
        arrayList_Odisha.add("Brahmapur");
        arrayList_Odisha.add("Cuttack");
        arrayList_Odisha.add("Dhenkanal");
        arrayList_Odisha.add("Keonjhar");
        arrayList_Odisha.add("Konark");
        arrayList_Odisha.add("Koraput");
        arrayList_Odisha.add("Paradip");
        arrayList_Odisha.add("Phulabani");
        arrayList_Odisha.add("Puri");
        arrayList_Odisha.add("Sambalpur");
        arrayList_Odisha.add("Udayagiri");

        //Puducherry
        arrayList_Puducherry=new ArrayList<>();
        arrayList_Puducherry.add("Karaikal");
        arrayList_Puducherry.add("Mahe");
        arrayList_Puducherry.add("Puducherry");
        arrayList_Puducherry.add("Yanam");

        //Punjab
        arrayList_Punjab=new ArrayList<>();
        arrayList_Punjab.add("Amritsar");
        arrayList_Punjab.add("Batala");
        arrayList_Punjab.add("Chandigarh");
        arrayList_Punjab.add("Faridkot\n");
        arrayList_Punjab.add("Firozpur");
        arrayList_Punjab.add("Gurdaspur");
        arrayList_Punjab.add("Hoshiarpur");
        arrayList_Punjab.add("Jalandhar");
        arrayList_Punjab.add("Kapurthala");
        arrayList_Punjab.add("Ludhiana");
        arrayList_Punjab.add("Nabha");
        arrayList_Punjab.add("Patiala");
        arrayList_Punjab.add("Rupnagar");
        arrayList_Punjab.add("Sangrur");

        //Rajasthan
        arrayList_Rajasthan=new ArrayList<>();
        arrayList_Rajasthan.add("Abu");
        arrayList_Rajasthan.add("Ajmer");
        arrayList_Rajasthan.add("Alwar");
        arrayList_Rajasthan.add("Amer");
        arrayList_Rajasthan.add("Barmer");
        arrayList_Rajasthan.add("Beawar");
        arrayList_Rajasthan.add("Bharatpur");
        arrayList_Rajasthan.add("Bhilwara");
        arrayList_Rajasthan.add("Bikaner");
        arrayList_Rajasthan.add("Bundi");
        arrayList_Rajasthan.add("Chittaurgarh");
        arrayList_Rajasthan.add("Churu");
        arrayList_Rajasthan.add("Dhaulpur");
        arrayList_Rajasthan.add("Dungarpur");
        arrayList_Rajasthan.add("Ganganagar");
        arrayList_Rajasthan.add("Hanumangarh");
        arrayList_Rajasthan.add("Jaipur");
        arrayList_Rajasthan.add("Jaisalmer");
        arrayList_Rajasthan.add("Jalor");
        arrayList_Rajasthan.add("Jhalawar");
        arrayList_Rajasthan.add("Jhunjhunu");
        arrayList_Rajasthan.add("Jodhpur");
        arrayList_Rajasthan.add("Kishangarh");
        arrayList_Rajasthan.add("Kota");
        arrayList_Rajasthan.add("Merta");
        arrayList_Rajasthan.add("Nagaur");
        arrayList_Rajasthan.add("Nathdwara");
        arrayList_Rajasthan.add("Pali");
        arrayList_Rajasthan.add("Phalodi");
        arrayList_Rajasthan.add("Pushkar");
        arrayList_Rajasthan.add("Sawai Madhopur");
        arrayList_Rajasthan.add("Shahpura");
        arrayList_Rajasthan.add("Sikar");
        arrayList_Rajasthan.add("Sirohi");
        arrayList_Rajasthan.add("Tonk");
        arrayList_Rajasthan.add("Udaipur");

        //Sikkim
        arrayList_Sikkim=new ArrayList<>();
        arrayList_Sikkim.add("Gangtok");
        arrayList_Sikkim.add("Gyalsing");
        arrayList_Sikkim.add("Lachung\n");
        arrayList_Sikkim.add("Mangan");

        //Tamil Nadu
        arrayList_Tamil_Nadu=new ArrayList<>();
        arrayList_Tamil_Nadu.add("Arcot");
        arrayList_Tamil_Nadu.add("Chengalpattu");
        arrayList_Tamil_Nadu.add("Chennai");
        arrayList_Tamil_Nadu.add("Chidambaram");
        arrayList_Tamil_Nadu.add("Coimbatore");
        arrayList_Tamil_Nadu.add("Cuddalore");
        arrayList_Tamil_Nadu.add("Dharmapuri");
        arrayList_Tamil_Nadu.add("Dindigul");
        arrayList_Tamil_Nadu.add("Erode");
        arrayList_Tamil_Nadu.add("Kanchipuram");
        arrayList_Tamil_Nadu.add("Kanniyakumari");
        arrayList_Tamil_Nadu.add("Kodaikanal");
        arrayList_Tamil_Nadu.add("Kumbakonam");
        arrayList_Tamil_Nadu.add("Madurai");
        arrayList_Tamil_Nadu.add("Mamallapuram");
        arrayList_Tamil_Nadu.add("Nagappattinam");
        arrayList_Tamil_Nadu.add("Nagercoil");
        arrayList_Tamil_Nadu.add("Palayankottai");
        arrayList_Tamil_Nadu.add("Pudukkottai");
        arrayList_Tamil_Nadu.add("Rajapalaiyam");
        arrayList_Tamil_Nadu.add("Ramanathapuram");
        arrayList_Tamil_Nadu.add("Salem");
        arrayList_Tamil_Nadu.add("Thanjavur");
        arrayList_Tamil_Nadu.add("Tiruchchirappalli");
        arrayList_Tamil_Nadu.add("Tirunelveli");
        arrayList_Tamil_Nadu.add("Tiruppur");
        arrayList_Tamil_Nadu.add("Tuticorin");
        arrayList_Tamil_Nadu.add("Udhagamandalam");
        arrayList_Tamil_Nadu.add("Vellore");

        //Telangana
        arrayList_Telangana=new ArrayList<>();
        arrayList_Telangana.add("Hyderabad");
        arrayList_Telangana.add("Karimnagar");
        arrayList_Telangana.add("Khammam");
        arrayList_Telangana.add("Mahbubnagar");
        arrayList_Telangana.add("Nizamabad");
        arrayList_Telangana.add("Sangareddi");
        arrayList_Telangana.add("Warangal");

        //Tripura
        arrayList_Tripura=new ArrayList<>();
        arrayList_Tripura.add("Agartala");

        //Uttar Pradesh
        arrayList_Uttar_Pradesh=new ArrayList<>();
        arrayList_Uttar_Pradesh.add("Agra");
        arrayList_Uttar_Pradesh.add("Aligarh");
        arrayList_Uttar_Pradesh.add("Amroha");
        arrayList_Uttar_Pradesh.add("Ayodhya");
        arrayList_Uttar_Pradesh.add("Azamgarh");
        arrayList_Uttar_Pradesh.add("Bahraich");
        arrayList_Uttar_Pradesh.add("Ballia");
        arrayList_Uttar_Pradesh.add("Banda");
        arrayList_Uttar_Pradesh.add("Bara Banki");
        arrayList_Uttar_Pradesh.add("Bareilly");
        arrayList_Uttar_Pradesh.add("Basti");
        arrayList_Uttar_Pradesh.add("Bijnor");
        arrayList_Uttar_Pradesh.add("Bithur");
        arrayList_Uttar_Pradesh.add("Budaun");
        arrayList_Uttar_Pradesh.add("Bulandshahr");
        arrayList_Uttar_Pradesh.add("Deoria");
        arrayList_Uttar_Pradesh.add("Etah");
        arrayList_Uttar_Pradesh.add("Etawah");
        arrayList_Uttar_Pradesh.add("Faizabad");
        arrayList_Uttar_Pradesh.add("Farrukhabad-cum-Fatehgarh");
        arrayList_Uttar_Pradesh.add("Fatehpur");
        arrayList_Uttar_Pradesh.add("Fatehpur Sikri");
        arrayList_Uttar_Pradesh.add("Ghaziabad");
        arrayList_Uttar_Pradesh.add("Ghazipur");
        arrayList_Uttar_Pradesh.add("Gonda");
        arrayList_Uttar_Pradesh.add("Gorakhpur");
        arrayList_Uttar_Pradesh.add("Hamirpur");
        arrayList_Uttar_Pradesh.add("Hardoi");
        arrayList_Uttar_Pradesh.add("Hathras");
        arrayList_Uttar_Pradesh.add("Jalaun");
        arrayList_Uttar_Pradesh.add("Jaunpur");
        arrayList_Uttar_Pradesh.add("Jhansi");
        arrayList_Uttar_Pradesh.add("Kannauj");
        arrayList_Uttar_Pradesh.add("Kanpur");
        arrayList_Uttar_Pradesh.add("Lakhimpur");
        arrayList_Uttar_Pradesh.add("Lalitpur");
        arrayList_Uttar_Pradesh.add("Lucknow");
        arrayList_Uttar_Pradesh.add("Mainpuri");
        arrayList_Uttar_Pradesh.add("Mathura");
        arrayList_Uttar_Pradesh.add("Meerut");
        arrayList_Uttar_Pradesh.add("Mirzapur-Vindhyachal");
        arrayList_Uttar_Pradesh.add("Moradabad");
        arrayList_Uttar_Pradesh.add("Muzaffarnagar");
        arrayList_Uttar_Pradesh.add("Partapgarh");
        arrayList_Uttar_Pradesh.add("Pilibhit");
        arrayList_Uttar_Pradesh.add("Prayagraj");
        arrayList_Uttar_Pradesh.add("Rae Bareli");
        arrayList_Uttar_Pradesh.add("Rampur");
        arrayList_Uttar_Pradesh.add("Saharanpur");
        arrayList_Uttar_Pradesh.add("Sambhal");
        arrayList_Uttar_Pradesh.add("Shahjahanpur");
        arrayList_Uttar_Pradesh.add("Sitapur");
        arrayList_Uttar_Pradesh.add("Sultanpur");
        arrayList_Uttar_Pradesh.add("Tehri");
        arrayList_Uttar_Pradesh.add("Varanasi");

        //Uttarakhand
        arrayList_Uttarakhand=new ArrayList<>();
        arrayList_Uttarakhand.add("Almora");
        arrayList_Uttarakhand.add("Dehra Dun");
        arrayList_Uttarakhand.add("Haridwar\n");
        arrayList_Uttarakhand.add("Mussoorie");
        arrayList_Uttarakhand.add("Nainital");
        arrayList_Uttarakhand.add("Pithoragarh");


        //West Bengal
        arrayList_West_Bengal=new ArrayList<>();
        arrayList_West_Bengal.add("Alipore");
        arrayList_West_Bengal.add("Alipur Duar");
        arrayList_West_Bengal.add("Asansol");
        arrayList_West_Bengal.add("Baharampur");
        arrayList_West_Bengal.add("Bally");
        arrayList_West_Bengal.add("Balurghat");
        arrayList_West_Bengal.add("Bankura");
        arrayList_West_Bengal.add("Baranagar");
        arrayList_West_Bengal.add("Barasat");
        arrayList_West_Bengal.add("Barrackpore");
        arrayList_West_Bengal.add("Basirhat");
        arrayList_West_Bengal.add("Bhatpara");
        arrayList_West_Bengal.add("Bishnupur");
        arrayList_West_Bengal.add("Budge Budge");
        arrayList_West_Bengal.add("Burdwan");
        arrayList_West_Bengal.add("Chandernagore");
        arrayList_West_Bengal.add("Darjiling");
        arrayList_West_Bengal.add("Diamond Harbour");
        arrayList_West_Bengal.add("Dum Dum");
        arrayList_West_Bengal.add("Durgapur");
        arrayList_West_Bengal.add("Halisahar");
        arrayList_West_Bengal.add("Haora");
        arrayList_West_Bengal.add("Hugli");
        arrayList_West_Bengal.add("Ingraj Bazar");
        arrayList_West_Bengal.add("Jalpaiguri");
        arrayList_West_Bengal.add("Kalimpong");
        arrayList_West_Bengal.add("Kamarhati");
        arrayList_West_Bengal.add("Kanchrapara");
        arrayList_West_Bengal.add("Kharagpur");
        arrayList_West_Bengal.add("Koch Bihar");
        arrayList_West_Bengal.add("Kolkata");
        arrayList_West_Bengal.add("Krishnanagar");
        arrayList_West_Bengal.add("Malda");
        arrayList_West_Bengal.add("Midnapore");
        arrayList_West_Bengal.add("Murshidabad");
        arrayList_West_Bengal.add("Navadwip");
        arrayList_West_Bengal.add("Palashi");
        arrayList_West_Bengal.add("Panihati\n");
        arrayList_West_Bengal.add("Purulia\n");
        arrayList_West_Bengal.add("Raiganj");
        arrayList_West_Bengal.add("Santipur");
        arrayList_West_Bengal.add("Shantiniketan");
        arrayList_West_Bengal.add("Shrirampur");
        arrayList_West_Bengal.add("Siliguri");
        arrayList_West_Bengal.add("Siuri");
        arrayList_West_Bengal.add("Tamluk");
        arrayList_West_Bengal.add("Titagarh");


        //listener
        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Andaman_And_Nicobar_Islands);
                }
                if(position==1)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Andhra_Pradesh);
                }
                if(position==2)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Arunachal_Pradesh);
                }
                if(position==3)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Assam);
                }
                if(position==4)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Bihar);
                }
                if(position==5)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Chandigarh);
                }
                if(position==6)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Chhattisgarh);
                }
                if(position==7)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Dadra_And_Nagar_Haveli);
                }
                if(position==8)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Daman_And_Diu);
                }
                if(position==9)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList_Delhi);
                }
                if(position==10)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Goa);
                }
                if(position==11)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList_Gujarat);
                }
                if(position==12)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Haryana);
                }if(position==13)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Himachal_Pradesh);
                }
                if(position==14)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Jammu_And_Kashmir);
                }
                if(position==15)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Jharkhand);
                }
                if(position==16)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Karnataka);
                }
                if(position==17)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Kerala);
                }
                if(position==18)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Madhya_Pradesh);
                }
                if(position==19)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Maharashtra);
                }
                if(position==20)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Manipur);
                }
                if(position==21)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Meghalaya);
                }
                if(position==22)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList_Mizoram);
                }
                if(position==23)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Nagaland);
                }
                if(position==24)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Odisha);
                }
                if(position==25)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Puducherry);
                }
                if(position==26)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Punjab);
                }
                if(position==27)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Rajasthan);
                }
                if(position==28)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Sikkim);
                }
                if(position==29)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Tamil_Nadu);
                }
                if(position==30)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Telangana);
                }
                if(position==31)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList_Tripura);
                }
                if(position==32)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Uttar_Pradesh);
                }
                if(position==33)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,arrayList_Uttarakhand);
                }
                if(position==34)
                {
                    arrayAdapter_city=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList_West_Bengal);
                }




                sp_city.setAdapter(arrayAdapter_city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //city spinner process ends

        //Generate random number
      /*  Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        eid="E-"+n;*/

        //find view by id
        btnRegister=view.findViewById(R.id.btnRegister);
        //eteid=view.findViewById(R.id.eid);
       // eteid.setText(String.valueOf(eid));
        etname=view.findViewById(R.id.ename);
        etemail=view.findViewById(R.id.etEmail);
        etcontact=view.findViewById(R.id.contact);
        etdob=view.findViewById(R.id.dob);
       // etjoining=view.findViewById(R.id.ename);
      //  etacc=view.findViewById(R.id.accno);
        //etcity=view.findViewById(R.id.city);
        //etstate=view.findViewById(R.id.state);

        tvstatus=view.findViewById(R.id.tvStatus);


        //strings
        name=email=dob=contact=joiningDate=accNo=status="";

        //calender
        Calendar calendar= Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        etdob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date =year + "/" + month + "/" + day;
                        etdob.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get values
               // eid = eteid.getText().toString().trim();
                name = etname.getText().toString().trim();
                email = etemail.getText().toString().trim();
              //  String temp;
                contact = etcontact.getText().toString().trim();
                //contact=Integer.parseInt(temp);
                dob = etdob.getText().toString().trim();
              //  joiningDate = etjoining.getText().toString().trim();
                //accNo = etacc.getText().toString().trim();
               // city = etcity.getText().toString().trim();
               // state = etstate.getText().toString().trim();
                state=sp_state.getSelectedItem().toString();
                city=sp_city.getSelectedItem().toString();
                status = tvstatus.getText().toString().trim();

                //validations
             /*   if (eid.isEmpty()) {
                    eteid.setError("Employee Id is required");
                    eteid.requestFocus();
                }*/
                if (name.isEmpty()) {
                    etname.setError("Name is required");
                    etname.requestFocus();
                }
                else if(!name.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$"))
                {
                    etname.setError("Name must contains only Characters");
                    etname.requestFocus();
                }
                else if (email.isEmpty()) {
                    etemail.setError("Email id is required");
                    etemail.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etemail.setError("Please enter Valid Email Id");
                    etemail.requestFocus();
                }
                else if (contact.isEmpty()) {
                    etcontact.setError("Contact No is required");
                    etcontact.requestFocus();
                }
                else if(!contact.matches("[0-9]{10}"))
                {
                    etcontact.setError("Please enter valid 10 Digit Mobile Number");
                    etcontact.requestFocus();
                }
                else if (dob.isEmpty()) {
                    etdob.setError("Date of Birth is required");
                    etdob.requestFocus();
                }
                /*if (joiningDate.isEmpty()) {
                    etjoining.setError("Please enter Full Name");
                    etjoining.requestFocus();
                }*/
               /* if (accNo.isEmpty()) {
                    etacc.setError("Please enter Full Name");
                    etacc.requestFocus();
                }*/
               /* else if (city.isEmpty()) {
                    etcity.setError("City is required");
                    etcity.requestFocus();
                }
               else if (state.isEmpty()) {
                    etstate.setError("State is required");
                    etstate.requestFocus();
                }*/
               else if ( !name.equals("") && !email.equals("") && !contact.equals("") && !dob.equals("") && !state.equals("") && !city.equals(""))
                {
                    Log.i(TAG, "All data are inserted");

                    StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
                    {
                       // stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                        @Override
                        public void onResponse(String response){
                            Log.d(TAG, response);
                            tvstatus.setText(response.toString());
                            Toast.makeText(getActivity(),"" +
                                    "" +
                                    "Employee added successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(),admin_dashboard.class);
                            startActivity(intent);
                            /*if (response.equals("success")) {
                               //Toast.makeText(getActivity(),"Email successfully sent to:"+email,Toast.LENGTH_LONG).show();
                               tvstatus.setText("Email successfully sent to:"+email);
                                btnRegister.setClickable(false);
                            } else if (response.equals("failure")) {
                               //Toast.makeText(getActivity(),"Email sending failed",Toast.LENGTH_LONG).show();
                                tvstatus.setText("Email sending failed!");
                            }
                            else if (response.equals("Error in insert")) {
                                //Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                                tvstatus.setText(response.toString());
                            }
                            else
                            {
                               // Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();
                                tvstatus.setText(response.toString());
                            }*/

                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> data=new HashMap<String, String>();
                          //  data.put("eid",eid);
                            data.put("name",name);
                            data.put("email",email);
                            data.put("contact",contact);
                            data.put("dob",dob);
                          //  data.put("joiningDate",joiningDate);
                          //  data.put("accNo",accNo);
                            data.put("city",city);
                            data.put("state",state);

                            Log.d(TAG, data.toString());

                            return  data;
                        }
                    };
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                    ));
                    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
                    requestQueue.add(stringRequest);
                }
               // Toast.makeText(getActivity(),"clicked on button register",Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}