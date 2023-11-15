package com.example.coffeeshop.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeeshop.DAO.NhanVienDao;
import com.example.coffeeshop.DTO.NhanVien;
import com.example.coffeeshop.R;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.Viewhoder>{

    private Context context;
    private ArrayList<NhanVien> list;
    private NhanVienDao nhanVienDao;
    public NhanVienAdapter(Context context, ArrayList<NhanVien> list, NhanVienDao nhanVienDao){
        this.context = context;
        this.list = list;
        this.nhanVienDao = nhanVienDao;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhanvien, parent, false);

        return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        holder.tvHoTen.setText(list.get(position).getHoTen());
        holder.tvChucVu.setText(list.get(position).getLoaiTaiKhoan());
        holder.tvSoDienThoai.setText(list.get(position).getSdt());

        holder.imUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = list.get(holder.getAdapterPosition());
                suaNV(nhanVien);
            }
        });
        holder.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaNhanVien(list.get(holder.getAdapterPosition()).getMaNV(), list.get(holder.getAdapterPosition()).getHoTen());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{
        TextView tvHoTen, tvChucVu, tvSoDienThoai;
        ImageView imUpdate, imDelete;
        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            tvHoTen = itemView.findViewById(R.id.tvHoTen);
            tvChucVu = itemView.findViewById(R.id.tvChucVu);
            tvSoDienThoai = itemView.findViewById(R.id.tvSoDienThoai);
            imUpdate = itemView.findViewById(R.id.imUpdate);
            imDelete = itemView.findViewById(R.id.imDelete);
        }
    }
    private void suaNV(NhanVien nv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suanhanvien, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        EditText edtNameSua, edtEmailSua, edtSdtSua, edtChucVuSua;
        Button btnSuaNhanVien, btnHuySuaNhanVien;
        edtNameSua = view.findViewById(R.id.edtHoTenSua);
        edtEmailSua = view.findViewById(R.id.edtEmailSua);
        edtSdtSua = view.findViewById(R.id.edtSoDienThoaiSua);
        edtChucVuSua = view.findViewById(R.id.edtChucVuSua);
        btnSuaNhanVien = view.findViewById(R.id.btnSuaNhanVien);
        btnHuySuaNhanVien = view.findViewById(R.id.btnHuaSuaNhanVien);
        // lay gia tri cu gan len edittext

        edtNameSua.setText(nv.getHoTen());
        edtEmailSua.setText(nv.getEmail());
        edtSdtSua.setText(nv.getSdt());
        edtChucVuSua.setText(nv.getLoaiTaiKhoan());

        btnSuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = edtNameSua.getText().toString();
                String sdt = edtSdtSua.getText().toString();
                String email = edtEmailSua.getText().toString();
                String chucvu = edtChucVuSua.getText().toString();

               NhanVien nhanVien = new NhanVien(nv.getMaNV(), user,sdt,email,chucvu);
                NhanVienDao nhanVienDao = new NhanVienDao(context);
                boolean check = nhanVienDao.suaNhanVien(nhanVien);
                if (check){
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = nhanVienDao.getListNhanVien();
                    notifyDataSetChanged();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuySuaNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
    private void xoaNhanVien(int manv, String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa nhân viên: " + name+" không?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = nhanVienDao.xoaNhanVien(manv);
                if (check){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list = nhanVienDao.getListNhanVien();
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
