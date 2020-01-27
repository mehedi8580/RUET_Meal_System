package com.example.ruet_meal;

public class tokenStore {

   public String roll,dinner,launch,hall;

   public tokenStore(){


   }


    public tokenStore(String roll, String dinner, String launch, String hall) {
        this.roll = roll;
        this.dinner = dinner;
        this.launch = launch;
        this.hall= hall;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }


}
