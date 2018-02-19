package com.library;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Neil on 4.2.18.
 */

public class Book extends AppCompatActivity{

    Button btnAddBook;
    Toolbar toolbar = null;
    private DBHandlerBook db1;
private DBHandlerAuthor db2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.book);

        toolbar = (Toolbar) findViewById(R.id.booktoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddBook = (Button) findViewById(R.id.button1);

        //spinner definition start
        final Spinner Spinner1 = (Spinner) findViewById(R.id.Spinner1);

        db2=new DBHandlerAuthor(this);

        String[] array = new String[1];
        array= db2.authornameList(this);
        String[] Athslt = new String[]{"Select Author"};
        String[] list= concat(Athslt,array);
        final List<String> authorList = new ArrayList<>(Arrays.asList(list));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, authorList) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        Spinner1.setAdapter(spinnerArrayAdapter);

        Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//spinner definition stop

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBook();
            }
        });
    }

        private void saveBook(){

            db1=new DBHandlerBook(this);
                //		Get BookName
                EditText entBookName = (EditText) findViewById(R.id.editText1);

                String bookname = entBookName.getText().toString();

                //		Get ISBN
                EditText entIsbn = (EditText) findViewById(R.id.editText2);
                String isbn = entIsbn.getText().toString();
                //Spinner
                Spinner entAuthor = (Spinner) findViewById(R.id.Spinner1);
                String author = entAuthor.getSelectedItem().toString();
                String atr = "Select Author";
                int c = 0;
                try {
                    if (author == atr) {
                        c = 1 / 0;
                    }

                } catch (Exception e) {
                    c = 1;
                }
                //		Get Book Description
                EditText entAboutBook = (EditText) findViewById(R.id.editText3);
                String aboutbook = entAboutBook.getText().toString();
                boolean check_bookname = checkbookname(bookname);
                boolean check_isbn = checkisbn(isbn);
                boolean check_author = checkauthor(author);
                boolean check_aboutbook = checkaboutbook(aboutbook);

                if (!check_bookname || !check_isbn || !check_author || !check_aboutbook || c == 1) {

                    Toast.makeText(getApplicationContext(), "Check the Data Again..", Toast.LENGTH_LONG).show();
                } else {

                    db1.saveNewBook(new Booknew( bookname, author,isbn, aboutbook));
                    Toast.makeText(getApplicationContext(), "Book Added to the List..", Toast.LENGTH_LONG).show();
                    finish();

                }
            }

            private boolean checkbookname(String bookname) {

                if (bookname.length() == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            private boolean checkisbn(String isbn) {

                if (isbn.length() == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            private boolean checkauthor(String author) {

                if (author.length() == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            private boolean checkaboutbook(String aboutbook) {

                if (aboutbook.length() == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            private String[] concat(String[] a, String[] b) {
                int alen = a.length;
                int blen = b.length;
                String[] c = new String[alen + blen];
                System.arraycopy(a, 0, c, 0, alen);
                System.arraycopy(b, 0, c, alen, blen);
                return c;
            }

            }

