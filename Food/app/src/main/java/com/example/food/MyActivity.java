package com.example.food;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.food.category.beans.Category;
import com.example.food.fragments.CategoryFragment;
import com.example.food.fragments.HomeFragment;
import com.example.food.fragments.MoreFragment;
import com.example.food.fragments.SearchFragment;

public class MyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
   // SearchFragment searchFragment;
    MoreFragment moreFragment;


    SearchView searchview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundResource(R.color.myColor);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        searchview=(SearchView)findViewById(R.id.searchview);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                SearchFragment searchFragment=SearchFragment.newInstance(query);
                getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,searchFragment).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        initFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cotainer,homeFragment).commit();

    }

    private void initFragment() {
        homeFragment=new HomeFragment();
        categoryFragment=new CategoryFragment();
       // searchFragment =new SearchFragment();
        moreFragment=new MoreFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
               getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,homeFragment).commit();
        } else if (id == R.id.category) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,categoryFragment).commit();
        } else if (id == R.id.more) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cotainer,moreFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
