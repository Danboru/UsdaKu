package com.example.priad.usdaku.fragments.user;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterRiwayat;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Riwayat;

import java.util.ArrayList;

public class tab3_user extends Fragment {

    private TextView namaUser,nimUser;

    public tab3_user() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_user, container, false);

        namaUser = (TextView) view.findViewById(R.id.txt_namaUserProfile);
        nimUser = (TextView) view.findViewById(R.id.txt_nimUserProfile);

        OpenHelper db = new OpenHelper(getContext());

        String username = getActivity().getIntent().getStringExtra("USERNAME");
        String userName = db.getUserName(username);
        String nim = db.getNim(username);

        namaUser.setText(userName);
        nimUser.setText(nim);

        //Cara sederhana menampilkan data
//        ArrayList listKu = new ArrayList();
//        listKu.add(new Riwayat("Usda Makanan Ringan",  1000 ));
//        listKu.add(new Riwayat("Usda Makanan Ringan",  1000 ));
//        ListAdapter adapter = new AdapterRiwayat(getActivity(), listKu);
//        ListView listView = (ListView) view.findViewById(R.id.lv_riwayatPembelian);
//        listView.setAdapter(adapter);


        return view;
    }

}
