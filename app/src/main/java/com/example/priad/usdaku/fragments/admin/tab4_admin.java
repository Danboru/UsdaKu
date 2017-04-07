package com.example.priad.usdaku.fragments.admin;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterLaporan;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Laporan;
import com.example.priad.usdaku.utils.GetTimeNow;

import java.util.ArrayList;
import java.util.Calendar;

public class tab4_admin extends Fragment {

    ArrayList<Laporan> list = new ArrayList<>();

    public tab4_admin() {
    }

    /**
     * Kelas onCreate, Kelas yang di jalankan setelah konstruktor
     * */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab4_admin, container, false);
        OpenHelper db = new OpenHelper(getContext());


        //Cara sederhana menampilkan data
//        ArrayList listKu = new ArrayList();
//        listKu.add(new Riwayat("Usda Makanan Ringan",  1000 ));
//        listKu.add(new Riwayat("Usda Makanan Ringan",  1000 ));
//        ListAdapter adapter = new AdapterRiwayat(getActivity(), listKu);
//        ListView listView = (ListView) view.findViewById(R.id.lv_riwayatPembelian);
//        listView.setAdapter(adapter);

        String tanggal, hari, bulan, tahun, hariIni, curentDate;
        GetTimeNow dataTime = new GetTimeNow();

        tanggal = dataTime.getTanggal();
        hari = dataTime.getHari();
        bulan = dataTime.getBulan();
        tahun = dataTime.getTahun();

        curentDate = dataTime.getCurrentDate();

        //Data hari ini
        hariIni =  hari + " - " + tanggal + " - " + bulan + " - " + tahun;

        db.addLaporan(new Laporan("Danang", "Admin Tolong Benahi Bug di aplikasi ini", hariIni));
        db.addLaporan(new Laporan("Iqbal", "Admin Tolong Benahi Bug di aplikasi ini", hariIni));
        db.addLaporan(new Laporan("Eka", "Admin Tolong Benahi Bug di aplikasi ini", hariIni));

        //menyimpan data ke dalam list
        list = db.getAllLaporan();

        AdapterLaporan adapter = new AdapterLaporan(getActivity(), list);
        ListView listKomplain = (ListView) view.findViewById(R.id.lv_komplainUser);
        listKomplain.setAdapter(adapter);

        return view;
    }
}
