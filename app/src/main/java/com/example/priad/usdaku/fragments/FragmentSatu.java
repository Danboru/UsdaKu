package com.example.priad.usdaku.fragments;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterBarang;
import com.example.priad.usdaku.javafiles.Barang;

import java.util.ArrayList;

public class FragmentSatu extends Fragment {

    public FragmentSatu() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_satu, container, false);

        ArrayList list = new ArrayList();
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 1000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 2000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 5000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 7000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 9000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 12000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 10000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 1000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 7000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 15000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 1000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 2000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 5000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 7000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 9000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 12000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 10000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 1000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 7000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 15000 ));


        ListAdapter adapter = new AdapterBarang(getActivity(), list);



        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_satu);
        listView.setAdapter(adapter);

        final SwipeRefreshLayout swLayout = (SwipeRefreshLayout) view.findViewById(R.id.swlayout);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        swLayout.setColorSchemeResources(R.color.atas,R.color.atas);


        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        // Berhenti berputar/refreshing
                        swLayout.setRefreshing(false);

                        Toast.makeText(getActivity(), "Berhasil Load Data", Toast.LENGTH_SHORT).show();

                    }
                }, 5000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();

                alertDialog.setCanceledOnTouchOutside(false);//supaya tidak hilang saat di click di luar
                alertDialog.setMessage("Apakah anda ingin melanjutkan pemesanan ?");

                alertDialog.setTitle("PEMESANAN");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Pesanan Anda Terkirim", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Pemesanan Di Batalkan", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();

            }
        });


        return view;

    }


}
