package com.library;

/**
 * Created by Neil on 5.2.18.
 */

public class Authornew {

    //Private Variable
    int _id2;
    String _age;
    String _authorname;
    String _dob;
    String _gender;
    String _aboutauthor;



    //empty constructor
    public Authornew() {
    }

    //all parameter in Constructor
    public Authornew(int _id,  String _authorname, String _age, String _gender,String _dob, String _aboutauthor) {
        this._id2 = _id;
        this._age = _age;
        this._authorname = _authorname;
        this._dob = _dob;
        this._gender = _gender;
        this._aboutauthor = _aboutauthor;
    }

    //three parameter Constructor
    public Authornew( String _authorname,String _age,  String _gender,String _dob, String _aboutauthor) {
        this._age = _age;
        this._authorname = _authorname;
        this._dob = _dob;
        this._gender = _gender;
        this._aboutauthor = _aboutauthor;
    }


    //Getters for  all fields


    public int get_id() {
        return _id2;
    }

    public String get_age() {
        return _age;
    }

    public String get_authorname() {
        return _authorname;
    }

    public String get_dob() {
        return _dob;
    }

    public String get_gender() {
        return _gender;
    }

    public String get_aboutauthor() {
        return _aboutauthor;
    }

    //Setters for all fields
    public void set_id(int _id) {
        this._id2 = _id;
    }

    public void set_age(String _age) {
        this._age = _age;
    }

    public void set_authorname(String _authorname) {
        this._authorname = _authorname;
    }

    public void set_dob(String _dob) {
        this._dob = _dob;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public void set_aboutauthor(String _aboutauthor) {
        this._aboutauthor = _aboutauthor;
    }
}