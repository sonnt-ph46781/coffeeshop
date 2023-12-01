package com.example.coffeeshop.fragment;

import static android.content.Context.MODE_PRIVATE;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeeshop.DAO.NhanVienDao;
import com.example.coffeeshop.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DoiMatKhauFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doimatkhau_fragment,container,false);
        TextInputLayout txtpassold = view.findViewById(R.id.txtPassold);
        TextInputLayout txtpassnew = view.findViewById(R.id.txtPassnew);
        TextInputLayout txtpassnhaplai = view.findViewById(R.id.txtPassnhaplai);
        TextInputEditText edtpassold = view.findViewById(R.id.edtPassold);
        TextInputEditText edtpassnew = view.findViewById(R.id.edtPassnew);
        TextInputEditText edtpassnhaplai = view.findViewById(R.id.edtPassnhaplai);
        Button btndoimatkhau = view.findViewById(R.id.btndoimatkhau);
        btndoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passold = edtpassold.getText().toString().trim();
                String passnew = edtpassnew.getText().toString().trim();
                String nhaplaipassnew = edtpassnhaplai.getText().toString().trim();
                if(passnew.trim().equals(nhaplaipassnew.trim())) {
                    if (passold.trim().isEmpty() || passnew.trim().isEmpty() || nhaplaipassnew.trim() .isEmpty()) {
                        Toast.makeText(requireContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("luuquyen", MODE_PRIVATE);
                    String tenDangNhap = sharedPreferences.getString("tenDangNhap", "");
                    NhanVienDao nhanVienDao = new NhanVienDao(requireContext());
                    boolean check = nhanVienDao.CapNhatMatkhau(tenDangNhap, passold, passnew);
                    if (check) {
                        Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Cập nhật không thành công ,vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    }
