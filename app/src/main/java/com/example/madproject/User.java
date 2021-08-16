package com.example.madproject;

public class User {

    public String name , phoneno , email , covid, volunteer;

    public User(){

    }

    public User(String name , String phoneno , String email)
    {
        this.name=name;
        this.email=email;
        this.phoneno=phoneno;
        covid="";
        volunteer="";
    }

}
