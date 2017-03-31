package com.example.priad.usdaku.fragments.user;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Layout;
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
import com.example.priad.usdaku.javafiles.Barang;

import java.util.ArrayList;

public class tab1_user extends Fragment {

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

        ArrayList list = new ArrayList();
        list.add(new Barang("Kue Serabi", "Serabi manis dan serabi asin", 1000 ));
        list.add(new Barang("Kue Lumpur", "Terbuat dari santan, kentang, tepung, dan telur", 2000 ));
        list.add(new Barang("Klepon", "Makanan ini terbuat dari tepung beras ketan yang di bentuk bola - bola kecil dan diisi dengan gula merah", 5000 ));
        list.add(new Barang("Onde-onde", "Terdapat bermacam-macam variasi, yang paling dikenal adalah onde-onde yang terbuat dari tepung ketan dan di dalamnya diisi pasta kacang hijau", 7000 ));
        list.add(new Barang("Kerak Telor", "Bahan-bahan pembuatnya yaitu beras ketan putih, telur bebek, ebi (udang kering yang diasinkan) yang disangrai", 9000 ));
        list.add(new Barang("Kue Mangkok", "Kue yang di bungkus dengan mangkok yang terbuat dari kertas", 12000 ));
        list.add(new Barang("Kue Lapis", "Kue ini dibuat dari tepung beras, tepung kanji, santan, gula pasir, garam dan pewarna.", 10000 ));
        list.add(new Barang("Getuk", "Getuk berbahan utama ketela pohon atau singkong. Getuk merupakan makanan yang mudah ditemukan di Jawa Tengah dan Jawa Timur", 1000 ));
        list.add(new Barang("Lumpia", "Makanan ini berupa lembaran tipis dari tepung gandum yang dijadikan kulit lalu digunakan sebagai pembungkus isian yang dapat berupa rebung, telur, sayuran segar, daging, atau makanan laut", 7000 ));
        list.add(new Barang("Kue Putu", "Kue putu merupakan kue yang berisi gula jawa dan parutan kelapa, tepung beras butiran kasar. Kue ini di kukus dengan diletakkan di dalam tabung bambu yang sedikit dipadatkan", 15000 ));
        list.add(new Barang("Lemper", "Enak dan murah", 1000 ));
        list.add(new Barang("Nagasari", "Nagasari terbuat dari tepung beras, tepung sagu, santan, dan gula yang diisi pisang. Kue ini biasanya dibalut dengan daun pisang lalu dikukus", 2000 ));
        list.add(new Barang("Cenil", "Cenil berasal dari Yogyakarta. Jajanan ini merupakan makanan yang terbuat dari pati ketela pohon. Makanan ini bisa dibentuk bulat-bulat kecil atau kotak kemudian diberi warna sesuai selera sebelum direbus.", 5000 ));

        ListAdapter adapter = new AdapterBarang(getActivity(), list);

        //menggunakan findViewBYId di Fragment
        ListView listView = (ListView) view.findViewById(R.id.lv_satu);
        listView.setAdapter(adapter);

        final SwipeRefreshLayout swLayout = (SwipeRefreshLayout) view.findViewById(R.id.swlayout);

        // Mengeset properti warna yang berputar pada SwipeRefreshLayout
        swLayout.setColorSchemeResources(R.color.atas,R.color.atas);

        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Handler untuk menjalankan jeda selama 5 detik
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Berhenti berputar/refreshing
                        swLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "Berhasil Load Data", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showCustomDialog();
            }
        });
        return view;
    }

    private void showCustomDialog() {

        final Dialog dialog = new Dialog(getContext());
        //Mengeset judul dialog
        dialog.setTitle("Pembelian");

        //Mengeset layout
        dialog.setContentView(R.layout.activity_pemesanan);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button cancelButton = (Button) dialog.findViewById(R.id.button_cancel);
        Button saveButton = (Button) dialog.findViewById(R.id.button_save);
        SeekBar seekBar = (SeekBar) dialog.findViewById(R.id.seekbar_jumlahpembelian);
        final TextView textView = (TextView) dialog.findViewById(R.id.txt_jumlahPembelian);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setCanceledOnTouchOutside(false);//supaya tidak hilang saat di click di luar
                alertDialog.setMessage("Apakah anda ingin melanjutkan pemesanan ?");

                alertDialog.setTitle("VERIFIKASI PEMESANAN");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Pesanan Anda Terkirim", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Pemesanan Di Batalkan", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();

                dialog.dismiss();
            }
        });

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
