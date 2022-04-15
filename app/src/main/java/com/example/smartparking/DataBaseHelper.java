package com.example.smartparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String Table_NAME = "users";
    private static final String Col1 = "ID";
    private static final String Col2 = "first_name";
    private static final String Col3 = "last_name";
    private static final String Col4 = "password";
    private static final String Col5 = "joined_on";
    private static final String Col6 = "age";
    private static final String Col7 = "addr";

    public DataBaseHelper(Context context){
        super(context, Table_NAME, null,1);
    }
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + Table_NAME + " ("+Col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ Col2 + " TEXT, "+ Col3 + " TEXT, " + Col4 + " TEXT, " + Col5 + " TEXT, " + Col6 + " INT, "+ Col7 + " TEXT)";
        db.execSQL(createTable);
    }
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS "+ Table_NAME);
        onCreate(db);
    }
    public Boolean addData(String fname, String lname, String pass, String jDate, int age, String addr){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, fname);
        contentValues.put(Col3, lname);
        contentValues.put(Col4, pass);
        contentValues.put(Col5, jDate);
        contentValues.put(Col6, age);
        contentValues.put(Col7, addr);


        Log.d(TAG, "addData: Adding "+fname+ "to" + Table_NAME);
        Log.d(TAG, "addData: Adding "+lname+ "to" + Table_NAME);
        Log.d(TAG, "addData: Adding "+pass+ "to" + Table_NAME);
        Log.d(TAG, "addData: Adding "+jDate+ "to" + Table_NAME);
        Log.d(TAG, "addData: Adding "+age+ "to" + Table_NAME);
        Log.d(TAG, "addData: Adding "+addr+ "to" + Table_NAME);

        long check = db.insert(Table_NAME,null,contentValues);
        //if data is not inserted correctly
        return check != -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getSpecificData(String cond){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table_NAME + " WHERE "+cond;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
