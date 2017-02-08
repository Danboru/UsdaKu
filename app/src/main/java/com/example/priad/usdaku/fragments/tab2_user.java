package com.example.priad.usdaku.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterPesanan;

import java.util.ArrayList;

public class tab2_user extends Fragment {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    public tab2_user() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2_user, container, false);

        dataSet = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView) view.findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);

        /**
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */
        layoutManager = new LinearLayoutManager(getContext());
        rvView.setLayoutManager(layoutManager);

        adapter = new AdapterPesanan(dataSet);
        rvView.setAdapter(adapter);

        return view;

    }

    private void initDataset() {

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Pesanan Satu");
        dataSet.add("Pesanan Dua");
        dataSet.add("Pesanan Tiga");
        dataSet.add("Pesanan Empat");
        dataSet.add("Pesanan Lima");
        dataSet.add("Pesanan Enam");

    }
}
