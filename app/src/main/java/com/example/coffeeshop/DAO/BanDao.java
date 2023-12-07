package com.example.coffeeshop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
             Cursor cursor = sqLiteDatabase.rawQuery("SELECT b.maBan, b.maNhanVien, nv.hoTen, b.maMon, m.tenMon, b.tinhTrang, b.tongTien\n" +
                     "FROM BAN b, NHANVIEN nv, MON m\n" +
                     "WHERE b.maNhanVien = nv.maNhanVien AND b.maMon = m.maMon\n",null);
            if (cursor.getCount()!=0){
                Log.d("BanDao", "Số hàng được trả về: " + cursor.getCount());
                cursor.moveToFirst();
               do {
                   list.add(new Ban(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6)));
               }while (cursor.moveToNext());
            }
          return  list;
}
        public  boolean thayDoiTrangThai(int maBan){
         SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("tinhTrang",1);
            long check = sqLiteDatabase.update("BAN",contentValues,"maBan = ?",new String[]{String.valueOf(maBan)});
            if (check == -1){
                return false;
            }
            return true;
}
           public boolean themBan(Ban ban){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNhanVien", ban.getMaNhanVien());
        contentValues.put("maMon",ban.getMaMon());
        contentValues.put("tinhTrang",ban.getTinhTrang());
        contentValues.put("tongTien",ban.getTongTien());
        long check = sqLiteDatabase.insert("BAN",null,contentValues);
        if (check==-1){
            return false;
        }
        return true;
           }
}
