package com.example.priad.usdaku.fragments.admin;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.aktifitas.AktifitasAdmin;
import com.example.priad.usdaku.databases.OpenHelper;

public class tab3_admin extends Fragment {

    TextView txt_jumlahUser, txt_jumlahBarang, txt_jumlahTransaksi, txt_jumlahKomplain;

    public tab3_admin() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void inisialView(View view){

         txt_jumlahUser = (TextView) view.findViewById(R.id.txt_jumlahUser);
         txt_jumlahBarang = (TextView) view.findViewById(R.id.txt_jumlahBarang);
         txt_jumlahTransaksi = (TextView) view.findViewById(R.id.txt_jumlahTransaksi);
         txt_jumlahKomplain = (TextView) view.findViewById(R.id.txt_jumlahKomplain);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_admin, container, false);
        inisialView(view);

        OpenHelper db = new OpenHelper(getContext());

        //Data yang di ambil dari database
        int jumlahUser, jumlahBarang, jumlahTransaksi, jumlahKomplain;
        jumlahUser = db.getUserCount();
        jumlahBarang = db.getBarangCount();
        jumlahTransaksi = db.getTransaksiCount();
        jumlahKomplain = db.getLaporanCount();

        //Setext ke view yang bersangkutan
        txt_jumlahUser.setText(String.valueOf(jumlahUser));
        txt_jumlahBarang.setText(String.valueOf(jumlahBarang));
        txt_jumlahTransaksi.setText(String.valueOf(jumlahTransaksi));
        txt_jumlahKomplain.setText(String.valueOf(jumlahKomplain));

        return view;
    }
}
