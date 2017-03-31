package com.example.priad.usdaku.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.priad.usdaku.R;
import com.example.priad.usdaku.provider.User;

import java.util.ArrayList;

/**
 * Created by priad on 31/03/2017.
 */
public class AdapterUser extends ArrayAdapter {

    private ArrayList list;
    private Activity act;

    public AdapterUser(Activity context, ArrayList object) {
        super(context, R.layout.row_daftar_user,object);
        this.list = object;
        this.act = context;
    }

    //Sesuaikan dengan data yang akan di tampilkan
    static class ViewHolder {
        protected ImageView foto_profile;
        protected TextView nama_user;
        protected TextView email_user;
        protected TextView nim_user;
        protected TextView poin_user;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = act.getLayoutInflater();
            view = inflater.inflate(R.layout.row_daftar_user, null);

            AdapterUser.ViewHolder holder = new ViewHolder();

            //Sesuaikan dengan view yang ada di dalam xml yang di gunakan
            holder.foto_profile = (ImageView) view.findViewById(R.id.foto_user);
            holder.nama_user = (TextView) view.findViewById(R.id.nama_user);
            holder.email_user = (TextView) view.findViewById(R.id.email_user);
            holder.nim_user = (TextView) view.findViewById(R.id.nim_user);
            holder.poin_user = (TextView) view.findViewById(R.id.point_user);
            view.setTag(holder);
        }

        AdapterUser.ViewHolder holder = (ViewHolder) view.getTag();

        User user = (User) list.get(position);

        holder.foto_profile.setImageResource(R.mipmap.ic_launcher);
        holder.nama_user.setText(user.getNamadepan_user() + user.getNamabelakang_user());
        holder.email_user.setText(user.getEmail_user());
        holder.nim_user.setText(user.getNim());
        holder.poin_user.setText(user.getPoin());

        return view;
    }

}
