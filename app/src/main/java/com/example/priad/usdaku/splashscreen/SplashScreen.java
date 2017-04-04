package com.example.priad.usdaku.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.priad.usdaku.MainActivity;
import com.example.priad.usdaku.R;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Transaksi;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends Activity {

    private static final String TAG = "SplashScreen";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Merubah kedalam keadaan fullscreen tanpa menubar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Get window screensize dan merubahnya kedalam keadaan fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //Membuat thead untuk menjalankan activity beberap second
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.d(TAG, "run: Berhasil Set Timer Sleep");
                    Thread.sleep(3000); //ini bagian set duration lamanya splashScreen
                }catch (Exception e){
                    Log.d(TAG, "run: Terjasi kesalahan saat Set Duration Sleep");
                    e.printStackTrace();
                }
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                //Menjalankan activity
                startActivity(i);
                finish();//Harus di fnish untuk mengilangkan activity di dalam process background
            }
        });

        Log.d(TAG, "onCreate: Mulai menjalankan Thread");
        thread.start();

        OpenHelper db = new OpenHelper(SplashScreen.this);

        //Delete Tester (WORKING)
//        db.deleteTransaksi(new Transaksi(1, null, 0,0));

        //Update Tester (WORKING)
//        db.updateTransaksi(new Transaksi(1, "nama baru", 9999, 999));

        //Select All Data (WORKING)
//        ArrayList arrayData = db.getAllTransaksi();
//        int dataTotal = 0;
//        for (Object data : arrayData
//             ) {
//
//            Toast.makeText(this, "Data = " + dataTotal  , Toast.LENGTH_SHORT).show();
//            dataTotal++;
//        }

        //Insert Tester (WORKING)
        db.addTransaksi(new Transaksi("Nama", 1000,  12));
        db.addTransaksi(new Transaksi("Nama Satu", 2000,  2));
        db.addTransaksi(new Transaksi("Nama Tiga", 6000,  20));
        db.addTransaksi(new Transaksi("Nama Empat", 8000,  62));

    }
}
