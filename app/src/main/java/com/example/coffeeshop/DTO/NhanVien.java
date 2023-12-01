package com.example.coffeeshop.DTO;

public class NhanVien {
    int maNV;
    String hoTen, sdt, email, tenDangNhap, matKhau, loaiTaiKhoan;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTen, String sdt, String email, String taiKhoan, String matKhau, String loaiTaiKhoan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.tenDangNhap = taiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public NhanVien(String hoTen, String sdt, String email, String taiKhoan, String matKhau, String loaiTaiKhoan) {
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.tenDangNhap = taiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public NhanVien(int maNV, String hoTen, String sdt, String email, String loaiTaiKhoan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return tenDangNhap;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.tenDangNhap = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
