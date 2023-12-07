package com.example.coffeeshop.DTO;

public class Ban {
    private int maban;
    private int maNhanVien;
    private String tenThanhVien;
    private int maMon;
    private String tenMon;
    private int tinhTrang;
    private int tongTien;

    public Ban(int maNhanVien, int maMon, int i, int tien) {
    }

    public Ban(int maban, int maNhanVien, String tenThanhVien, int maMon, String tenMon, int tinhTrang, int tongTien) {
        this.maban = maban;
        this.maNhanVien = maNhanVien;
        this.tenThanhVien = tenThanhVien;
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.tinhTrang = tinhTrang;
        this.tongTien = tongTien;
    }

    public int getMaban() {
        return maban;
    }

    public void setMaban(int maban) {
        this.maban = maban;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
