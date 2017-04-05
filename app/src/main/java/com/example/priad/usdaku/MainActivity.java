package com.example.priad.usdaku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.priad.usdaku.aktifitas.AktifitasAdmin;
import com.example.priad.usdaku.aktifitas.AktifitasUser;
import com.example.priad.usdaku.aktifitas.Pendaftaran;
import com.example.priad.usdaku.databases.OpenHelper;
import com.example.priad.usdaku.provider.Barang;

public class MainActivity extends Activity {

    Button button;
    EditText satu, dua;
    TextView pendaftaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        inisialisiView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(satu.getText().toString().equalsIgnoreCase("user") && dua.getText().toString().equalsIgnoreCase("user")){
                        Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, AktifitasUser.class);
                        startActivity(intent);
                        finish();
                    }
                else if(satu.getText().toString().equalsIgnoreCase("admin") && dua.getText().toString().equalsIgnoreCase("admin")){

                        Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, AktifitasAdmin.class);
                        startActivity(intent);
                        finish();
                    }
                else {
                        Toast.makeText(MainActivity.this, "Kamu Ngapain", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        pendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(MainActivity.this, Pendaftaran.class);
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Kesalahan Saat Memanggil Activity Pendaftaran", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void inisialisiView(){
        button = (Button) findViewById(R.id.btn_satu);
        satu = (EditText) findViewById(R.id.edt_satu);
        dua = (EditText) findViewById(R.id.edt_dua);
        pendaftaran = (TextView) findViewById(R.id.pendaftaran);
    }
    }