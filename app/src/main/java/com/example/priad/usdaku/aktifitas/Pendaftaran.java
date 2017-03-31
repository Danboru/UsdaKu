package com.example.priad.usdaku.aktifitas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.priad.usdaku.R;
import com.example.priad.usdaku.databases.DaftarUser;
import com.example.priad.usdaku.javafiles.User;

public class Pendaftaran extends AppCompatActivity {

    Button daftarUser;
    EditText namaDepan, namaBelakag, emailUser, nimUser, passUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);
        
        daftarUser = (Button) findViewById(R.id.kirimPendaftaran);
        namaDepan = (EditText) findViewById(R.id.edt_namadepan);
        namaBelakag = (EditText) findViewById(R.id.edt_namabelakang);
        emailUser = (EditText) findViewById(R.id.edt_email);
        nimUser = (EditText) findViewById(R.id.edt_nim);
        passUser = (EditText) findViewById(R.id.edt_password);

        daftarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Data inputan user
                String namaDepannya = namaDepan.getText().toString();
                String namaBelakangnya = namaBelakag.getText().toString();
                String emailNya = emailUser.getText().toString();
                Integer nimNya = Integer.parseInt(nimUser.getText().toString());
                String passNya = passUser.getText().toString();

                //Database Class
                DaftarUser db = new DaftarUser(Pendaftaran.this);

                //Notifikasi untuk pengisian data berhasil
                Toast.makeText(Pendaftaran.this, "Data Sudah Di buat", Toast.LENGTH_SHORT).show();
                db.addUser(new User( namaDepannya, namaBelakangnya, emailNya, nimNya, passNya));
                
            }
        });
        
    }
}
