package com.example.root.contactcollector;

import java.io.Serializable;

/**
 * Created by rjpp on 1/27/2018.
 */

public class Info implements Serializable{

    private String name = "";
    private String sufix = "";
    private String prefix = "";
    private String number = "";
    private String email = "";
    private String business = "" ;
    private String website = "";
    private String title = "";

    public Info(String name, String number, String email, String sufix, String prefix, String business, String website,
                String title){
        this.name = name;
        this.number = number;
        this.email = email;
        this.sufix = sufix;
        this.prefix = prefix;
        this.business = business;
        this.title = title;
        this.website = website;
    }

    public Info(){

    }

    public String getName(){
        return name;
    }

    public String getBusiness() {
        return business;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSufix() {
        return sufix;
    }

    public String getTitle() {
        return title;
    }

    public String getWebsite() {
        return website;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSufix(String sufix) {
        this.sufix = sufix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString(){
        return name;
    }
}
