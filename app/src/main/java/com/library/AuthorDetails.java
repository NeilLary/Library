package com.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.library.mRecycler.BookAdapter2;
import java.util.ArrayList;
import java.util.List;

public class AuthorDetails extends AppCompatActivity {

    TextView txname,txage, txbornin;
    Toolbar toolbar;

    private RecyclerView rv1;
    private BookAdapter2 bukadapter;
    private DBHandlerBook dbBook;
    private RecyclerView.LayoutManager lm1;
    private String filter = "";
    TextView writtenbooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_contents);

        rv1=(RecyclerView)findViewById(R.id.writtenbooksrecycler);
        lm1=new LinearLayoutManager(this);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(lm1);
        dbBook=new DBHandlerBook(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txname=(TextView)findViewById(R.id.textView9);
        txage=(TextView)findViewById(R.id.textView10);
        txbornin=(TextView)findViewById(R.id.textView12);

        txname.setText(getIntent().getStringExtra("authorname"));
        txage.setText("Age  "+getIntent().getStringExtra("age")+" / " + getIntent().getStringExtra("gender"));
        txbornin.setText("Born in  "+getIntent().getStringExtra("dob"));
        List<Booknew> writtenbookslist =new ArrayList<>();
        writtenbookslist=dbBook.writtenBooks(getIntent().getStringExtra("authorname"));

        bukadapter=new BookAdapter2(writtenbookslist,this,rv1);
        writtenbooks=(TextView) findViewById(R.id.books_written);
        writtenbooks.setText(Integer.toString(bukadapter.getItemCount()));

        rv1.setAdapter(bukadapter);


    }
}
