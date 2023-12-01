package com.example.coffeeshop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coffeeshop.DTO.NhanVien;
import com.example.coffeeshop.Database.DbHelper;

import java.util.ArrayList;

public class NhanVienDao {
    private DbHelper dbHeper;
    private SharedPreferences sharedPreferences;
    public NhanVienDao(Context context){
        dbHeper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("luuquyen",Context.MODE_PRIVATE);
    }

    public ArrayList<NhanVien> getListNhanVien(){
        SQLiteDatabase database = dbHeper.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN",null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new NhanVien(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getString(6)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public boolean checkDangNhap(String taikhoan , String matkhau){
        SQLiteDatabase database = dbHeper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN WHERE tenDangNhap =? AND matKhau =?", new String[]{taikhoan,matkhau});
        return cursor.getCount() >0;
    }
    public String Fogot(String email){
        SQLiteDatabase database = dbHeper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT matKhau FROM NHANVIEN WHERE email =? ", new String[]{email});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else {
            return "";
        }
    }
    public boolean getTk(String user){
        SQLiteDatabase database = dbHeper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN WHERE tenDangNhap =?", new String[]{user});
        return cursor.getCount() >0;
    }
    public String getEmail(String email){
        SQLiteDatabase database = dbHeper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT email FROM NHANVIEN WHERE tenDangNhap =?", new String[]{email});
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }return "";
    }
    public boolean themNhanVien(NhanVien nv){
        SQLiteDatabase database = dbHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen",nv.getHoTen());
        values.put("soDienThoai",nv.getSdt());
        values.put("email", nv.getEmail());
        values.put("tenDangNhap", nv.getTaiKhoan());
        values.put("matKhau", nv.getMatKhau());
        values.put("loaiTaiKhoan",nv.getLoaiTaiKhoan());
        long check = database.insert("NHANVIEN", null,values);
        return check != -1;
    }
    public boolean suaNhanVien(NhanVien nv){
        SQLiteDatabase database = dbHeper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen",nv.getHoTen());
        values.put("soDienThoai",nv.getSdt());
        values.put("email", nv.getEmail());
        values.put("loaiTaiKhoan",nv.getLoaiTaiKhoan());
        long check = database.update("NHANVIEN",values,"maNhanVien = ?",new String[]{String.valueOf(nv.getMaNV())});
        return check != -1;
    }
    public boolean xoaNhanVien(int manv){
        SQLiteDatabase database = dbHeper.getWritableDatabase();
        int check = database.delete("NHANVIEN","maNhanVien = ?", new String[]{String.valueOf(manv)});
        if (check <= 0) return false;
        return true;

    }
    public  String getChucVu(String user){
        SQLiteDatabase database = dbHeper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT loaiTaiKhoan FROM NHANVIEN WHERE tenDangNhap=?", new String[]{user});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        else {
            return "";
        }
    }
    public boolean CapNhatMatkhau(String tenDangNhap, String passold, String passnew) {
        SQLiteDatabase database = dbHeper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM NHANVIEN WHERE tenDangNhap=? AND matKhau=?", new String[]{tenDangNhap, passold});
        if (cursor.getCount() > 0) {
            Log.d("CapNhatMatkhau", "Người dùng được tìm thấy. Thực hiện cập nhật mật khẩu.");
            ContentValues contentValues = new ContentValues();
            contentValues.put("matKhau", passnew);
            long check = database.update("NHANVIEN", contentValues, "tenDangNhap =?", new String[]{tenDangNhap});
            if(check == -1)
                return false;
            return true;
        }
        return false;
    }
}
