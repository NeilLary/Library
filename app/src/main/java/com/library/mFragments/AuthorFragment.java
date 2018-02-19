package com.library.mFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.library.DBHandlerAuthor;
import com.library.R;
import com.library.mRecycler.AuthorAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public  class AuthorFragment extends Fragment {

    private RecyclerView rv2;
    private DBHandlerAuthor dbAuthor;
    private AuthorAdapter atradapter;
    private RecyclerView.LayoutManager lm2;
    private String filter = "";
    TextView atrnumber;

    public AuthorFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_author,null);
        rv2=(RecyclerView)view.findViewById(R.id.authorrecycler);
        lm2=new LinearLayoutManager(getActivity());
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(lm2);
        dbAuthor=new DBHandlerAuthor(getContext());
        atradapter=new AuthorAdapter(dbAuthor.authorList(filter),getContext(),rv2);
        rv2.setAdapter(atradapter);
        atrnumber= (TextView)view.findViewById(R.id.authornumber);
        atrnumber.setText(Integer.toString(atradapter.getItemCount()));
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
