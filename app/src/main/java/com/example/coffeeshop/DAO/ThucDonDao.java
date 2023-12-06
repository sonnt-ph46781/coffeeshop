package com.example.coffeeshop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeeshop.DTO.Mon;
import com.example.coffeeshop.Database.DbHelper;

import java.util.ArrayList;

public class ThucDonDao {
    DbHelper dbHelper;
    public ThucDonDao(Context context){
        dbHelper=new DbHelper(context);
    }
    public ArrayList<Mon> getDSMon(){
        ArrayList<Mon> list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT mn.maMon,mn.tenMon,mn.giaTien,mn.maLoai,li.tenLoai FROM MON mn ,LOAIMON li WHERE mn.maLoai = li.maLoai",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new Mon(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public int XoaMon(int maMon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM BAN WHERE maMon = ?",new String[]{String.valueOf(maMon)});
        if (cursor.getCount()!=0){
            return -1;
        }
        long check = sqLiteDatabase.delete("MON","maMon=?",new String[]{String.valueOf(maMon)});
        if (check==-1)
            return 0;
        return 1;
    }
    public boolean themMonMoi(String tenMon, int giaTien , int maLoai){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenMon",tenMon);
        contentValues.put("giaTien",giaTien);
        contentValues.put("maLoai",maLoai);
        long check = sqLiteDatabase.insert("MON",null,contentValues);
        if (check == -1)
            return false;
        return  true;
    }
    public boolean capNhatMon(int maMon, String tenMon,int giaTien,int maLoai){
        SQLiteDatabase sqLiteDatabase =dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maMon",maMon);
        contentValues.put("giaTien",giaTien);
        contentValues.put("maLoai",maLoai);
        long check= sqLiteDatabase.update("MON",contentValues,"maMon=?",new String[]{String.valueOf(maMon)});
        if (check==-1)
            return false;
        return true;
    }
}
