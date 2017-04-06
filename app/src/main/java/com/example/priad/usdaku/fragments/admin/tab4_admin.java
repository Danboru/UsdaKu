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

import java.util.ArrayList;

public class tab4_admin extends Fragment {

    ArrayList<Laporan> list = new ArrayList<>();

    public tab4_admin() {
    }

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

        list = db.getAllLaporan();

        AdapterLaporan adapter = new AdapterLaporan(getActivity(), list);
        ListView listKomplain = (ListView) view.findViewById(R.id.lv_komplainUser);
        listKomplain.setAdapter(adapter);

        return view;
    }
}
