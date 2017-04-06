package com.example.priad.usdaku.fragments.admin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.adapter.AdapterBarang;
import com.example.priad.usdaku.adapter.AdapterUser;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Barang;
import com.example.priad.usdaku.provider.Transaksi;
import com.example.priad.usdaku.provider.User;

import java.util.ArrayList;

public class tab2_admin extends Fragment {

    //Varible yang di gunkan untuk menampung data yang berasal dari datbase
    private ArrayList list = new ArrayList();

    public tab2_admin() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_admin, container, false);
        OpenHelper db = new OpenHelper(getContext());

        //Memasukkan data kedalam list
        list = db.getAllUser();
        ListAdapter adapter = new AdapterUser(getActivity(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_barangjual_admin);
        listView.setAdapter(adapter);

        final SwipeRefreshLayout swLayout = (SwipeRefreshLayout) view.findViewById(R.id.swlayout);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        swLayout.setColorSchemeResources(R.color.atas,R.color.bawah);

        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Berhenti berputar/refreshing
                        swLayout.setRefreshing(false);
                        OpenHelper db = new OpenHelper(getContext());

                        Toast.makeText(getActivity(), "Uptodate", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Memanggil fungsi show popup
                showUpdateUserDialog(position);
            }
        });
        return view;
    }

    //Memunculkan dialog saat update user
    private void showUpdateUserDialog(int posisi) {

        final User user = (User) list.get(posisi);

        final Dialog dialog = new Dialog(getContext());
        //Mengeset judul dialog
        dialog.setTitle("Update User");

        //Mengeset layout
        dialog.setContentView(R.layout.popup_updateuser);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Variable View
        final EditText namaDepan, namaBelakang, nimUser, pointUser, emailUser, statusUser, passUser;

        //Inisialisasi view
        namaDepan = (EditText) dialog.findViewById(R.id.txt_namauser_depan);
        namaBelakang = (EditText) dialog.findViewById(R.id.txt_namauser_belakang);
        nimUser = (EditText) dialog.findViewById(R.id.txt_nimUser);
        pointUser = (EditText) dialog.findViewById(R.id.txt_pointuser);
        emailUser = (EditText) dialog.findViewById(R.id.txt_emailuser);
        statusUser = (EditText) dialog.findViewById(R.id.txt_statususer);
        passUser = (EditText) dialog.findViewById(R.id.txt_passuser);

        Button updateUser, deleteUser;
        updateUser = (Button) dialog.findViewById(R.id.btn_updateuser);
        deleteUser = (Button) dialog.findViewById(R.id.btn_deleteuser);

        //Mutator view
        namaDepan.setText(user.getNamadepan_user().toString().trim());
        namaBelakang.setText(user.getNamabelakang_user().toString().trim());
        nimUser.setText(String.valueOf(user.getNim()));
        pointUser.setText(String.valueOf(user.getPoin()));
        emailUser.setText(user.getEmail_user());
        statusUser.setText("Mahasiswa");
        passUser.setText(user.getPassword_user());

        //SetListener untuk button update
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namaDepanNya, namaBelakangNya, emailUserNya, statusUserNya, passUserNya;
                int nimUserNya,pointUserNya ;
                namaDepanNya = namaDepan.getText().toString();
                namaBelakangNya = namaBelakang.getText().toString();
                nimUserNya = Integer.parseInt(nimUser.getText().toString());
                pointUserNya = Integer.parseInt(pointUser.getText().toString());
                emailUserNya = emailUser.getText().toString();
                statusUserNya = statusUser.getText().toString();
                passUserNya = passUser.getText().toString();

                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                OpenHelper db = new OpenHelper(getContext());
                db.updateUser(new User(user.getId_user(), namaDepanNya, namaBelakangNya, emailUserNya,
                        nimUserNya, passUserNya, pointUserNya, statusUserNya, null ));
                db.close();
                dialog.dismiss();

            }
        });

        //SetListener untuk button delete
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Menampilkan alert dialog untuk verifikasi penghapusan barang
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setCanceledOnTouchOutside(false);//Supaya tidak hilang saat di click di luar
                alertDialog.setMessage("Apakah anda yakin ingin menghapus " + user.getNamadepan_user() + " ?");
                alertDialog.setTitle("VERIFIKASI DELETE");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //WORKING
                        Toast.makeText(getContext(), user.getNamadepan_user() + " Sudah di Hapus", Toast.LENGTH_SHORT).show();
                        //Menjalankan fungsi hapus barang
                        OpenHelper db = new OpenHelper(getContext());
                        db.deleteUser(new User(user.getId_user(), null, null, null, 0, null, 0, null, null));
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Penghapusan Di Batalkan", Toast.LENGTH_SHORT).show();
                    }
                });

                //Menampilkan popup
                alertDialog.show();
                //Menutup popup
                dialog.dismiss();
            }
        });

        //Menampilkan custom dialog
        dialog.show();
    }


}
