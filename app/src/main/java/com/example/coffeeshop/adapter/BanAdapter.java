package com.example.coffeeshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.BanDao;
import com.example.coffeeshop.DTO.Ban;
import com.example.coffeeshop.R;

import java.util.ArrayList;

public class BanAdapter extends RecyclerView.Adapter<BanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Ban>list;

    public BanAdapter(Context context, ArrayList<Ban> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_rcv_ban,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanAdapter.ViewHolder holder, int position) {
        holder.txtMaban.setText("Mã bàn : "+list.get(position).getMaban());
        holder.txtMaNV.setText("Mã nhân viên : "+list.get(position).getMaNhanVien());
        holder.txtTenNV.setText("Tên nhân viên : "+list.get(position).getTenThanhVien());
        holder.txtMaMon.setText("Mã Món : "+list.get(position).getMaMon());
        holder.txtTenMon.setText("Tên Món : "+list.get(position).getTenMon());
        String trangthai="";
        if (list.get(position).getTinhTrang()==1){
            trangthai = "Đã thanh toán";
            holder.btnThanhToan.setVisibility(View.GONE);
        }else{
            trangthai="chưa thanh toán";
            holder.btnThanhToan.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trạng Thái : "+trangthai);
        holder.txtTongTien.setText("Tổng tiền : "+list.get(position).getTongTien());
        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BanDao banDao=new BanDao(context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaban,txtMaNV,txtTenNV,txtMaMon,txtTenMon,txtTrangThai,txtTongTien;
        Button btnThanhToan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaban = itemView.findViewById(R.id.txtMaban);
            txtMaNV = itemView.findViewById(R.id.txtMaNV);
            txtTenNV = itemView.findViewById(R.id.txtTenNV);
            txtMaMon= itemView.findViewById(R.id.txtMaMon);
            txtTenMon= itemView.findViewById(R.id.txtTenMon);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            btnThanhToan = itemView.findViewById(R.id.btnThanhToan);

        }
    }
}
