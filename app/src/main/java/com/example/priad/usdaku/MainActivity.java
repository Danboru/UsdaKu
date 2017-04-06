package com.example.priad.usdaku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class MainActivity extends Activity {

    Button button;
    EditText inputUsername, inputPass;
    TextView pendaftaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        final OpenHelper db =new OpenHelper(this);
        inisialisiView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Inisial variable
                String userName = inputUsername.getText().toString();
                String passwordUser = inputPass.getText().toString();
                String passTersimpan = db.getSinlgeEntry(userName);

                if (TextUtils.isEmpty(inputUsername.getText()) || TextUtils.isEmpty(inputPass.getText())){
                    Toast.makeText(MainActivity.this, "Isi semua Field", Toast.LENGTH_SHORT).show();
                } else {
                    //Penanganan inputan dari user
                    if(passwordUser.equalsIgnoreCase(passTersimpan)){

                        //Ketiak berhasil
                        Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, AktifitasUser.class);

                        //Kirim data ke activity bersangkutan menggunakan put extra yang nantinya akan di get dengan key yang di berikan
                        intent.putExtra("USERNAME", userName);

                        startActivity(intent);
                        finish();
                        db.close();

                    }  else if(inputUsername.getText().toString().equalsIgnoreCase("admin") && inputPass.getText().toString().equalsIgnoreCase("admin")){

                        Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, AktifitasAdmin.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Username Belum Terdaftar", Toast.LENGTH_SHORT).show();
                    }
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

    /***
     *
     * Bagian penginisilaisasian view yang akan di gunakan
     *
     **/
    public void inisialisiView(){
        button = (Button) findViewById(R.id.btn_masukAplikasi);
        inputUsername = (EditText) findViewById(R.id.edt_inputUsername);
        inputPass = (EditText) findViewById(R.id.edt_inputPassword);
        pendaftaran = (TextView) findViewById(R.id.pendaftaran);
    }
    }