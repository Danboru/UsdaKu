package com.example.priad.usdaku.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.priad.usdaku.javafiles.Barang;
import com.example.priad.usdaku.javafiles.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by priad on 25/03/2017.
 */
public class DaftarUser extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "usdaku";

    // User table name
    private static final String TABLE_USER = "user";

    // Users Table Columns names
    private static final String KEY_ID = "id_user";
    private static final String KEY_NAMADEPAN = "namadepan_user";
    private static final String KEY_BELAKANG = "namabelakang_user";
    private static final String KEY_EMAIL = "email_user";
    private static final String KEY_NIM = "nim_user";
    private static final String KEY_PASSWORD = "password_user";

    public DaftarUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMADEPAN + " TEXT,"
                + KEY_BELAKANG + " TEXT," + KEY_EMAIL + " TEXT, " + KEY_NIM + " TEXT, "+ KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    // Adding new user
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMADEPAN, user.getNamadepan_user());
        values.put(KEY_BELAKANG, user.getNamabelakang_user());
        values.put(KEY_EMAIL, user.getEmail_user());
        values.put(KEY_NIM, user.getNim());
        values.put(KEY_PASSWORD, user.getPassword_user());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single user
    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID,
                        KEY_NAMADEPAN, KEY_BELAKANG, KEY_EMAIL,KEY_NIM, KEY_PASSWORD }, KEY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5));

        //Return user
        return user;
    }

    // Getting All user
    public List<User> getAllUser() {
        List<User> usersList = new ArrayList<User>();
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

                // Adding user to list
                usersList.add(user);

            } while (cursor.moveToNext());
        }
        // return user list
        return usersList;
    }

    // Updating single user
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMADEPAN, user.getNamadepan_user());
        values.put(KEY_BELAKANG, user.getNamabelakang_user());
        values.put(KEY_EMAIL, user.getEmail_user());
        values.put(KEY_NIM, user.getNim());
        values.put(KEY_PASSWORD, user.getPassword_user());

        // updating row
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId_user()) });
    }

    // Deleting single user
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getId_user()) });
        db.close();
    }

    // Getting users Count
    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
