package com.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Neil on 06-11-2015.
 */
public class DBHandlerAuthor extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "author";

    // Contacts table name
    private static final String TABLE_AUTHOR_DETAIL = "authorDetails";

    // Contacts Table Columns names
    private static final String KEY_ID2 = "id2";
    private static final String KEY_AGE = "age";
    private static final String KEY_AUTHORNAME = "authorname";
    private static final String KEY_DOB = "dob";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ABOUTAUTHOR = "aboutauthor";

    public DBHandlerAuthor(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_AUTHOR_DETAIL_TABLE = "CREATE TABLE " + TABLE_AUTHOR_DETAIL + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY,"
                + KEY_AUTHORNAME + " TEXT,"
                + KEY_AGE + " TEXT,"
                + KEY_GENDER + " TEXT,"
                + KEY_DOB + " TEXT,"
                + KEY_ABOUTAUTHOR + " TEXT " + ")";                ;

        db.execSQL(CREATE_AUTHOR_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR_DETAIL);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Student Information
    void saveNewAuthor(Authornew newAuth) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_AGE, newAuth.get_age());
        values.put(KEY_AUTHORNAME, newAuth.get_authorname());
        values.put(KEY_DOB, newAuth.get_dob());
        values.put(KEY_GENDER, newAuth.get_gender());
        values.put(KEY_ABOUTAUTHOR, newAuth.get_aboutauthor());



        // Inserting Row
        db.insert(TABLE_AUTHOR_DETAIL, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Authors
    public List<Authornew> authorList(String filter) {
        String query;

        if(filter.equals("")){
            query="SELECT  * FROM " + TABLE_AUTHOR_DETAIL;
        }else {
            query="SELECT  * FROM " + TABLE_AUTHOR_DETAIL + " ORDER BY " + filter;
        }

        List<Authornew> authorlinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Authornew athr;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                athr= new Authornew();

                athr.set_id(Integer.parseInt(cursor.getString(0)));
                athr.set_age(cursor.getString(2));
                athr.set_authorname(cursor.getString(1));
                athr.set_dob(cursor.getString(4));
                athr.set_gender(cursor.getString(3));
                athr.set_aboutauthor(cursor.getString(5));

                // Adding contact to list
                authorlinkedList.add(athr);

            } while (cursor.moveToNext());
        }

        // return contact list
        return authorlinkedList;
    }

    public Authornew getAuthor(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT  * FROM " + TABLE_AUTHOR_DETAIL + " WHERE id=" + id;
        Cursor cursor = db.rawQuery(query, null);
        Authornew recievedauthor = new Authornew();
        if (cursor.getCount()>0) {
            cursor.moveToFirst();

            recievedauthor.set_age(cursor.getString(2));
            recievedauthor.set_authorname(cursor.getString(1));
            recievedauthor.set_dob(cursor.getString(4));
            recievedauthor.set_gender(cursor.getString(3));
            recievedauthor.set_aboutauthor(cursor.getString(5));
        }
        return  recievedauthor;
    }

    public String[] authornameList(Context c) {

            String query="SELECT  * FROM " + TABLE_AUTHOR_DETAIL;

        DBHandlerAuthor athr =new DBHandlerAuthor(c);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Authornew> author =  athr.authorList("");
        String[] authornameList=new String[author.size()];
        int i=0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                authornameList[i] = cursor.getString(1);
                i++;
            } while (i<author.size()&&cursor.moveToNext());
        }
        return authornameList;
    }
}