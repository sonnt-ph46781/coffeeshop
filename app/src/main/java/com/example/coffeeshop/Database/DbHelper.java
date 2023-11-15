package com.example.coffeeshop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "QuanLyCoffee",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbonhavien = "CREATE TABLE NHANVIEN(maNhanVien INTEGER PRIMARY KEY AUTOINCREMENT, hoTen TEXT, soDienThoai TEXT, email TEXT, tenDangNhap TEXT, matKhau TEXT, loaiTaiKhoan TEXT)";
        db.execSQL(dbonhavien);

        String dataNhanVien = "INSERT INTO NHANVIEN VALUES(1,'Trọng Đức','0373783569','duc43059@gmail.com','admin','123456','quan ly'),(2,'Minh Khoa','0382876909','vkhoa30102004@gmail.com','nhanvien','123456','nhan vien')";
        db.execSQL(dataNhanVien);

        String dboloaimon = "CREATE TABLE LOAIMON(maLoai INTEGER PRIMARY KEY AUTOINCREMENT, tenLoai TEXT)";
        db.execSQL(dboloaimon);

        String dbomon = "CREATE TABLE MON(maMon INTEGER PRIMARY KEY AUTOINCREMENT, tenMon TEXT, giaTien INTEGER, tinhTrang TEXT, maLoai INTEGER REFERENCES LOAIMON(maLoai))";
        db.execSQL(dbomon);

        String dboban = "CREATE TABLE BAN(maBan INTEGER PRIMARY KEY AUTOINCREMENT, tenBan TEXT, tinhTrang TEXT, maNguoiDung INTEGER REFERENCES NGUOIDUNG(maNguoiDung))";
        db.execSQL(dboban);

        String dbohoadon = "CREATE TABLE DONDAT(maDonDat INTEGER PRIMARY KEY AUTOINCREMENT, maNguoiDung INTEGER REFERENCES NGUOIDUNG(maNguoiDung), ngayDat TEXT, tinhTrang TEXT, tongTien INTEGER, maBan INTEGER)";
        db.execSQL(dbohoadon);

        String dbocthd = "CREATE TABLE CTHD(maDonDat INTEGER PRIMARY KEY AUTOINCREMENT, maMon INTEGER REFERENCES MON(maMon), soLuong INTEGER)";
        db.execSQL(dbocthd);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion){
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAIMON");
            db.execSQL("DROP TABLE IF EXISTS BAN");
            db.execSQL("DROP TABLE IF EXISTS DONDAT");
            db.execSQL("DROP TABLE IF EXISTS CTDD");
            db.execSQL("DROP TABLE IF EXISTS MON");
            onCreate(db);
        }
    }
}