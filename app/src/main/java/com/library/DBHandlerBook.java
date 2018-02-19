package com.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Neil on 06-11-2015.
 */
public class DBHandlerBook extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "book";

    // Contacts table name
    private static final String TABLE_BOOK_DETAIL = "bookDetails";

    // Contacts Table Columns names
    private static final String KEY_ID1 = "id1";
    private static final String KEY_BOOKNAME = "bookname";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_ISBN = "isbn";
    private static final String KEY_ABOUTBOOK = "aboutbook";

    public DBHandlerBook(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BOOK_DETAIL_TABLE = "CREATE TABLE " + TABLE_BOOK_DETAIL + "("
                + KEY_ID1 + " INTEGER PRIMARY KEY,"
                + KEY_BOOKNAME + " TEXT,"
                + KEY_AUTHOR + " TEXT,"
                + KEY_ISBN + " TEXT,"
                + KEY_ABOUTBOOK + " TEXT " + ")";                ;

        db.execSQL(CREATE_BOOK_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_DETAIL);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Student Information
    void saveNewBook(Booknew newBuk) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ISBN, newBuk.get_isbn());
        values.put(KEY_BOOKNAME, newBuk.get_bookname());
        values.put(KEY_AUTHOR, newBuk.get_author());
        values.put(KEY_ABOUTBOOK, newBuk.get_aboutbook());



        // Inserting Row
        db.insert(TABLE_BOOK_DETAIL, null, values);
        db.close(); // Closing database connection
    }


    public boolean updateBookInfo(int updId1,  String updBookName, String updAuthor,int updIsbn, String updAboutBook) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues args = new ContentValues();

        args.put(KEY_ISBN, updIsbn);
        args.put(KEY_BOOKNAME, updBookName);
        args.put(KEY_AUTHOR, updAuthor);
        args.put(KEY_ABOUTBOOK, updAboutBook);


        return db.update(TABLE_BOOK_DETAIL, args, KEY_ID1 + "=" + updId1, null) > 0;
    }

    // Getting All Books
    public List<Booknew> bookList(String filter) {
        String query;

        if(filter.equals("")){
            query="SELECT  * FROM " + TABLE_BOOK_DETAIL;
        }else {
            query="SELECT  * FROM " + TABLE_BOOK_DETAIL + " ORDER BY " + filter;
        }
        List<Booknew> bookLinkedList = new LinkedList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Booknew buk;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                buk = new Booknew();
                buk.set_id1(Integer.parseInt(cursor.getString(0)));
                buk.set_isbn(cursor.getString(3));
                buk.set_bookname(cursor.getString(1));
                buk.set_author(cursor.getString(2));
                buk.set_aboutbook(cursor.getString(4));


                // Adding contact to list
                bookLinkedList.add(buk);

            } while (cursor.moveToNext());
        }

        // return contact list
        return bookLinkedList;
    }

    public Booknew getBook(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT  * FROM " + TABLE_BOOK_DETAIL + " WHERE id=" + id;
        Cursor cursor = db.rawQuery(query, null);
        Booknew recievedbook = new Booknew();
        if (cursor.getCount()>0) {
            cursor.moveToFirst();

            recievedbook.set_id1(Integer.parseInt(cursor.getString(0)));
            recievedbook.set_isbn(cursor.getString(3));
            recievedbook.set_bookname(cursor.getString(1));
            recievedbook.set_author(cursor.getString(2));
            recievedbook.set_aboutbook(cursor.getString(4));
        }
        return  recievedbook;
    }

     public List<Booknew> writtenBooks(String name){


           String  query="SELECT  * FROM " + TABLE_BOOK_DETAIL;

         List<Booknew> bookLinkedList = new ArrayList<Booknew>();

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);
         Booknew buk ;
         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             do{
                 buk = new Booknew();
                 if(name.equals(cursor.getString(2))) {

                     buk.set_id1(Integer.parseInt(cursor.getString(0)));
                     buk.set_isbn(cursor.getString(3));
                     buk.set_bookname(cursor.getString(1));
                     buk.set_author(cursor.getString(2));
                     buk.set_aboutbook(cursor.getString(4));
                     bookLinkedList.add(buk);
                 }

             } while (cursor.moveToNext());
         }
        return bookLinkedList;

     }

}