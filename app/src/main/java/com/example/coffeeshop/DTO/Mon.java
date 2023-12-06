package com.example.coffeeshop.DTO;

public class Mon {
    private int mamon;
    private String tenmon;
    private int giatien;
    private int maloai;
    private String tenloai;

    public Mon() {
    }

    public Mon(int mamon, String tenmon, int giatien, int maloai, String tenloai) {
        this.mamon = mamon;
        this.tenmon = tenmon;
        this.giatien = giatien;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}