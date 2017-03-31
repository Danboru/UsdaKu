package com.example.priad.usdaku.fragments.user;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        dataSet.add("Lemper");
        dataSet.add("Kue Serabi");
        dataSet.add("Kue Lumpur");
        dataSet.add("Nagasari");
        dataSet.add("Kue Mangkok");
        dataSet.add("Onde-onde");

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Pengaturan Pesanan");
        menu.add(0, v.getId(), 0, "Hapus Pesanan");
        menu.add(0, v.getId(), 0, "Update Pesanan");
        menu.add(0, v.getId(), 0, "Tentang Pesanan");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals("Hapus Pesanan")){

            Toast.makeText(getContext(), "Pesanan Di hapus", Toast.LENGTH_SHORT).show();

        }

        return true;
    }
}
