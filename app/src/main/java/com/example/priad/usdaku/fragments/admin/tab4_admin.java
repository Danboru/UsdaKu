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
import com.example.priad.usdaku.databases.OpenHelper;

public class tab4_admin extends Fragment {

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
        OpenHelper db = new OpenHelper(getActivity());

        //Cara sederhana menampilkan data
//        ArrayList listKu = new ArrayList();
//        listKu.add(new Riwayat("Usda Makanan Ringan",  1000 ));
//        listKu.add(new Riwayat("Usda Makanan Ringan",  1000 ));
//        ListAdapter adapter = new AdapterRiwayat(getActivity(), listKu);
//        ListView listView = (ListView) view.findViewById(R.id.lv_riwayatPembelian);
//        listView.setAdapter(adapter);

        String[] dataTemp = {"Komplain 1", "Komplain 2", "Komplain 3", "Komplain 4",
                "Komplain 5", "Komplain 6", "Komplain 7", "Komplain 8"};

        ListView listKomplain = (ListView) view.findViewById(R.id.lv_komplainUser);
        ArrayAdapter<String> adapterTemp = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataTemp);
        listKomplain.setAdapter(adapterTemp);

        return view;
    }
}
