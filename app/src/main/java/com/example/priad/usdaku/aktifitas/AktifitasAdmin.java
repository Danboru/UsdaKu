package com.example.priad.usdaku.aktifitas;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
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

/**
 *
 * Kelas yang mengatur aktifitas admin, kelas ini menjadi file yang memberikan privilage apa saja yang bisa di lakukan oleh
 * admin di dalam proses bisnis di aplikasi ini
 * pengeturan koneksi antar satu fragment dengan fragment lainnya
 *
 * @author DanangPriabada
 *
 * */
public class AktifitasAdmin extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    /**
     * Kelas onCreate, Kelas yang di jalankan setelah konstruktor
     * */
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

    //Adapter untuk setiap tab yang akan di tampilkan
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
            case R.id.tentangaplikasi :
                //Memanggil popup
                showTentangAplikasi();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTentangAplikasi(){
        final Dialog dialog = new Dialog(AktifitasAdmin.this);
        //Mengeset judul dialog
        dialog.setTitle("Tentang Aplikasi");
        //Mengeset layout
        dialog.setContentView(R.layout.popup_tentang_aplikasi);
        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);
        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);
        //Inisialisasi View
        //Button cancelButton = (Button) dialog.findViewById(R.id.button_cancel);
        //Menampilkan custom dialog
        dialog.show();
    }
}
