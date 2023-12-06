package com.example.coffeeshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ViewUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.LoaiMonDao;
import com.example.coffeeshop.DTO.LoaiMon;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapter.LoaiMonadapter;

import java.util.ArrayList;

public class LoaiMonFragment extends Fragment {
    RecyclerView rcvloaimon;
    LoaiMonDao loaiMonDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loaimon_fragment, viewGroup, false);
        rcvloaimon =view.findViewById(R.id.rcvloaimon);
        EditText edtthemloaimon = view.findViewById(R.id.edtthemloaimon);
        Button btnthemloaimon = view.findViewById(R.id.btnthemloaimon);
        loaiMonDao = new LoaiMonDao(getContext());
        loaddata();
btnthemloaimon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String tenloai = edtthemloaimon.getText().toString();
        if(loaiMonDao.ThemLoaiMon(tenloai)){
            loaddata();
            edtthemloaimon.setText("");
        }else {
            Toast.makeText(getContext(), "Thêm loại món không thành công", Toast.LENGTH_SHORT).show();
        }
    }
});
        return view;
    }
private void loaddata(){
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
rcvloaimon.setLayoutManager(linearLayoutManager);
    ArrayList<LoaiMon> list= loaiMonDao.getDsLoaiMon();
    LoaiMonadapter loaiMonadapter =new LoaiMonadapter(getContext(),list);
rcvloaimon.setAdapter(loaiMonadapter);
    }
}
