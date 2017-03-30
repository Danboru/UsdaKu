package com.example.priad.usdaku.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.javafiles.Barang;

import java.util.ArrayList;

public class AdapterBarang extends ArrayAdapter {

    private ArrayList list;
    private Activity act;

    public AdapterBarang(Activity context, ArrayList objects) {
        super(context, R.layout.layout_barang, objects);
        this.list = objects;
        this.act = context;
    }

    static class ViewHolder {
        protected ImageView icon;
        protected TextView nama;
        protected TextView keterangan;
        protected TextView harga;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.layout_barang, null);

            ViewHolder holder = new ViewHolder();
            holder.icon = (ImageView) view.findViewById(R.id.item_icon);
            holder.nama = (TextView) view.findViewById(R.id.item_nama);
            holder.keterangan = (TextView) view
                    .findViewById(R.id.item_keterangan);
            holder.harga = (TextView) view.findViewById(R.id.item_harganya);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        
        Barang phone = (Barang) list.get(position);
        String harga = String.valueOf(phone.getHarga());

        holder.icon.setImageResource(R.mipmap.ic_launcher);
        holder.nama.setText(phone.getName());
        holder.keterangan.setText(phone.getBrand());
        holder.harga.setText("Rp." + harga);

        return view;
    }
}
