package com.example.priad.usdaku.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.provider.Transaksi;

import java.util.ArrayList;

public class AdapterTransaksi extends ArrayAdapter {

    //Komponen untuk menampilkan data
    private ArrayList list;
    private Activity act;

    public AdapterTransaksi(Activity context, ArrayList objects) {
        super(context, R.layout.row_transaksi_barang, objects);
        this.list = objects;
        this.act = context;
    }

    //Sesuaikan dengan view yang ada di layout yang di gunakan
    static class ViewHolder {
        protected ImageView icon_pesanan;
        protected TextView tv_title_pesanan;
        protected TextView tv_harga_pesanan;
        protected TextView tv_jumlah_pesanan;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.row_transaksi_barang, null);

            ViewHolder holder = new ViewHolder();
            holder.icon_pesanan = (ImageView) view.findViewById(R.id.icon_pesanan);
            holder.tv_title_pesanan = (TextView) view.findViewById(R.id.tv_title_pesanan);
            holder.tv_harga_pesanan = (TextView) view.findViewById(R.id.tv_harga_pesanan);
            holder.tv_jumlah_pesanan = (TextView) view.findViewById(R.id.tv_jumlah_pesanan);
            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        Transaksi transaksi = (Transaksi) list.get(position);
        String harga = String.valueOf(transaksi.getHargabarang_transaksi());

        holder.icon_pesanan.setImageResource(R.drawable.usdaku);
        holder.tv_title_pesanan.setText(transaksi.getNamabarang_transaksi());
        holder.tv_harga_pesanan.setText(harga);
        holder.tv_jumlah_pesanan.setText(transaksi.getJumlahbarang_transaksi());

        return view;
    }
}