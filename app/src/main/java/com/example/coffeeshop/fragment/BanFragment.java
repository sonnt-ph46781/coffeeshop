package com.example.coffeeshop.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.BanDao;
import com.example.coffeeshop.DAO.NhanVienDao;
import com.example.coffeeshop.DAO.ThucDonDao;
import com.example.coffeeshop.DTO.Ban;
import com.example.coffeeshop.DTO.Mon;
import com.example.coffeeshop.DTO.NhanVien;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapter.BanAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class BanFragment extends Fragment {
    BanDao banDao;
    RecyclerView rcvban;
    ArrayList<Ban> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ban_fragment, container, false);
        rcvban = view.findViewById(R.id.rcvban);
        FloatingActionButton floatthem = view.findViewById(R.id.floatthem);

        // Khởi tạo RecyclerView và Adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvban.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        BanAdapter banAdapter = new BanAdapter(getContext(), list);
        rcvban.setAdapter(banAdapter);

        // Thu thập dữ liệu và cập nhật adapter
        databan();

        floatthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDIgLOG();
            }
        });

        return view;
    }
    private void databan() {
        banDao = new BanDao(getContext());
        list.clear(); // Xóa danh sách trước khi thêm dữ liệu
        list.addAll(banDao.getDSban());
        BanAdapter banAdapter = (BanAdapter) rcvban.getAdapter();
        if (banAdapter != null) {
            banAdapter.notifyDataSetChanged();
        } else {
            Log.e("BanFragment", "Adapter là null");
        }
    }


    private void showDIgLOG(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.diglog_themban,null);
        Spinner spnNhanVien = view.findViewById(R.id.spnNhanVien);
        Spinner spnMon =view.findViewById(R.id.spnMon);
        EditText edttienBan = view.findViewById(R.id.edttienBan);
        getDATAnhanvien(spnNhanVien);
        getDataMon(spnMon);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<String,Object> hmnhanvien =(HashMap<String, Object>)spnNhanVien.getSelectedItem();
                int maNhanVien = (int)hmnhanvien.get("maNhanVien");
                HashMap<String,Object> hmMon = (HashMap<String, Object>)spnMon.getSelectedItem();
                int maMon =(int) hmMon.get("maMon");
                int tien = Integer.parseInt(edttienBan.getText().toString());
                themBan(maNhanVien,maMon,tien);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private  void getDATAnhanvien(Spinner spnNhanVien){
        NhanVienDao nhanVienDao =new NhanVienDao(getContext());
        ArrayList<NhanVien> list = nhanVienDao.getListNhanVien();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (NhanVien nv :list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maNhanVien",nv.getMaNV());
            hs.put("hoTen",nv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter =new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"hoTen"},new int[]{android.R.id.text1});
        spnNhanVien.setAdapter(simpleAdapter);
    }
    private void getDataMon(Spinner spnMon){
        ThucDonDao thucDonDao = new ThucDonDao(getContext());
        ArrayList<Mon> list = thucDonDao.getDSMon();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (Mon mn : list){
            HashMap<String,Object> hs =new HashMap<>();
            hs.put("maMon",mn.getMamon());
            hs.put("tenMon" , mn.getTenmon());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"tenMon"},new int[]{android.R.id.text1});
        spnMon.setAdapter(simpleAdapter);
    }
    private void themBan( int maNhanVien,int maMon , int tien){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("INFO",Context.MODE_PRIVATE);
        Ban ban = new Ban(maNhanVien,maMon,0,tien);
        boolean kiemtra =banDao.themBan(ban);
        if (kiemtra) {
            Toast.makeText(getContext(), "Thêm bàn thành công", Toast.LENGTH_SHORT).show();
            databan();
        } else {
            Toast.makeText(getContext(), "Thêm bàn thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
