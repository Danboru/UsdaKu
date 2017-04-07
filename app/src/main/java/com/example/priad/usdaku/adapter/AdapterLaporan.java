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
 *
 * Adapter di gunakan untuk mengadaptasikan data yang berbeda - beda
 * agar bisa kompatible dengan beberapa view
 * untuk kasus ini dapter di gunakan untuk mengadaptasikan data agar bisa di tampilkan di textview
 * yang bersangkutan
 *
 * @author DanangPriabada
 *
 * */
public class AdapterLaporan extends ArrayAdapter {

    //Variable penting
    private ArrayList listLaporan;
    private Activity context;

    public AdapterLaporan(Activity context, ArrayList resource) {
        //Menggunakan custom layout
        super(context, R.layout.row_laporan_user,  resource);
        this.listLaporan = resource;
        this.context = context;
    }

    /**
     * Ini adalah inner class yang di gunakan untuk penyesuaian view yang akan di gunakan
     * adapter membutuhkannya untuk mengetahui dimana data akan di leteakkan
     * */
    static class ViewHolder{
        //Class yang menampung view di dalam row layout
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
            //Menggunakan custom layout
            view = inflater.inflate(R.layout.row_laporan_user, null);

            ViewHolder holder = new ViewHolder();
            //Set id untuk setiap view yang ada di holder
            holder.nama_pelapor = (TextView) view.findViewById(R.id.txt_namapelapor);
            holder.pesan_terlampir = (TextView) view.findViewById(R.id.txt_pesanterlampir);
            holder.tanggal_pelaporan = (TextView) view.findViewById(R.id.txt_tanggallapor);

            //Simpan Holder kedalah covertView
            view.setTag(holder);
        }
            ViewHolder holder = (ViewHolder) view.getTag();

            //Pastikan object laporan berisikan posisi yang di ambil dari get(position)
            Laporan laporan = (Laporan) listLaporan.get(position);

            //Sesuaikan dengan tipda data yang ada di provider
            holder.nama_pelapor.setText(laporan.getNama_pelapor());
            holder.pesan_terlampir.setText(laporan.getPesan_terlampir());
            holder.tanggal_pelaporan.setText(laporan.getTanggal_pelaporan());

        //Mengembalikan view yang sudah di set dengan data holder
        return  view;
    }
}
