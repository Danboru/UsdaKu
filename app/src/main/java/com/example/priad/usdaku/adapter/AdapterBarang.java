package com.example.priad.usdaku.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.provider.Barang;

import java.util.ArrayList;

public class AdapterBarang extends ArrayAdapter {

    private ArrayList list;
    private Activity act;

    public AdapterBarang(Activity context, ArrayList objects) {
        super(context, R.layout.row_barang_yangdijual, objects);
        this.list = objects;
        this.act = context;
    }

    static class ViewHolder {
        protected ImageView icon;
        protected TextView nama_barang;
        protected TextView keterangan_barang;
        protected TextView harga_barang;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.row_barang_yangdijual, null);

            ViewHolder holder = new ViewHolder();
            holder.icon = (ImageView) view.findViewById(R.id.gambar_barang);
            holder.nama_barang = (TextView) view.findViewById(R.id.nama_barang);
            holder.keterangan_barang = (TextView) view
                    .findViewById(R.id.keterangan_barang);
            holder.harga_barang = (TextView) view.findViewById(R.id.harga_barang);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        
        Barang barang = (Barang) list.get(position);
        String harga = String.valueOf(barang.getHarga_barang());

        holder.icon.setImageResource(R.drawable.usdaku);
        holder.nama_barang.setText(barang.getNama_barang());
        holder.keterangan_barang.setText(barang.getKeterangan_barang());
        holder.harga_barang.setText("Rp." + harga);

        return view;
    }
}
