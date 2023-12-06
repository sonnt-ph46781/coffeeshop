package com.example.coffeeshop.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeeshop.DTO.Ban;
import com.example.coffeeshop.Database.DbHelper;

import java.util.ArrayList;

public class BanDao {
    DbHelper dbHelper;
    public BanDao(Context context){
        dbHelper= new DbHelper(context);
    }
      public ArrayList<Ban> getDSban(){
          ArrayList<Ban> list = new ArrayList<>();
           SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
             Cursor cursor = sqLiteDatabase.rawQuery("SELECT b.maBan,b.maNhanVien,nv.hoTen,b.maMon,m.tenMon,b.tinhTrang,b.tongTien FROM BAN b ,NHANVIEN nv,MON m WHERE b.maNhanVien = nv.maNhanVien AND b.maMon = m.maMon",null);
            if (cursor.getCount()!=0){
            cursor.moveToFirst();
               do {
           list.add(new Ban(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6)));
           }while (cursor.moveToNext());
            }
          return  list;
}
}
