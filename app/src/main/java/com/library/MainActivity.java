package com.library;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.library.mFragments.AuthorFragment;
import com.library.mFragments.BookFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private boolean fabExpanded = false;
    private FloatingActionButton fabNew;
    private LinearLayout layoutFabBook;
    private LinearLayout layoutFabAuthor;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    NavigationView navigationView=null;
    Toolbar toolbar;
    DrawerLayout mdrawerLayout;
    ActionBarDrawerToggle mtoggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fabNew = (FloatingActionButton) this.findViewById(R.id.fabNew);
        layoutFabBook = (LinearLayout) this.findViewById(R.id.layoutFabBook);
        layoutFabAuthor = (LinearLayout) this.findViewById(R.id.layoutFabAuthor);

        fabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded){
                    fabNew.startAnimation(rotate_backward);

                    layoutFabBook.startAnimation(fab_close);
                    layoutFabAuthor.startAnimation(fab_close);
                    layoutFabBook.setClickable(false);
                    layoutFabAuthor.setClickable(false);
                    fabExpanded=false;

                } else {
                    fabNew.startAnimation(rotate_forward);

                    layoutFabBook.startAnimation(fab_open);
                    layoutFabAuthor.startAnimation(fab_open);
                    layoutFabBook.setClickable(true);
                    layoutFabAuthor.setClickable(true);
                    fabExpanded=true;
                }
            }
        });

        //Book
        FloatingActionButton fabBook = (FloatingActionButton) this.findViewById(R.id.fabBook);
        fabBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Intent1 = new Intent(MainActivity.this,
                        Book.class);
                fabNew.startAnimation(rotate_backward);

                layoutFabBook.startAnimation(fab_close);
                layoutFabAuthor.startAnimation(fab_close);
                layoutFabBook.setClickable(false);
                layoutFabAuthor.setClickable(false);
                fabExpanded=false;
                startActivity(Intent1);
            }
        });

        //Author
        FloatingActionButton fabAuthor = (FloatingActionButton) this.findViewById(R.id.fabAuthor);
        fabAuthor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Intent2 = new Intent(MainActivity.this,
                        Author.class);
                fabNew.startAnimation(rotate_backward);

                layoutFabBook.startAnimation(fab_close);
                layoutFabAuthor.startAnimation(fab_close);
                layoutFabBook.setClickable(false);
                layoutFabAuthor.setClickable(false);
                fabExpanded=false;
                startActivity(Intent2);

            }
        });

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mtoggle= new ActionBarDrawerToggle(
                this, mdrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fabExpanded){
            fabNew.startAnimation(rotate_backward);

            layoutFabBook.startAnimation(fab_close);
            layoutFabAuthor.startAnimation(fab_close);
            layoutFabBook.setClickable(false);
            layoutFabAuthor.setClickable(false);
            fabExpanded=false;

        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void displaySelectedScreen(int id){
        Fragment fragment =null;
        switch (id)
        {
            case R.id.nav_book:
                fragment=new BookFragment();
                break;

            case R.id.nav_author:
                fragment=new AuthorFragment();
                break;

        }

        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment2, fragment);
            ft.commit();
        }
        DrawerLayout drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

            @SuppressWarnings("StatementWithEmptyBody")
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id= item.getItemId();

                 displaySelectedScreen(id);

                 return true;
             }
         }
