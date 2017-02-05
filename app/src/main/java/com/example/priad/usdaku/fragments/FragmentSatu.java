package com.example.priad.usdaku.fragments;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 20000 ));
        list.add(new Barang("Usda Makanan Ringan", "Ini adalah Keterangan tentang usda yang di posting", 1000 ));

        ListAdapter adapter = new com.example.priad.usdaku.adapter.ListAdapter(getActivity(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_satu);
        listView.setAdapter(adapter);

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