package com.example.priad.usdaku.fragments.user;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.priad.usdaku.adapter.AdapterJualan;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Barang;

import java.util.ArrayList;

public class tab4_user extends Fragment {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    ListView listView;

    //Keperluan popupInfoBarang
    private ArrayList list = new ArrayList();
    int posisiDataSekarang;

    EditText namaBarang, keterantanBarang, hargaBarang;
    Button jualBarang;

    public tab4_user() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab4_user, container, false);

        dataSet = new ArrayList<>();
//        initDataset();
//        rvView = (RecyclerView) view.findViewById(R.id.barangYangDiJual);
//        rvView.setHasFixedSize(false);
        jualBarang = (Button) view.findViewById(R.id.jualBarangnya);

        //Inisialisasi view
        namaBarang = (EditText) view.findViewById(R.id.namaBarang);
        keterantanBarang = (EditText) view.findViewById(R.id.keteranganBarang);
        hargaBarang = (EditText) view.findViewById(R.id.hargaBarang);
        listView = (ListView) view.findViewById(R.id.lv_satu);

        View root = inflater.inflate(R.layout.tab4_user, container, false);

        jualBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//        db.addBarang(new Barang("Barang Satu", 1000, "Keterangan", 12, "Ada", "Alamat Gambar"));

//        list.add(new Barang("Kue Serabi", 1000 ,"Serabi manis dan serabi asin", 10, "Ada", "gambar" ));
//        list.add(new Barang("Kue Serabi", 1000 ,"Serabi manis dan serabi asin", 10, "Ada", "gambar" ));
//        list.add(new Barang("Kue Serabi", 1000 ,"Serabi manis dan serabi asin", 10, "Ada", "gambar" ));
//        list.add(new Barang("Kue Serabi", 1000 ,"Serabi manis dan serabi asin", 10, "Ada", "gambar" ));
//        list.add(new Barang("Kue Lumpur", "Terbuat dari santan, kentang, tepung, dan telur", 2000 ));
//        list.add(new Barang("Klepon", "Makanan ini terbuat dari tepung beras ketan yang di bentuk bola - bola kecil dan diisi dengan gula merah", 5000 ));
//        list.add(new Barang("Onde-onde", "Terdapat bermacam-macam variasi, yang paling dikenal adalah onde-onde yang terbuat dari tepung ketan dan di dalamnya diisi pasta kacang hijau", 7000 ));
//        list.add(new Barang("Kerak Telor", "Bahan-bahan pembuatnya yaitu beras ketan putih, telur bebek, ebi (udang kering yang diasinkan) yang disangrai", 9000 ));
//        list.add(new Barang("Kue Mangkok", "Kue yang di bungkus dengan mangkok yang terbuat dari kertas", 12000 ));
//        list.add(new Barang("Kue Lapis", "Kue ini dibuat dari tepung beras, tepung kanji, santan, gula pasir, garam dan pewarna.", 10000 ));
//        list.add(new Barang("Getuk", "Getuk berbahan utama ketela pohon atau singkong. Getuk merupakan makanan yang mudah ditemukan di Jawa Tengah dan Jawa Timur", 1000 ));
//        list.add(new Barang("Lumpia", "Makanan ini berupa lembaran tipis dari tepung gandum yang dijadikan kulit lalu digunakan sebagai pembungkus isian yang dapat berupa rebung, telur, sayuran segar, daging, atau makanan laut", 7000 ));
//        list.add(new Barang("Kue Putu", "Kue putu merupakan kue yang berisi gula jawa dan parutan kelapa, tepung beras butiran kasar. Kue ini di kukus dengan diletakkan di dalam tabung bambu yang sedikit dipadatkan", 15000 ));
//        list.add(new Barang("Lemper", "Enak dan murah", 1000 ));
//        list.add(new Barang("Nagasari", "Nagasari terbuat dari tepung beras, tepung sagu, santan, dan gula yang diisi pisang. Kue ini biasanya dibalut dengan daun pisang lalu dikukus", 2000 ));
//        list.add(new Barang("Cenil", "Cenil berasal dari Yogyakarta. Jajanan ini merupakan makanan yang terbuat dari pati ketela pohon. Makanan ini bisa dibentuk bulat-bulat kecil atau kotak kemudian diberi warna sesuai selera sebelum direbus.", 5000 ));

                //Data inputan
                String namaBar = namaBarang.getText().toString().trim();
                String keterangan = keterantanBarang.getText().toString().trim();
                int harga = Integer.parseInt(hargaBarang.getText().toString().trim());

                //Clear inputan
                namaBarang.setText("");keterantanBarang.setText("");hargaBarang.setText("");

                    OpenHelper db = new OpenHelper(getContext());
                    Toast.makeText(getContext(), "Berhasil Input data", Toast.LENGTH_SHORT).show();
                    db.addBarang(new Barang(namaBar, harga, keterangan, 0, "Ada", "Alamat Gambar"));

            }
        });

//        /**
//         * Kita menggunakan LinearLayoutManager untuk list standar
//         * yang hanya berisi daftar item
//         * disusun dari atas ke bawah
//         */
//        layoutManager = new LinearLayoutManager(getContext());
//        rvView.setLayoutManager(layoutManager);
//
//        adapter = new AdapterJualan(dataSet);
//        rvView.setAdapter(adapter);

        OpenHelper db = new OpenHelper(getContext());
        ArrayList list = new ArrayList();

        list = db.getAllBarang();
        ListAdapter adapter = new AdapterBarang(getActivity(), list);

        ListView listView = (ListView) view.findViewById(R.id.lv_satu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Memanggil info dialog popup
                showInfoDialog(position);
            }
        });

        return view;
    }

//    private void initDataset() {
//        /**
//         * Tambahkan item ke dataset
//         * dalam prakteknya bisa bermacam2
//         * tidak hanya String seperti di kasus ini
//         */
//        dataSet.add("Barang yang Di Jual");
//        dataSet.add("Barang yang Di Jual");
//
//    }

    private void showInfoDialog(int position) {

        //Pembuatan object database
        OpenHelper db = new OpenHelper(getContext());
        list = db.getAllBarang();
        final Barang barang = (Barang) list.get(position);

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
        TextView info_namaBarang = (TextView) dialog.findViewById(R.id.infoNamaBarangPopup);
        TextView info_keteranganBarang = (TextView) dialog.findViewById(R.id.infoKeteranganBarangPopup);
        TextView info_hargaBarang = (TextView) dialog.findViewById(R.id.infoHargaBarangPopup);

        Button updateBarangInfo = (Button) dialog.findViewById(R.id.btn_updateBarangButtonInfo);
        Button deleteBarangInfo = (Button) dialog.findViewById(R.id.btn_deleteBarangButtonInfo);

        //Mutator view
        info_namaBarang.setText(barang.getNama_barang().toString().trim());
        info_keteranganBarang.setText(barang.getKeterangan_barang().toString().trim());
        info_hargaBarang.setText( "Rp. " + String.valueOf(barang.getHarga_barang()));

        //Listener Button
        updateBarangInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Barang di Update", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBarangInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Menampilkan alert dialog untuk verifikasi pembelian barang
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setCanceledOnTouchOutside(false);//supaya tidak hilang saat di click di luar
                alertDialog.setMessage("Apakah anda yakin ingin menghapus " + barang.getNama_barang() + " ?");
                alertDialog.setTitle("VERIFIKASI DELETE BARANG");
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
                alertDialog.show();
                dialog.dismiss();
            }
        });

        //Menampilkan custom dialog
        dialog.show();
    }

}