package com.library;

/**
 * Created by Neil on 5.2.18.
 */

public class Booknew {

    //Private Variable
    int _id1;
    String _isbn;
    String _bookname;
    String _author;
    String _aboutbook;



    //empty constructor
    public Booknew() {
    }

    //all parameter in Constructor
    public Booknew(int _id,  String _bookname, String _author,String  _isbn, String _aboutbook) {
        this._id1 = _id;
        this._isbn = _isbn;
        this._bookname = _bookname;
        this._author = _author;
        this._aboutbook = _aboutbook;
    }

    //three parameter Constructor
    public Booknew( String _bookname, String _author,String _isbn, String _aboutbook) {
        this._isbn = _isbn;
        this._bookname = _bookname;
        this._author = _author;
        this._aboutbook = _aboutbook;
    }


    //Getters for  all fields


    public int get_id1() {
        return _id1;
    }

    public String get_isbn() {
        return _isbn;
    }

    public String get_bookname() {
        return _bookname;
    }

    public String get_author() {
        return _author;
    }

    public String get_aboutbook() {
        return _aboutbook;
    }

    public void set_id1(int _id1) {
        this._id1 = _id1;
    }

    public void set_isbn(String _isbn) {
        this._isbn = _isbn;
    }

    public void set_bookname(String _bookname) {
        this._bookname = _bookname;
    }

    public void set_author(String _author) {
        this._author = _author;
    }

    public void set_aboutbook(String _aboutbook) {
        this._aboutbook = _aboutbook;
    }
}