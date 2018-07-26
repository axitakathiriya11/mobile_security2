package com.example.admin.mobile_security;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by admin on 06-07-2018.
 */

public class dbHelper {

    public static final String DB_Name = "MobDB";
    public static final int DB_VERSION = 1;

    public static final String TABLE = "user";
    public static final String ID = "Id";
    public static final String Phone= "Phone";
    public static final String Pin = "Pin";
    public  static final String Create_date="create_date";


    public static final String Query="create table "+TABLE+"("+ID+" integer primary key autoincrement,"+Phone+" number not null,"+Pin+" number not null,"+Create_date+" datetime);";

     private SQLiteDatabase sqLiteDatabase;
        dbAdapter adapter;
        Context c;
        dbHelper(Context c)
        {
            this.c=c;
            adapter=new dbAdapter(c,DB_Name,null,DB_VERSION);
        }
        public dbHelper open()
        {
            sqLiteDatabase=adapter.getWritableDatabase();
            return this;
        }
        public void close()
        {
            sqLiteDatabase.close();
        }
        public  long insert(String phoneNo,String pin)
        {
            ContentValues contentValues=new ContentValues();
            contentValues.put(Phone,phoneNo);
            contentValues.put(Pin,pin);
            contentValues.put(Create_date,String.valueOf(new Date()));
            return sqLiteDatabase.insert(TABLE,null,contentValues);

        }

    public class dbAdapter extends SQLiteOpenHelper{

        public dbAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists"+TABLE);
        }
    }
}
