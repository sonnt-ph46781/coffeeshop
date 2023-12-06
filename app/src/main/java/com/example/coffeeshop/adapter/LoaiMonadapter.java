package com.example.coffeeshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.LoaiMonDao;
import com.example.coffeeshop.DTO.LoaiMon;
import com.example.coffeeshop.R;

import java.util.ArrayList;

public class LoaiMonadapter extends RecyclerView.Adapter<LoaiMonadapter.ViewHoder> {
    private Context context;
    private ArrayList<LoaiMon>list;

    public LoaiMonadapter(Context context, ArrayList<LoaiMon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LoaiMonadapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.item_rcv_loaimon,parent,false);
        return new ViewHoder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiMonadapter.ViewHoder holder, int position) {
        holder.txttenloai.setText("Tên loại : "+list.get(position).getTenloai());
        holder.txtmaloai.setText("Mã Loại : "+String.valueOf(list.get(position).getMaloai()));
        holder.ivxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiMonDao loaiMonDao = new LoaiMonDao(context);
                int check=loaiMonDao.XoaLoaiMon(list.get(holder.getAdapterPosition()).getMaloai());
                switch (check){
                    case 1:
                        list.clear();
                        list =loaiMonDao.getDsLoaiMon();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa sách nào", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "XÓA không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView txtmaloai,txttenloai;
        ImageView ivxoa;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtmaloai = itemView.findViewById(R.id.txtMaloai);
            txttenloai=itemView.findViewById(R.id.txtTenloai);
            ivxoa = itemView.findViewById(R.id.ivxoa);
        }
    }
}
