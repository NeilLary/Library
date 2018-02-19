package com.library.mFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.library.DBHandlerBook;
import com.library.R;
import com.library.mRecycler.BookAdapter1;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {

    private RecyclerView rv1;
    private BookAdapter1 bukadapter;
    private DBHandlerBook dbBook;
    private RecyclerView.LayoutManager lm1;
    private String filter = "";
    TextView buknumber;

    public BookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

View view = inflater.inflate(R.layout.fragment_book, container, false);
        rv1=(RecyclerView)view.findViewById(R.id.bookrecycler);
        lm1=new LinearLayoutManager(getActivity());
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(lm1);
        dbBook=new DBHandlerBook(getContext());
        bukadapter=new BookAdapter1(dbBook.bookList(filter),getContext(),rv1);
        buknumber= (TextView)view.findViewById(R.id.booknumber);
        buknumber.setText(Integer.toString(bukadapter.getItemCount()));

        rv1.setAdapter(bukadapter);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
