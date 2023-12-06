package com.example.coffeeshop.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.ThucDonDao;
import com.example.coffeeshop.DTO.Mon;
import com.example.coffeeshop.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ThucDonAdapter extends RecyclerView.Adapter<ThucDonAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Mon> list;
    private ArrayList<HashMap<String,Object>>listHM;
    private ThucDonDao thucDonDao;

    public ThucDonAdapter(Context context, ArrayList<Mon> list, ArrayList<HashMap<String, Object>> listHM, ThucDonDao thucDonDao) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.thucDonDao = thucDonDao;
    }

    @NonNull
    @Override
    public ThucDonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.item_rcv_mon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThucDonAdapter.ViewHolder holder, int position) {
        holder.txtmamon.setText("Mã Món : "+String.valueOf(list.get(position).getMamon()));
        holder.txttenmon.setText("Tên Món : "+list.get(position).getTenmon());
        holder.txtgiatien.setText("GIá : "+ String.valueOf(list.get(position).getGiatien()));
        holder.txtmaloai.setText("Mã Loại : "+String.valueOf(list.get(position).getMaloai()));
        holder.txttenloai.setText("Tên Loại : "+list.get(position).getTenloai());
        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDigLog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = thucDonDao.XoaMon(list.get(holder.getAdapterPosition()).getMamon());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loaddata();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không được xóa sách này vì đang có trên Bàn", Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivdelete, ivSua;
        TextView txtmamon,txttenmon,txtgiatien,txtmaloai,txttenloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmamon = itemView.findViewById(R.id.txtmamon);
            txttenmon = itemView.findViewById(R.id.txttenmon);
            txtgiatien= itemView.findViewById(R.id.txtgiatien);
            txtmaloai= itemView.findViewById(R.id.txtmaloai);
            txttenloai= itemView.findViewById(R.id.txttenloai);
            ivdelete = itemView.findViewById(R.id.ivdelete);
            ivSua= itemView.findViewById(R.id.ivSua);
        }
    }
    private void showDigLog(Mon mon){
        //Alert diglog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater =((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.diglog_suamon,null);
        builder.setView(view);
        EditText edttenmon = view.findViewById(R.id.edttenmon);
        EditText edttien = view.findViewById(R.id.edttien);
        TextView txtmaMon = view.findViewById(R.id.txtmaMon);
        Spinner spnloaimon = view.findViewById(R.id.spnloaimon);
        txtmaMon.setText("Mã món : "+ mon.getMamon());
        edttenmon.setText(mon.getTenmon());
        edttien.setText(String.valueOf(mon.getGiatien()));
        SimpleAdapter simpleAdapter = new SimpleAdapter(context,listHM, android.R.layout.simple_list_item_1,new String[]{"tenLoai"},new int[]{android.R.id.text1});
        spnloaimon.setAdapter(simpleAdapter);
        int index = 0;
        int postion = -1;
        for (HashMap<String,Object> item :listHM){
            if ((int)item.get("maLoai")==mon.getMaloai()){
                postion = index;
            }
            index++;
        }
        spnloaimon.setSelection(postion);
        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenMon =edttenmon.getText().toString();
                int giatien = Integer.parseInt(edttien.getText().toString());
                HashMap<String,Object>hs =(HashMap<String, Object>) spnloaimon.getSelectedItem();
                int maLoai = (int) hs.get("maLoai");
                boolean check = thucDonDao.capNhatMon(mon.getMamon(),tenMon,giatien,maLoai);
                if (check){
                    Toast.makeText(context, "Cập nhật Món thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                }else {
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
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
    private void loaddata(){
        list.clear();
        list= thucDonDao.getDSMon();
        notifyDataSetChanged();
    }
}
