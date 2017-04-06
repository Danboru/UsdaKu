package com.example.priad.usdaku.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.priad.usdaku.provider.Barang;
import com.example.priad.usdaku.provider.Laporan;
import com.example.priad.usdaku.provider.Transaksi;
import com.example.priad.usdaku.provider.User;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by priad on 25/03/2017.
 */
public class OpenHelper extends SQLiteOpenHelper {
    
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "usdaku";
    // User Taable Name
    private static final String TABLE_USER = "user";
    private static final String TABLE_BARANG = "barang";
    private static final String TABLE_TRANSAKSI = "transaksi";
    private static final String TABLE_LAPORAN = "laporan";

    //Kolom Table user
    private static final String KEY_USER_ID = "id_user";
    private static final String KEY_USER_NAMADEPAN = "namadepan_user";
    private static final String KEY_USER_NAMABELAKANG = "namabelakang_user";
    private static final String KEY_USER_EMAIL = "email_user";
    private static final String KEY_USER_NIM = "nim_user";
    private static final String KEY_USER_PASSWORD = "password_user";
    private static final String KEY_USER_IMAGE = "image_user";

    //Kolom Table Barang
    private static final String KEY_BARANG_ID = "id_barang";
    private static final String KEY_BARANG_NAMABARANG = "nama_barang";
    private static final String KEY_BARANG_HARGABARANG = "harga_barang";
    private static final String KEY_BARANG_KETERANGANBARANG = "keteranga_barang";
    private static final String KEY_BARANG_JUMLAHBARANG = "jumlah_barang";
    private static final String KEY_BARANG_STATUSBARANG = "status_barang";
    private static final String KEY_BARANG_URLIMAGE = "url_imagebarang";

    //Kolom Table Transaksi
    private static final String KEY_TRANSAKSI_ID = "id_transaksi";
    private static final String KEY_TRANSAKSI_NAMABARANG = "namabarang_transaksi";
    private static final String KEY_TRANSAKSI_HARGABARANG = "hargabarang_transaksi";
    private static final String KEY_TRANSAKSI_JUMLAHBARANG = "jumlahbarang_transaksi";

    //Kolom Table Laporan
    private static final String KEY_ID_LAPORAN = "id_pelapor";
    private static final String KEY_USER_PELAPOR = "user_pelapor";
    private static final String KEY_PESAN_TERLAMPIR = "pesan_terlampir";
    private static final String KEY_TANGGAL_PELAPORAN = "tanggal_pelaporan";


    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables (FIX)
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Table User (FIX)
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USER_NAMADEPAN + " TEXT,"
                + KEY_USER_NAMABELAKANG + " TEXT," + KEY_USER_EMAIL + " TEXT, "
                + KEY_USER_NIM + " INTEGER, "+ KEY_USER_PASSWORD + " TEXT,"  + KEY_USER_IMAGE + " TEXT" + ")";

        //Table Barang (FIX)
        String CREATE_BARANG_TABLE = "CREATE TABLE " + TABLE_BARANG + "("
                + KEY_BARANG_ID + " INTEGER PRIMARY KEY," + KEY_BARANG_NAMABARANG + " TEXT,"
                + KEY_BARANG_KETERANGANBARANG + " TEXT," + KEY_BARANG_HARGABARANG + " INTEGER, "
                + KEY_BARANG_JUMLAHBARANG + " INTEGER, " + KEY_BARANG_STATUSBARANG + " TEXT, "
                + KEY_BARANG_URLIMAGE + " TEXT " + ")";

        //Table Transaki (FIX)
        String CREATE_TRANSAKSI_TABLE = "CREATE TABLE " + TABLE_TRANSAKSI + "("
                + KEY_TRANSAKSI_ID + " INTEGER PRIMARY KEY,"+ KEY_TRANSAKSI_NAMABARANG + " TEXT,"
                + KEY_TRANSAKSI_HARGABARANG + " INTEGER, "
                + KEY_TRANSAKSI_JUMLAHBARANG + " INTEGER" + ")";

