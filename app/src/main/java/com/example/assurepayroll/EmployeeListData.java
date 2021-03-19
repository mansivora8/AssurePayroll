package com.example.assurepayroll;

public class EmployeeListData {

    private String emp_id;
    private String name;
    private String contact;
    private String dob;
    private String joining_date;
    private String acc_no;
    private String city;
    private String state;
    private String email_id;

    public EmployeeListData() {
    }

    public EmployeeListData(String emp_id, String name, String contact, String dob, String joining_date, String acc_no, String city, String state, String email_id) {
        this.emp_id = emp_id;
        this.name = name;
        this.contact = contact;
        this.dob = dob;
        this.joining_date = joining_date;
        this.acc_no = acc_no;
        this.city = city;
        this.state = state;
        this.email_id = email_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(String joining_date) {
        this.joining_date = joining_date;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
