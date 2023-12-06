package com.example.coffeeshop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.BanDao;
import com.example.coffeeshop.DTO.Ban;
import com.example.coffeeshop.R;
import com.example.coffeeshop.adapter.BanAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BanFragment extends Fragment {
    private Context context;
    BanDao banDao;
    RecyclerView rcvban;
    ArrayList<Ban> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ban_fragment,container,false);
        rcvban = view.findViewById(R.id.rcvban);
        FloatingActionButton floatthem = view.findViewById(R.id.floatthem);
        databan();
        floatthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
    private void databan(){
        banDao= new BanDao(getContext());
        list =banDao.getDSban();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvban.setLayoutManager(linearLayoutManager);
        BanAdapter banAdapter = new BanAdapter(getContext(), list);
        rcvban.setAdapter(banAdapter);
    }
}
