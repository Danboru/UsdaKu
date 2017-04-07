package com.example.priad.usdaku.aktifitas;

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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.priad.usdaku.MainActivity;
import com.example.priad.usdaku.R;
import com.example.priad.usdaku.fragments.user.tab2_user;
import com.example.priad.usdaku.fragments.user.tab4_user;
import com.example.priad.usdaku.fragments.user.tab1_user;
import com.example.priad.usdaku.fragments.user.tab3_user;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Kelas yang mengatur aktifitas user, kelas ini menjadi file yang memberikan privilage apa saja yang bisa di lakukan oleh
 * user di dalam proses bisnis di aplikasi ini
 * pengeturan koneksi antar satu fragment dengan fragment lainnya
 *
 * @author DanangPriabada
 *
 * */
public class AktifitasUser extends AppCompatActivity {

    //Varible yang di gunakan untuk style tab
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bagian_user);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Menampilkan button up di toolbar
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Title aplikasi di set secara manual
        ((TextView) findViewById(R.id.main_toolbar_title)).setText("Usdaku");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    //Untuk menambahkan tab baru
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab1_user(), "PASAR");
        adapter.addFragment(new tab2_user(), "PEMBELIAN");
        adapter.addFragment(new tab3_user(), "PROFILE");
        adapter.addFragment(new tab4_user(), "PENJUALAN");

        viewPager.setAdapter(adapter);
    }

    //Inner class yang di gunakan untuk menyesuaikan tab yang akan di tampilkan
    static class ViewPagerAdapter extends FragmentPagerAdapter {
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

    //Optoin menu yang ada di pojok kanan atas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);//Menu Resource, Menu
        return true;
    }

    //Item yang ada di option menu
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
