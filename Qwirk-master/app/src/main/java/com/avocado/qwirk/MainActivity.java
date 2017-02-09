package com.avocado.qwirk;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import android.content.Intent;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    SharedPreferences authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home_white_24dp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_assignment_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_date_range_white_24dp);

        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().show();
        Bundle bundle = getIntent().getExtras();
        //if(bundle.get("fragment") == "CalendarFragment"){
            //pass to calendar with bundle.get("id")

        //}
        authentication = getSharedPreferences(App.AUTHPREFS, 0);
        if (!authentication.getBoolean("Authenticated", false))
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        CalendarFragment calendar = new CalendarFragment(adapter);
        PlaceHolderFragment profile = new PlaceHolderFragment();
        AssignmentFeedFragment assignfeed = new AssignmentFeedFragment();

        Bundle bundle2 = new Bundle();
        bundle2.putString("text", "Placeholder for the Profile page");
        profile.setArguments(bundle2);

        Bundle bundle = new Bundle();
        bundle.putString("text", "Placeholder for the Calendar page");
        calendar.setArguments(bundle);

        adapter.addFragment(assignfeed, ""); //Profile
        adapter.addFragment(new PostFeedFragment(), ""); //Feed
        adapter.addFragment(calendar, ""); //Calendar
        viewPager.setAdapter(adapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.search) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.logout) {
            SharedPreferences.Editor editor = authentication.edit();
            editor.putBoolean("Authenticated", false);
            editor.apply();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clickFunction(View v) {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        intent.putExtra("user_image", R.id.user_image);
        startActivity(intent);
    }
}
