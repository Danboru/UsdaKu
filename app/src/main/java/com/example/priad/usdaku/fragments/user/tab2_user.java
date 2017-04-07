package com.example.priad.usdaku.fragments.user;

import android.app.Dialog;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.priad.usdaku.adapter.AdapterTransaksi;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Transaksi;

import java.util.ArrayList;

public class tab2_user extends Fragment {

    //Varibale yang di gunakan untuk menampung data yang ada di dalam database
    private ArrayList list = new ArrayList();
    private int jumlahPembelianUpdate; //Jumlah pembelian transaksi

    //Konstruktor
    public tab2_user() {
        //Konstruktor kosong di perlukan
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_user, container, false);

        //Memangil helper database
        OpenHelper db = new OpenHelper(getContext());

        //Memasukkan data kedalam list
        list = db.getAllTransaksi();
        ListAdapter adapter = new AdapterTransaksi(getActivity(), list);

        //Menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_transaksi);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        final SwipeRefreshLayout swLayout = (SwipeRefreshLayout) view.findViewById(R.id.swlayoutpesanan);

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
                        list = db.getAllTransaksi();
                        Toast.makeText(getActivity(), "Uptodate", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Menambahkan option ke kontext menu
        menu.add(Menu.NONE, R.id.update_transaksi, Menu.NONE, "Update Pesanan");
        menu.add(Menu.NONE, R.id.delete_transaksi, Menu.NONE, "Delete Pesanan");
        menu.add(Menu.NONE, R.id.di_terima, Menu.NONE, "Pesanan Sudah Diterima");
        menu.add(Menu.NONE, R.id.laporkan_transaksi, Menu.NONE, "Laporkan Kesalahan");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        OpenHelper db = new OpenHelper(getContext());

        //Mengambil posisi data list yang di gunakan oleh contextmenu
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()) {
            case R.id.update_transaksi:

                //Memnggil fungsi popup
                shoUpdateJumlahPesanan(index);

                return true;
            case R.id.delete_transaksi: //FIX (Tapi masih ada sedikit BUG)

                Toast.makeText(getContext(), "Sudah Di Hapus", Toast.LENGTH_SHORT).show();
                db.deleteTransaksi(new Transaksi(index, null, 0,0));
                return true;

            case R.id.di_terima: //BUG

                Toast.makeText(getContext(), "Sudah Di Terima", Toast.LENGTH_SHORT).show();
                db.deleteTransaksi(new Transaksi(index, null, 0,0));
                return true;

            case R.id.laporkan_transaksi: //BUG

                Toast.makeText(getContext(), "Sudah Di Laporkan", Toast.LENGTH_SHORT).show();
                db.deleteTransaksi(new Transaksi(index, null, 0,0));
                return true;
        }
        return super.onContextItemSelected(item);
    }

        //Memunculkan dialog saat update transaksi
        private void shoUpdateJumlahPesanan(final int posisi) {

            final Dialog dialog = new Dialog(getContext());
            //Mengeset judul dialog
            dialog.setTitle("Update Pembelian");


            final Transaksi transaksi = (Transaksi) list.get(posisi);

            //Mengeset layout
            dialog.setContentView(R.layout.popup_updatejumlahpesanan);

            //Membuat agar dialog tidak hilang saat di click di area luar dialog
            dialog.setCanceledOnTouchOutside(false);

            //Membuat dialog agar berukuran responsive
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

            //Inisialisasi View
            Button cancelButton = (Button) dialog.findViewById(R.id.btn_cancel_update);
            Button saveButton = (Button) dialog.findViewById(R.id.btn_save_update);
            SeekBar seekBar = (SeekBar) dialog.findViewById(R.id.seekbar_jumlahpembeleian_update);
            final TextView jumlahBeliUpdate = (TextView) dialog.findViewById(R.id.txt_jumlahpesanan_update);
            TextView namabarangPembelian = (TextView) dialog.findViewById(R.id.txt_namapesanan_update);

            //Set nama barang yang ada di popup
            namabarangPembelian.setText(transaksi.getNamabarang_transaksi());

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    jumlahBeliUpdate.setText(String.valueOf(progress));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    //Jumlah pembelian transaksi update
                    jumlahPembelianUpdate = seekBar.getProgress();
                }
            });

            //membatalkan update
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            //Menyimpan update
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    OpenHelper db = new OpenHelper(getContext());
                    db.updateTransaksi(new Transaksi(posisi, jumlahPembelianUpdate));
                    db.close();
                    dialog.dismiss();
                }
            });

            //Menampilkan custom dialog
            dialog.show();
        }
}
