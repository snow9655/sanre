package com.hodanet.sanre.business.model;

public class TemperatureModel {

    public String ltemp;
    public String ntemp;
    public String htemp;
    public long   time;

    public String getLtemp() {
        return ltemp;
    }

    public void setLtemp(String ltemp) {
        this.ltemp = ltemp;
    }

    public String getNtemp() {
        return ntemp;
    }

    public void setNtemp(String ntemp) {
        this.ntemp = ntemp;
    }

    public String getHtemp() {
        return htemp;
    }

    public void setHtemp(String htemp) {
        this.htemp = htemp;
    }

    
    public long getTime() {
        return time;
    }

    
    public void setTime(long time) {
        this.time = time;
    }
    
}
