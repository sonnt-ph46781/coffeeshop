package com.example.coffeeshop.DTO;

public class Ban {
    private int maban;
    private int maNhanVien;
    private int maMon;
    private int tinhTrang;
    private int tongTien;
    private String tenThanhVien;
    private String tenMon;

    public Ban() {
    }

    public Ban(int maban, int maNhanVien, int maMon, int tinhTrang, int tongTien, String tenThanhVien, String tenMon) {
        this.maban = maban;
        this.maNhanVien = maNhanVien;
        this.maMon = maMon;
        this.tinhTrang = tinhTrang;
        this.tongTien = tongTien;
        this.tenThanhVien = tenThanhVien;
        this.tenMon = tenMon;
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

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
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

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
}
