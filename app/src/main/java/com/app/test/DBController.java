package com.app.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBController extends SQLiteOpenHelper {
    private Context context;
    private static final String LOGCAT = null;
    private static final String DATABASE_NAME = "sentiment.db";

    private static final int DATABASE_VERSION = 1;

    private static final String tableName = "Sentiments";
    private static final String column_id = "id";

    private static final String column_text = "text";
    private static final String column_label = "label";

    public DBController(Context applicationContext){
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = applicationContext;
        Log.d(LOGCAT, "Created");
    }
    public void onCreate(SQLiteDatabase db){
        String query;
        query = "CREATE TABLE IF NOT EXISTS "+tableName+
                " (" +column_id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                " "+column_text+" TEXT, " +
                ""+column_label +" TEXT);";
        Log.e("Create Query", query);
        db.execSQL(query);
    }
    public void onUpgrade(SQLiteDatabase db, int version_old, int current_version){
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }
    public void addData(String text, String label){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_text, text);
        cv.put(column_label, label);
        long result = db.insert(tableName, null, cv);
        if(result == -1){
            Toast.makeText(context, "Kayıt başarısız oldu.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Kayıt başarıyla eklendi.", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor getAllDatas(){
        String query = "SELECT * FROM "+tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
