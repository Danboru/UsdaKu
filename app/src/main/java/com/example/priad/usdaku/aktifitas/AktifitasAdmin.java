package com.example.priad.usdaku.aktifitas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.priad.usdaku.MainActivity;
import com.example.priad.usdaku.R;
import com.example.priad.usdaku.fragments.admin.tab1_admin;
import com.example.priad.usdaku.fragments.admin.tab2_admin;
import com.example.priad.usdaku.fragments.admin.tab3_admin;
import com.example.priad.usdaku.fragments.admin.tab4_admin;
import com.example.priad.usdaku.fragments.user.tab1_user;
import com.example.priad.usdaku.fragments.user.tab2_user;
import com.example.priad.usdaku.fragments.user.tab3_user;
import com.example.priad.usdaku.fragments.user.tab4_user;

import java.util.ArrayList;
import java.util.List;

public class AktifitasAdmin extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bagian_admin);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set title bar
        ((TextView) findViewById(R.id.main_toolbar_title)).setText("Usdaku");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    //Untuk menambahkan tab baru
    private void setupViewPager(ViewPager viewPager) {
        AktifitasAdmin.ViewPagerAdapter adapter = new AktifitasAdmin.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab1_admin(), "PASAR");
        adapter.addFragment(new tab2_admin(), "USER");
        adapter.addFragment(new tab3_admin(), "STATUS");
        adapter.addFragment(new tab4_admin(), "LAPORAN");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
