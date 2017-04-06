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
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Barang;

import java.util.ArrayList;

public class tab1_admin extends Fragment {

    private ArrayList list = new ArrayList();

    public tab1_admin() {
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
        list = db.getAllBarang();
        ListAdapter adapter = new AdapterBarang(getActivity(), list);

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

                showInfoDialog(position);

            }
        });
        return view;
    }

    //Menampilkan info barang dalam bentuk popup dialog
    private void showInfoDialog(int position) {

        //Pembuatan object database
        OpenHelper db = new OpenHelper(getContext());
        list = db.getAllBarang();

        //Data row
        final Barang barang = (Barang) list.get(position);

        //View Dialog berdasarkan context
        final Dialog dialog = new Dialog(getContext());

        //Mengeset judul dialog
        dialog.setTitle("Info Barang");

        //Mengeset layout
        dialog.setContentView(R.layout.popup_infobarang);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Inisialisasi View
        final EditText info_namaBarang = (EditText) dialog.findViewById(R.id.infoNamaBarangPopup);
        final EditText info_keteranganBarang = (EditText) dialog.findViewById(R.id.infoKeteranganBarangPopup);
        final EditText info_hargaBarang = (EditText) dialog.findViewById(R.id.infoHargaBarangPopup);
        Button updateBarangInfo = (Button) dialog.findViewById(R.id.btn_updateBarangButtonInfo);
        Button deleteBarangInfo = (Button) dialog.findViewById(R.id.btn_deleteBarangButtonInfo);

        //Mutator view
        info_namaBarang.setText(barang.getNama_barang().toString().trim());
        info_keteranganBarang.setText(barang.getKeterangan_barang().toString().trim());
        info_hargaBarang.setText(String.valueOf(barang.getHarga_barang()));

        //Listener Button
        updateBarangInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = info_namaBarang.getText().toString();
                String keterangan = info_keteranganBarang.getText().toString();
                Integer harga = Integer.parseInt(info_hargaBarang.getText().toString());

                //Menangani ketika barang gagal di update, agar program tidak langsung keluar (Forceclose)
                try {
                    //Menjalankan fungsi update
                    OpenHelper db = new OpenHelper(getContext());
                    db.updateBarang(new Barang(barang.getId_barang(), nama  , harga, keterangan) );
                    Toast.makeText(getContext(), "Barang di Update", Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(getContext(), "Kesalahan saat Memanggil fungsi update" , Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        //Fungsi delete barang
        deleteBarangInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Menampilkan alert dialog untuk verifikasi penghapusan barang
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setCanceledOnTouchOutside(false);//Supaya tidak hilang saat di click di luar
                alertDialog.setMessage("Apakah anda yakin ingin menghapus " + barang.getNama_barang() + " ?");
                alertDialog.setTitle("VERIFIKASI DELETE");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getContext(), barang.getNama_barang() + " Sudah di Hapus", Toast.LENGTH_SHORT).show();
                        //Menjalankan fungsi hapus barang
                        OpenHelper db = new OpenHelper(getContext());
                        db.deleteBarang(new Barang(barang.getId_barang(), null, 0, null, 0, null, null));
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
