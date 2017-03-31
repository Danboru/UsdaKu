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

/**
 * Created by priad on 31/03/2017.
 */
public class AdapterUser extends ArrayAdapter {

    private ArrayList list;
    private Activity act;

    public AdapterUser(Activity context, ArrayList object) {
        super(context, R.layout.layout_user ,object);
        this.list = object;
        this.act = context;
    }

    //Sesuaikan dengan data yang akan di tampilkan
    static class ViewHolder {
        protected ImageView icon;
        protected TextView nama_user;
        protected TextView email;
        protected TextView nim;
        protected TextView point;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.layout_user, null);

            AdapterBarang.ViewHolder holder = new AdapterBarang.ViewHolder();

            //Sesuaikan dengan view yang ada di dalam xml yang di gunakan
            holder.icon = (ImageView) view.findViewById(R.id.item_icon);
            holder.nama = (TextView) view.findViewById(R.id.item_nama);
            holder.keterangan = (TextView) view.findViewById(R.id.item_keterangan);
            holder.harga = (TextView) view.findViewById(R.id.item_harganya);
            view.setTag(holder);
        }

        AdapterBarang.ViewHolder holder = (AdapterBarang.ViewHolder) view.getTag();

        Barang phone = (Barang) list.get(position);
        String harga = String.valueOf(phone.getHarga());

        holder.icon.setImageResource(R.mipmap.ic_launcher);
        holder.nama.setText(phone.getName());
        holder.keterangan.setText(phone.getBrand());
        holder.harga.setText("Rp." + harga);

        return view;
    }

}
