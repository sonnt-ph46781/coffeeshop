package com.example.coffeeshop.DTO;

public class LoaiMon {
    private int maloai;
    private String tenloai;


    public LoaiMon() {
    }

    public LoaiMon(int maloai,String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;

    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}


