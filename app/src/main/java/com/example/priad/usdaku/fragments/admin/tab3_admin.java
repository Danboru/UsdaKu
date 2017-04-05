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

    public tab3_admin() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_admin, container, false);

        return view;
    }
}
