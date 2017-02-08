package com.example.priad.usdaku.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.priad.usdaku.MainActivity;
import com.example.priad.usdaku.R;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    Log.i("SplashScreen", "Berhasil Set Timer Sleep");
                    Thread.sleep(3000); //ini bagian set duration lamanya splashScreen
                }catch (Exception e){
                    Log.i("SplashScreen", "Terjasi kesalahan saat Set Duration Sleep");
                    e.printStackTrace();
                }
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        Log.d("SplashScreen", "Mulai menjalankan Thread");
        thread.start();

    }
}
