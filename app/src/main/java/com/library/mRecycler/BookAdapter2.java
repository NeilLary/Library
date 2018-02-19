package com.library.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.library.BookDetails;
import com.library.Booknew;
import com.library.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 8.2.18.
 */

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.BookHolder> {

    private List<Booknew> buk;
    private Context c1;
    private RecyclerView rv1;

    public BookAdapter2(List<Booknew> buk, Context c1,  RecyclerView rv1) {
        this.c1 = c1;
        this.buk = buk;
        this.rv1 = rv1;
    }

    class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bookname, author, isbn, aboutbook;
        List<Booknew> books = new ArrayList<Booknew>();
        Context c1;
        BookHolder(View itemView, Context c1, List<Booknew> books) {
            super(itemView);
            this.books=books;
            this.c1=c1;
            itemView.setOnClickListener(this);
            bookname = (TextView) itemView.findViewById(R.id.cardviewbookname);
            author = (TextView) itemView.findViewById(R.id.cardviewauthor);
            isbn = (TextView) itemView.findViewById(R.id.isbnright);
            aboutbook = (TextView) itemView.findViewById(R.id.cardviewcontent);
        }
        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Booknew book = this.books.get(position);
            Intent intent = new Intent(this.c1, BookDetails.class);
            intent.putExtra("bookname", book.get_bookname());
            intent.putExtra("author", book.get_author());
            intent.putExtra("isbn", book.get_isbn());
            intent.putExtra("aboutbook", book.get_aboutbook());
            this.c1.startActivity(intent);
        }
    }
    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bookcard,parent,false);
        return new BookHolder(view,c1,buk);
    }

    @Override
    public void onBindViewHolder(BookHolder holder,final int position) {
        final Booknew booknew=buk.get(position);
        holder.bookname.setText(booknew.get_bookname());
        holder.author.setText(booknew.get_author());
        holder.aboutbook.setText(booknew.get_aboutbook());
        holder.isbn.setText(booknew.get_isbn());

    }

    @Override
    public int getItemCount() {
        return buk.size();
    }


}


