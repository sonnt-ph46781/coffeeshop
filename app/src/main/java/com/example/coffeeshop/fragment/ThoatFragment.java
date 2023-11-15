package com.example.coffeeshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeeshop.LoginActivity;
import com.example.coffeeshop.R;

public class ThoatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.thoat_fragment,container,false);
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        return view;
    }
}
