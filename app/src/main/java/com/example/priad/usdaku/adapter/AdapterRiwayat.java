package com.example.priad.usdaku.adapter;

/**
 * Created by priad on 2/5/2017.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.provider.Riwayat;

import java.util.ArrayList;

public class AdapterRiwayat extends ArrayAdapter {

    private ArrayList list;
    private Activity act;

    public AdapterRiwayat(Activity context, ArrayList objects) {
        super(context, R.layout.row_riwayat_pembelian_local, objects);
        this.list = objects;
        this.act = context;
    }

    static class ViewHolder {
        protected ImageView gambar_barang;
        protected TextView nama_barang_riwayat;
        protected TextView harga_barang_riwayat;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.row_riwayat_pembelian_local, null);

            ViewHolder holder = new ViewHolder();
            holder.gambar_barang = (ImageView) view.findViewById(R.id.gambar_barang_riwayat);
            holder.nama_barang_riwayat = (TextView) view.findViewById(R.id.nama_barang_riwayat);
            holder.harga_barang_riwayat = (TextView) view.findViewById(R.id.harga_barang_riwayat);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();


        Riwayat riwayat = (Riwayat) list.get(position);
        String harga = String.valueOf(riwayat.getHarga_barang_riwayat());

        holder.gambar_barang.setImageResource(R.drawable.usdaku);
        holder.nama_barang_riwayat.setText(riwayat.getNama_barang_riwayat());
        holder.harga_barang_riwayat.setText("Rp." + harga);

        return view;
    }

}
