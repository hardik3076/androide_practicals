package com.example.p22;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
public static final String DB_NAME="RegistrationDB";
public static final String TABLE_NAME="Registration";

public DBHelper(Context context) {
    super(context, DB_NAME, null, 1);
}

@Override
public void onCreate(SQLiteDatabase db) {

    db.execSQL("CREATE TABLE Registration(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,phone TEXT,password TEXT)");
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS Registration");
    onCreate(db);
}

public boolean insertData(String name,String email,String phone,String password){

    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();

    cv.put("name",name);
    cv.put("email",email);
    cv.put("phone",phone);
    cv.put("password",password);

    long result=db.insert(TABLE_NAME,null,cv);

    if(result==-1)
        return false;
    else
        return true;
}

public Cursor getData(){

    SQLiteDatabase db=this.getReadableDatabase();
    Cursor cursor=db.rawQuery("SELECT * FROM Registration",null);
    return cursor;
}

public boolean updateData(String id,String name,String email,String phone,String password){

    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues cv=new ContentValues();

    cv.put("name",name);
    cv.put("email",email);
    cv.put("phone",phone);
    cv.put("password",password);

    db.update(TABLE_NAME,cv,"id=?",new String[]{id});
    return true;
}

public Integer deleteData(String id){

    SQLiteDatabase db=this.getWritableDatabase();
    return db.delete(TABLE_NAME,"id=?",new String[]{id});
}
}
