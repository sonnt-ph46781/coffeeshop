package com.example.coffeeshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.coffeeshop.R;

public class TrangChuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trangchu_fragment,container,false);
        Button btntrangchu= view.findViewById(R.id.btntrangchu);
        Button btnthanhvien = view.findViewById(R.id.btnthanhvien);
        Button btnban = view.findViewById(R.id.btnban);
        Button btnthucdon = view.findViewById(R.id.btnthucdon);
        Button btnbanchaynhat = view.findViewById(R.id.btnbanchaynhat);
        Button btndoanhthu = view.findViewById(R.id.btnDoanhthu);
        // sử dụng FragmentManager để quản lí fragment
        final FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        btntrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadfragment(new TrangChuFragment());
            }
        });
        btnthanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadfragment(new NhanvienFragment());
            }
        });
        btnban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadfragment(new BanFragment());
            }
        });
        btnthucdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadfragment(new ThucDonFragment());
            }
        });
        btnbanchaynhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadfragment(new MonChayNhatFragment());
            }
        });
        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
loadfragment(new ThongKeFragment());
            }
        });
        return view;
    }
    // hàm để tải frgment
    private void  loadfragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=requireActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.addToBackStack(null);//cho phép quay lại fregnment trươc đó
        fragmentTransaction.commit();

    }
}
