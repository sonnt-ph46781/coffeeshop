package com.example.coffeeshop.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.NhanVienDao;
import com.example.coffeeshop.DTO.NhanVien;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapter.NhanVienAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NhanvienFragment extends Fragment {

    private RecyclerView recyclerNhanVien;

    private NhanVienDao nhanVienDao;

    private FloatingActionButton floatAddNhanVien;


    

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.nhanvien_fragment, container, false);

        recyclerNhanVien = view.findViewById(R.id.recyclerNhanVien);
        floatAddNhanVien = view.findViewById(R.id.floatButton);
        

        nhanVienDao = new NhanVienDao(getContext());

        loadNhanVien();

        floatAddNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThemNV();
            }
        });

        return view;
    }
    private void loadNhanVien(){
        ArrayList<NhanVien> list = nhanVienDao.getListNhanVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerNhanVien.setLayoutManager(linearLayoutManager);
        NhanVienAdapter adapter = new NhanVienAdapter(getContext(),list,nhanVienDao);
        recyclerNhanVien.setAdapter(adapter);
    }
    private void showThemNV(){
       AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
       LayoutInflater inflater = getLayoutInflater();
       View view = inflater.inflate(R.layout.themnhanvien_layout,null);
       builder.setView(view);
       AlertDialog dialog = builder.create();
        EditText edtHoTen, edtEmail, edtSoDienThoai, edtTaiKhoanDangKi, edtPassDangKi, edtChucVu;
        Button btnDangKiTaiKhoan, btnHuyDangKiTaiKhoan;

        edtHoTen = view.findViewById(R.id.edtHoTen);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtSoDienThoai = view.findViewById(R.id.edtSoDienThoai);
        edtTaiKhoanDangKi = view.findViewById(R.id.edtTaiKhoanRegister);
        edtPassDangKi = view.findViewById(R.id.edtPassRegister);
        edtChucVu = view.findViewById(R.id.edtChucVu);
        btnDangKiTaiKhoan = view.findViewById(R.id.btnDangKi);
        btnHuyDangKiTaiKhoan = view.findViewById(R.id.btnHuyDangKi);

        btnDangKiTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtHoTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSoDienThoai.getText().toString();
                String user = edtTaiKhoanDangKi.getText().toString();
                String pass = edtPassDangKi.getText().toString();
                String chucvu = edtChucVu.getText().toString();

                boolean check = nhanVienDao.getTk(user);


                if (name.isEmpty() && email.isEmpty() && sdt.isEmpty() && user.isEmpty() && pass.isEmpty() && chucvu.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (check) {
                    Toast.makeText(getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                }
                else {
                    NhanVien nv = new NhanVien(name,email,sdt,user,pass,chucvu);
                    boolean add = nhanVienDao.themNhanVien(nv);
                    if (add){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadNhanVien();
                        dialog.dismiss();
                    }
                    else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuyDangKiTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

}
