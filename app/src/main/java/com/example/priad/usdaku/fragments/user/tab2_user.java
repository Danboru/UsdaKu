package com.example.priad.usdaku.fragments.user;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterTransaksi;
import com.example.priad.usdaku.databases.OpenHelper;

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
        OpenHelper db = new OpenHelper(getContext());

        //Memasukkan data kedalam list
        list = db.getAllTransaksi();
        ListAdapter adapter = new AdapterTransaksi(getActivity(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_transaksi);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Item " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
