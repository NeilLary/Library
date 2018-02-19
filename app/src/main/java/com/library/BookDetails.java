package com.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {

    TextView txname,txauthor,txisbn, txaboutbook;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_contents);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txname=(TextView)findViewById(R.id.textView4);
        txauthor=(TextView)findViewById(R.id.textView6);
        txisbn=(TextView)findViewById(R.id.textView7);
        txaboutbook=(TextView)findViewById(R.id.textView8);

        txname.setText(getIntent().getStringExtra("bookname"));
        txauthor.setText("by  "+getIntent().getStringExtra("author"));
        txisbn.setText("ISBN  -  "+getIntent().getStringExtra("isbn"));
        txaboutbook.setText(getIntent().getStringExtra("aboutbook"));
    }
}
