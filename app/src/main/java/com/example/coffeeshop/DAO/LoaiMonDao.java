package com.example.coffeeshop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeeshop.DTO.LoaiMon;
import com.example.coffeeshop.Database.DbHelper;

import java.util.ArrayList;

public class LoaiMonDao {
    DbHelper dbHelper;
    public LoaiMonDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LoaiMon> getDsLoaiMon (){
        ArrayList<LoaiMon> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor =sqLiteDatabase. rawQuery("SELECT * FROM LOAIMON",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new LoaiMon(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());

        }
        return list;
    }
    public  boolean ThemLoaiMon(String tenloai){
SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai",tenloai);
long check = sqLiteDatabase.insert("LOAIMON",null,contentValues);
if (check==-1)
    return false;
    return true;
    }
    public int XoaLoaiMon(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MON WHERE maLoai = ?",new String[]{String.valueOf(id)});
        if (cursor.getCount()!=0){
            return  -1;
        }
        long check= sqLiteDatabase.delete("LOAIMON","maLoai=?",new String[]{String.valueOf(id)});
        if (check==-1)
            return 0;
        return 1;
    }
}
