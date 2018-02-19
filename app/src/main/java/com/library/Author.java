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

public class Author extends AppCompatActivity {


    Button btnAddAuthor;
    Toolbar toolbar = null;
    private DBHandlerAuthor db2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.author);
        toolbar = (Toolbar) findViewById(R.id.authortoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddAuthor = (Button) findViewById(R.id.button2);

//spinner definition start
        final Spinner Spinner2 = (Spinner) findViewById(R.id.Spinner2);

        String[] Gender = new String[]{
                "Gender",
                "Male",
                "Female"
        };
        final List<String> genderList = new ArrayList<>(Arrays.asList(Gender));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, genderList) {

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
        Spinner2.setAdapter(spinnerArrayAdapter);

        Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        btnAddAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAuthor();
            }
        });
    }

    private void saveAuthor() {
        db2 = new DBHandlerAuthor(this);

        //		Get AuthorName
        EditText entAuthorName = (EditText) findViewById(R.id.editText4);
        String authorname = entAuthorName.getText().toString();

        //		Get Age
        EditText entAge = (EditText) findViewById(R.id.editText5);
        String age = entAge.getText().toString();


        //Spinner
        Spinner entgender = (Spinner) findViewById(R.id.Spinner2);
        String gender = entgender.getSelectedItem().toString();
        String sex = "Gender";
        int c = 0;
        try {
            if (gender == sex) {
                c = 1 / 0;
            }

        } catch (Exception e) {
            c = 1;
        }

        //		Get DOB
        EditText entDob = (EditText) findViewById(R.id.editText6);
        String dob = entDob.getText().toString();

        //		Get AboutAuthor
        EditText entAboutAuthor = (EditText) findViewById(R.id.editText7);
        String aboutauthor = entAboutAuthor.getText().toString();

        boolean check_authorname = checkauthorname(authorname);
        boolean check_age = checkage(age);
        boolean check_gender = checkgender(gender);
        boolean check_dob = checkdob(dob);
        boolean check_aboutauthor = checkaboutauthor(aboutauthor);

        if (!check_authorname || !check_age || !check_gender || !check_dob || !check_aboutauthor || c == 1) {

            Toast.makeText(getApplicationContext(), "Check the Data Again..", Toast.LENGTH_LONG).show();
        } else {



            db2.saveNewAuthor(new Authornew(authorname, age, gender, dob, aboutauthor));
            Toast.makeText(getApplicationContext(), "Author Added to the List..", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean checkauthorname(String authorname) {

        if (authorname.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkage(String age) {

        if (age.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkgender(String gender) {

        if (gender.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkdob(String dob) {

        if (dob.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkaboutauthor(String aboutauthor) {

        if (aboutauthor.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}