        //Table Laporan (FIX)
        String CREATE_TABLE_LAPORAN = "CREATE TABLE " + TABLE_LAPORAN + "(" + KEY_ID_LAPORAN + " INTEGER PRIMARY KEY," +
                KEY_USER_PELAPOR + " TEXT, " + KEY_PESAN_TERLAMPIR + " TEXT, " + KEY_TANGGAL_PELAPORAN + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BARANG_TABLE);
        db.execSQL(CREATE_TRANSAKSI_TABLE);
        db.execSQL(CREATE_TABLE_LAPORAN);
    }

    // Upgrading database (FIX)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARANG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAPORAN);

        // Create tables again
        onCreate(db);
    }

    // Adding new user (BUG)
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAMADEPAN, user.getNamadepan_user());
        values.put(KEY_USER_NAMABELAKANG, user.getNamabelakang_user());
        values.put(KEY_USER_EMAIL, user.getEmail_user());
        values.put(KEY_USER_NIM, user.getNim());
        values.put(KEY_USER_PASSWORD, user.getPassword_user());
        values.put(KEY_USER_IMAGE, user.getPassword_user());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    //Get Pass (BUG)
    public String getSinlgeEntry(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_USER, null, KEY_USER_NAMADEPAN + " = ? ", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD));
        cursor.close();
        return password ;
    }

    //Get Username (BUG)
    public String getUserName(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  " + TABLE_USER + " where "+KEY_USER_NAMADEPAN + " =? ", new String[]{userName});
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String user= cursor.getString(cursor.getColumnIndex(KEY_USER_NAMADEPAN));
        cursor.close();
        return user;
    }

    //Get Nim (BUG)
    public String getNim(String userName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  " + TABLE_USER + " where "+KEY_USER_NAMADEPAN + " =? ", new String[]{userName});
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String user= cursor.getString(cursor.getColumnIndex(KEY_USER_NIM));
        cursor.close();
        return user;
    }

    // Adding new barang (FIX)
    public void addBarang(Barang barang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BARANG_NAMABARANG, barang.getNama_barang());
        values.put(KEY_BARANG_HARGABARANG, barang.getHarga_barang());
        values.put(KEY_BARANG_KETERANGANBARANG, barang.getKeterangan_barang());
        values.put(KEY_BARANG_JUMLAHBARANG, barang.getJumlah_barang());
        values.put(KEY_BARANG_STATUSBARANG, barang.getStatus_barang());
        values.put(KEY_BARANG_URLIMAGE, barang.getUrl_gambarbarang());

        // Inserting Row
        db.insert(TABLE_BARANG, null, values);
        db.close(); // Closing database connection
    }

    // Adding new transaksi (FIX)
    public void addTransaksi(Barang transaksi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRANSAKSI_NAMABARANG, transaksi.getNama_barang());
        values.put(KEY_TRANSAKSI_HARGABARANG, transaksi.getHarga_barang());
        values.put(KEY_TRANSAKSI_JUMLAHBARANG, transaksi.getJumlah_barang());

        // Inserting Row
        db.insert(TABLE_TRANSAKSI, null, values);
        db.close(); // Closing database connection
    }

    // Adding new LAPORAN (FIX)
    public void addLaporan(Laporan laporan) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_LAPORAN, laporan.getId_laporan());
        values.put(KEY_USER_PELAPOR, laporan.getNama_pelapor());
        values.put(KEY_PESAN_TERLAMPIR, laporan.getPesan_terlampir());
        values.put(KEY_TANGGAL_PELAPORAN, laporan.getTanggal_pelaporan());

        // Inserting Row
        db.insert(TABLE_LAPORAN, null, values);
        db.close(); // Closing database connection
    }

    // Getting single user (BUG)
    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //ID - NAMADEPAN - NAMABELAKANG - EMAIL - NIM - PASSWORD - IMAGE
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_USER_ID,
                        KEY_USER_NAMADEPAN, KEY_USER_NAMABELAKANG,
                        KEY_USER_EMAIL,KEY_USER_NIM, KEY_USER_PASSWORD,
                        KEY_USER_IMAGE }, KEY_USER_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
                cursor.getString(6), cursor.getString(7));

        //Return user
        return user;
    }

    // Getting Single Barang (BUG)
    Barang getBarang(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        //ID - NAMADEPAN - NAMABELAKANG - EMAIL - NIM - PASSWORD - IMAGE
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_BARANG_ID,
                        KEY_BARANG_NAMABARANG, KEY_BARANG_HARGABARANG,
                        KEY_BARANG_KETERANGANBARANG,KEY_BARANG_JUMLAHBARANG, KEY_BARANG_STATUSBARANG,
                        KEY_BARANG_URLIMAGE }, KEY_BARANG_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Barang barang = new Barang(cursor.getString(0), Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),Integer.parseInt(cursor.getString(3)),
                cursor.getString(4), cursor.getString(5));

        //Return user
        return barang;
    }

    // Getting All user (BUG)
    public ArrayList<User> getAllUser() {
        ArrayList<User> usersList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId_user(Integer.parseInt(cursor.getString(0)));
                user.setNamadepan_user(cursor.getString(1));
                user.setNamabelakang_user(cursor.getString(2));
                user.setEmail_user(cursor.getString(3));
                user.setNim(Integer.parseInt(cursor.getString(4)));
                user.setPassword_user(cursor.getString(5));
                user.setImage_user(cursor.getString(6));

                // Adding user to list
                usersList.add(user);

            } while (cursor.moveToNext());
        }
        // return user list
        return usersList;
    }

    // Getting All Barang (FIX)
    public ArrayList getAllBarang() {
        ArrayList barangList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BARANG;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Barang barang = new Barang();
                barang.setId_barang(cursor.getInt(0));
                barang.setNama_barang(cursor.getString(cursor.getColumnIndex(KEY_BARANG_NAMABARANG)));
                barang.setHarga_barang(cursor.getInt(cursor.getColumnIndex(KEY_BARANG_HARGABARANG)));
                barang.setKeterangan_barang(cursor.getString(cursor.getColumnIndex(KEY_BARANG_KETERANGANBARANG)));
                barang.setJumlah_barang(cursor.getInt(cursor.getColumnIndex(KEY_BARANG_JUMLAHBARANG)));
                barang.setStatus_barang(cursor.getString(cursor.getColumnIndex(KEY_BARANG_STATUSBARANG)));
                barang.setUrl_gambarbarang(cursor.getString(cursor.getColumnIndex(KEY_BARANG_URLIMAGE)));

                // Adding user to list
                barangList.add(barang);

            } while (cursor.moveToNext());
        }
        // return barang list
        return barangList;
    }

    // Getting All Transaksi (FIX)
    public ArrayList<Transaksi> getAllTransaksi() {

        ArrayList transaksiList = new ArrayList();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TRANSAKSI;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transaksi transaksi = new Transaksi();
                transaksi.setId_transaksi(cursor.getInt(cursor.getColumnIndex(KEY_TRANSAKSI_ID)));
                transaksi.setNamabarang_transaksi(cursor.getString(cursor.getColumnIndex(KEY_TRANSAKSI_NAMABARANG)));
                transaksi.setHargabarang_transaksi(cursor.getInt(cursor.getColumnIndex(KEY_TRANSAKSI_HARGABARANG)));
                transaksi.setJumlahbarang_transaksi(cursor.getInt(cursor.getColumnIndex(KEY_TRANSAKSI_JUMLAHBARANG)));

                // Adding user to list
                transaksiList.add(transaksi);

            } while (cursor.moveToNext());
        }
        // return transaksi list
        return transaksiList;
    }

    // Getting All laporan (FIX)
    public ArrayList<Laporan> getAllLaporan() {

        ArrayList laporanList = new ArrayList();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_LAPORAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Laporan laporan = new Laporan();
                laporan.setId_laporan(cursor.getInt(cursor.getColumnIndex(KEY_ID_LAPORAN)));
                laporan.setNama_pelapor(cursor.getString(cursor.getColumnIndex(KEY_USER_PELAPOR)));
                laporan.setPesan_terlampir(cursor.getString(cursor.getColumnIndex(KEY_PESAN_TERLAMPIR)));
                laporan.setTanggal_pelaporan(cursor.getString(cursor.getColumnIndex(KEY_TANGGAL_PELAPORAN)));

                // Adding laporan to list
                laporanList.add(laporan);

            } while (cursor.moveToNext());
        }
        // return laporan list
        return laporanList;
    }

    // Updating Single User (BUG)
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //ID - NAMADEPAN - NAMABELAKANG - EMAIL - NIM - PASSWORD - IMAGE
        values.put(KEY_USER_NAMADEPAN, user.getNamadepan_user());
        values.put(KEY_USER_NAMABELAKANG, user.getNamabelakang_user());
        values.put(KEY_USER_EMAIL, user.getEmail_user());
        values.put(KEY_USER_NIM, user.getNim());
        values.put(KEY_USER_PASSWORD, user.getPassword_user());
        values.put(KEY_USER_IMAGE, user.getImage_user());

        // updating row
        return db.update(TABLE_USER, values, KEY_USER_ID + " = ?",
                new String[] { String.valueOf(user.getId_user()) });
    }

    // Updating Single Barang (BUG)
    public int updateBarang(Barang barang) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BARANG_NAMABARANG, barang.getNama_barang());
        values.put(KEY_BARANG_KETERANGANBARANG, barang.getKeterangan_barang());
        values.put(KEY_BARANG_HARGABARANG, barang.getHarga_barang());
        //values.put(KEY_BARANG_JUMLAHBARANG, barang.getJumlah_barang());
        //values.put(KEY_BARANG_STATUSBARANG, barang.getStatus_barang());
        //values.put(KEY_BARANG_URLIMAGE, barang.getUrl_gambarbarang());

        // updating row
        return db.update(TABLE_BARANG, values, KEY_BARANG_ID + " = ?",
                new String[] { String.valueOf(barang.getId_barang()) });
    }

    // Updating Single Transaksi (BUG)
    public int updateTransaksi(Transaksi transaksi) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TRANSAKSI_NAMABARANG, transaksi.getNamabarang_transaksi());
        values.put(KEY_TRANSAKSI_HARGABARANG, transaksi.getHargabarang_transaksi());
        values.put(KEY_TRANSAKSI_JUMLAHBARANG, transaksi.getJumlahbarang_transaksi());

        // updating row
        return db.update(TABLE_TRANSAKSI, values, KEY_TRANSAKSI_ID + " = ?",
                new String[] { String.valueOf(transaksi.getId_transaksi()) });
    }

    // Deleting single barang (FIX)
    public void deleteBarang(Barang barang) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BARANG, KEY_BARANG_ID + " = ?",
                new String[] { String.valueOf(barang.getId_barang()) });
        db.close();
    }

    // Deleting single user (BUG)
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_USER_ID + " = ?",
                new String[] { String.valueOf(user.getId_user()) });
        db.close();
    }

    // Deleting single transaksi (BUG)
    public void deleteTransaksi(Transaksi transaksi) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSAKSI, KEY_TRANSAKSI_ID + " = ?",
                new String[] { String.valueOf(transaksi.getId_transaksi()) });
        db.close();
    }

    // Deleting single laporan (BUG)
    public void deleteLaporan(Laporan laporan) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LAPORAN, KEY_ID_LAPORAN + " = ?",
                    new String[] { String.valueOf(laporan.getId_laporan()) });
        db.close();
    }

    // Getting users Count (BUG)
    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    // Getting barang Count (FIX)
    public int getBarangCount() {
        String countQuery = "SELECT * FROM " + TABLE_BARANG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    // Getting transaksi Count (FIX)
    public int getTransaksiCount() {
        String countQuery = "SELECT * FROM " + TABLE_TRANSAKSI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    // Getting Laporan Count (FIX)
    public int getLaporanCount() {
        String countQuery = "SELECT * FROM " + TABLE_LAPORAN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

}
