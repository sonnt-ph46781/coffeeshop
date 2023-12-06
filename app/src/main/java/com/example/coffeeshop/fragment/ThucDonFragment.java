package com.example.coffeeshop.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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

import com.example.coffeeshop.DAO.LoaiMonDao;
import com.example.coffeeshop.DAO.ThucDonDao;
import com.example.coffeeshop.DTO.LoaiMon;
import com.example.coffeeshop.DTO.Mon;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapter.ThucDonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ThucDonFragment extends Fragment {
    ThucDonDao thucDonDao;
    RecyclerView rcvthucdon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thucdon_fragment,container,false);
        rcvthucdon=view.findViewById(R.id.rvcthucdon);
        FloatingActionButton floatadd =view.findViewById(R.id.floatadd);
        thucDonDao= new ThucDonDao(getContext());
        dulieumon();
        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdiglog();
            }
        });
        return view;
    }
    private void dulieumon(){
        ArrayList<Mon> list = thucDonDao.getDSMon();
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        rcvthucdon.setLayoutManager(linearLayoutManager);
        ThucDonAdapter thucDonAdapter =new ThucDonAdapter(getContext(),list,getDsLoaiMon(),thucDonDao);
        rcvthucdon.setAdapter(thucDonAdapter);
    }
    private void showdiglog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.diglog_themmon,null);
        builder.setView(view);
        EditText edtTenMon = view.findViewById(R.id.edtTenMon);
        EditText edtTien = view.findViewById(R.id.edtTien);
        Spinner spnLoaiMon = view.findViewById(R.id.spnLoaiMon);
        SimpleAdapter simpleAdapter= new SimpleAdapter(getContext(),getDsLoaiMon(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},new int[]{android.R.id.text1});
        spnLoaiMon.setAdapter(simpleAdapter);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenMon = edtTenMon.getText().toString();
                int tien =Integer.parseInt(edtTien.getText().toString());
                HashMap<String,Object>hs = (HashMap<String, Object>)spnLoaiMon.getSelectedItem();
                int maLoai = (int) hs.get("maLoai");
                boolean check = thucDonDao.themMonMoi(tenMon,tien,maLoai);
                if(check){
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    dulieumon();
                }else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });























        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private ArrayList<HashMap<String,Object>>getDsLoaiMon(){
        LoaiMonDao loaiMonDao= new LoaiMonDao(getContext());
        ArrayList<LoaiMon> list = loaiMonDao.getDsLoaiMon();
        ArrayList<HashMap<String,Object>>listHM = new ArrayList<>();
        for(LoaiMon loaiMon : list){
            HashMap<String,Object> hs= new HashMap<>();
            hs.put("maLoai",loaiMon.getMaloai());
            hs.put("tenLoai",loaiMon.getTenloai());
            listHM.add(hs);
        }
        return listHM;
    }
}
