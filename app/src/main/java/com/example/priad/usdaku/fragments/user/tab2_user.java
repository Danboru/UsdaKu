package com.example.priad.usdaku.fragments.user;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterRiwayat;
import com.example.priad.usdaku.adapter.AdapterTransaksi;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Riwayat;
import com.example.priad.usdaku.provider.Transaksi;

import java.util.ArrayList;

public class tab2_user extends Fragment {

    private ArrayList list = new ArrayList();

    public tab2_user() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_user, container, false);

        ArrayList listKu = new ArrayList();
        listKu.add(new Transaksi("nama", 1000, 20 ));

        ListAdapter adapter = new AdapterTransaksi(getActivity(), listKu);

        ListView listView = (ListView) view.findViewById(R.id.lv_transaksi);
        listView.setAdapter(adapter);

        return view;
    }

}
