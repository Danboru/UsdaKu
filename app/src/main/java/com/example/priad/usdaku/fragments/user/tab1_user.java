package com.example.priad.usdaku.fragments.user;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.priad.usdaku.provider.Transaksi;

import java.util.ArrayList;

public class tab1_user extends Fragment {

    private ArrayList list = new ArrayList();
    private int jumlahPembelian;

    public tab1_user() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_user, container, false);
        OpenHelper db = new OpenHelper(getContext());

        //Memasukkan data kedalam list
        list = db.getAllBarang();
        ListAdapter adapter = new AdapterBarang(getActivity(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_barangjual);
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
                showCustomDialog(position);
            }
        });
        return view;
    }

    //memunculkan dialog saat membeli barang
    private void showCustomDialog(int position) {

        final Barang barang = (Barang) list.get(position);
        //final Transaksi transaksi = (Transaksi) list.get(position);

        final Dialog dialog = new Dialog(getContext());
        //Mengeset judul dialog
        dialog.setTitle("Pembelian");

        //Mengeset layout
        dialog.setContentView(R.layout.popup_pemesanan);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Inisialisasi View
        Button cancelButton = (Button) dialog.findViewById(R.id.button_cancel);
        Button saveButton = (Button) dialog.findViewById(R.id.button_save);
        SeekBar seekBar = (SeekBar) dialog.findViewById(R.id.seekbar_jumlahpembelian);
        TextView namaBarang = (TextView) dialog.findViewById(R.id.namaBarangPopup);
        final View pembatas = dialog.findViewById(R.id.pembatas);
        final TextView totalpembayaran = (TextView) dialog.findViewById(R.id.tv_totalpembayaran);

        final TextView textView = (TextView) dialog.findViewById(R.id.txt_jumlahPembelian);

        //Set nama barang yang ada di popup
        namaBarang.setText(barang.getNama_barang());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));

                if(progress > 0){
                    pembatas.setVisibility(View.VISIBLE);
                    totalpembayaran.setVisibility(View.VISIBLE);
                    totalpembayaran.setText(String.valueOf(barang.getHarga_barang() * progress));
                }else {
                    pembatas.setVisibility(View.GONE);
                    totalpembayaran.setVisibility(View.GONE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Jumlah pembelian barang
                jumlahPembelian = seekBar.getProgress();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jumlahPembelian > 0){
                    //Menampilkan alert dialog untuk verifikasi pembelian barang
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setCanceledOnTouchOutside(false);//supaya tidak hilang saat di click di luar
                    alertDialog.setMessage("Apakah anda ingin melanjutkan pemesanan ?");
                    alertDialog.setTitle("VERIFIKASI PEMESANAN");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "Pesanan di Kirim", Toast.LENGTH_SHORT).show();
                            try {
                                OpenHelper db = new OpenHelper(getContext());
                                //Menangani apabila pembelian kurang dari 1
                                if(jumlahPembelian > 0){
                                    db.addTransaksi(new Barang(barang.getId_barang(), barang.getNama_barang(), barang.getHarga_barang(),
                                            null, jumlahPembelian, null, null));
                                }else {
                                    Toast.makeText(getContext(), "Minimal Beli 1 Barang", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e) {
                                Toast.makeText(getContext(), "Kesalahan saat mengirim pesanan", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "Pesanan Di Batalkan", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertDialog.show();

                    dialog.dismiss();
                //Desicion pembelinan apabila < 1    
                }else {
                    Toast.makeText(getContext(), "Minimal Beli 1 Barang", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Set Listener untuk button cancel
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //Menampilkan custom dialog
        dialog.show();
    }

}
