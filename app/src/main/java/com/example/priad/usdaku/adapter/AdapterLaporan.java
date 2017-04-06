package com.example.priad.usdaku.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.provider.Laporan;

import java.util.ArrayList;

/**
 * Created by priad on 06/04/2017.
 */

public class AdapterLaporan extends ArrayAdapter {

    //Variable penting
    private ArrayList listLaporan;
    private Activity context;

    public AdapterLaporan(Activity context, ArrayList resource) {
        super(context, R.layout.row_laporan_user,  resource);
        this.listLaporan = resource;
        this.context = context;
    }

    //Class yang menampung ciew di dalam row layout
    static class ViewHolder{
        TextView nama_pelapor;
        TextView pesan_terlampir;
        TextView tanggal_pelaporan;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(convertView == null){

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.row_laporan_user, null);

            ViewHolder holder = new ViewHolder();
            holder.nama_pelapor = (TextView) view.findViewById(R.id.txt_namapelapor);
            holder.pesan_terlampir = (TextView) view.findViewById(R.id.txt_pesanterlampir);
            holder.tanggal_pelaporan = (TextView) view.findViewById(R.id.txt_tanggallapor);
            view.setTag(holder);
        }

            ViewHolder holder = (ViewHolder) view.getTag();

        //Pastikan object laporan berisikan posisi yang di ambil dari get(position)
            Laporan laporan = (Laporan) listLaporan.get(position);

        //Sesuaikan dengan tipda data yang ada di provider
            holder.nama_pelapor.setText(laporan.getNama_pelapor());
            holder.pesan_terlampir.setText(laporan.getPesan_terlampir());
            holder.tanggal_pelaporan.setText(laporan.getTanggal_pelaporan());

        return  view;
    }
}
