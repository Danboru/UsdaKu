package com.example.priad.usdaku.utils;

import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by priad on 07/04/2017.
 */
public class GetTimeNow {

    Calendar calendar = Calendar.getInstance();
    String tanggal, hari, bulan, tahun;

    int minutes = calendar.get(Calendar.MINUTE);//menit
    int seconds = calendar.get(Calendar.SECOND);//detik
    int date = calendar.get(Calendar.DAY_OF_MONTH);//tanggal
    int day = calendar.get(Calendar.DAY_OF_WEEK);//hari
    int month = calendar.get(Calendar.MONTH);//bulan
    int year = calendar.get(Calendar.YEAR);//tahun

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getTanggal(){

        tanggal = String.valueOf(date);
        return tanggal;
    }

    public String getHari(){

        switch(day) {

            case Calendar.SUNDAY :
                hari = "Minggu";
                break;

            case Calendar.MONDAY :
                hari = "Senin";
                break;

            case Calendar.TUESDAY :
                hari = "Selasa";
                break;

            case Calendar.WEDNESDAY :
                hari = "Rabu";
                break;

            case Calendar.THURSDAY :
                hari = "Kamis";
                break;

            case Calendar.FRIDAY :
                hari = "Jumat";
                break;

            case Calendar.SATURDAY :
                hari = "Sabtu";
                break;
        }
        return hari;
    }

    public String getBulan(){

        switch (month){

            case Calendar.JANUARY :
                bulan = "Januari";
                break;

            case Calendar.FEBRUARY :
                bulan = "Februari";
                break;

            case Calendar.MARCH :
                bulan = "Maret";
                break;

            case Calendar.APRIL :
                bulan = "April";
                break;

            case Calendar.MAY :
                bulan = "Mei";
                break;

            case Calendar.JUNE :
                bulan = "Juni";
                break;

            case Calendar.JULY :
                bulan = "Juli";
                break;

            case Calendar.AUGUST :
                bulan = "Agustus";
                break;

            case Calendar.SEPTEMBER :
                bulan = "September";
                break;

            case Calendar.OCTOBER :
                bulan = "Oktober";
                break;

            case Calendar.NOVEMBER :
                bulan = "November";
                break;

            case Calendar.DECEMBER :
                bulan = "Desember";
                break;
        }
    return bulan;
    }

    public String getTahun(){
        tahun = String.valueOf(year);
        return tahun;
    }

    public String getCurrentDate() {
        Date current = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = frmt.format(current);
        return dateString;
    }

}
