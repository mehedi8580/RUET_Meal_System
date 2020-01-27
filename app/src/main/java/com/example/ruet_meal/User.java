package com.example.ruet_meal;

public class User {

    public  String name,phone,email,department,roll,hall;

    public  User(String name,String phone,String email,String department,String roll,String hall)
    {

      this.name=name;
      this.phone=phone;
      this.email=email;
      this.department=department;
      this.roll=roll;
      this.hall=hall;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }
}
