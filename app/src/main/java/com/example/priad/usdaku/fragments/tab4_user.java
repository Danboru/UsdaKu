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
import com.example.priad.usdaku.adapter.AdapterJualan;

import java.util.ArrayList;

public class tab4_user extends Fragment {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    public tab4_user() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab4_user, container, false);

        dataSet = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView) view.findViewById(R.id.barangYangDiJual);
        rvView.setHasFixedSize(false);

        /**
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */
        layoutManager = new LinearLayoutManager(getContext());
        rvView.setLayoutManager(layoutManager);

        adapter = new AdapterJualan(dataSet);
        rvView.setAdapter(adapter);

        return view;

    }

    private void initDataset() {

        /**
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");
        dataSet.add("Barang yang Di Jual");



    }
}