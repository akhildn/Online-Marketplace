package com.iupui.marketplace.model.beans;

import java.io.Serializable;

/**
 * Created by anaya on 4/2/2017.
 */
public class Address implements Serializable {


    private String streetAddress;
    private String city;
    private String state;
    private String zip;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString(){
        return streetAddress+", "+city+", "+state+" - "+zip;
    }

}
