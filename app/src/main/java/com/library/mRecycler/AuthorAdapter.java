package com.library.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.library.AuthorDetails;
import com.library.Authornew;
import com.library.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 8.2.18.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorHolder> {

     private Context c2;
     private  List<Authornew> atr;
     private RecyclerView rv2;

    public AuthorAdapter(List<Authornew> atr, Context c2,  RecyclerView rv2) {
        this.c2 = c2;
        this.atr = atr;
        this.rv2 = rv2;
    }
    class AuthorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView authorname, age, gender, dob, aboutauthor;
        List<Authornew> authors = new ArrayList<Authornew>();
        Context c2;
        AuthorHolder(View itemView,Context c2, List<Authornew> authors) {
            super(itemView);
            this.authors=authors;
            this.c2=c2;
            itemView.setOnClickListener(this);
            authorname = (TextView) itemView.findViewById(R.id.cardviewauthorname);
            age = (TextView) itemView.findViewById(R.id.agecenter);
            gender = (TextView) itemView.findViewById(R.id.cardgender);
            dob = (TextView) itemView.findViewById(R.id.bornin);
        }

    @Override
    public void onClick(View v) {

        int position = getAdapterPosition();
        Authornew author = this.authors.get(position);
        Intent intent = new Intent(this.c2, AuthorDetails.class);
        intent.putExtra("authorname", author.get_authorname());
        intent.putExtra("age", author.get_age());
        intent.putExtra("gender", author.get_gender());
        intent.putExtra("dob", author.get_dob());
        intent.putExtra("aboutauthor", author.get_aboutauthor());

        this.c2.startActivity(intent);
    }
}
    public void add(int pos, Authornew author){
        atr.add(pos,author);
        notifyItemInserted(pos);

    }


    @Override
    public AuthorAdapter.AuthorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.authorcard,parent,false);
        return new AuthorHolder(view,c2,atr);
    }

    @Override
    public void onBindViewHolder(AuthorHolder holder, final int position) {
        final Authornew authornew=atr.get(position);
        holder.authorname.setText(authornew.get_authorname());
        holder.gender.setText(authornew.get_gender());
        holder.dob.setText(authornew.get_dob());
        holder.age.setText(authornew.get_age());

    }

    @Override
    public int getItemCount() {
        return atr.size();
    }


}


