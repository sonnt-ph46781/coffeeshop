package com.example.coffeeshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.DAO.NhanVienDao;
import com.example.coffeeshop.email.SendMail;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout txtTaiKhoanLogin, txtPassLogin;
    TextInputEditText edtUserLogin, edtPassLogin;
    CheckBox chkLuuDangNhap;
    Button btnLogin;
    TextView txtFogot;

    NhanVienDao nhanVienDao;
    private SendMail sendMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtTaiKhoanLogin = findViewById(R.id.txtUserLogin);
        txtPassLogin = findViewById(R.id.txtPassLogin);
        chkLuuDangNhap = findViewById(R.id.chkLuuLogin);
        btnLogin = findViewById(R.id.btnLogin);
        txtFogot = findViewById(R.id.txtFogot);
        edtUserLogin = findViewById(R.id.edtUserLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        nhanVienDao = new NhanVienDao(this);
        sendMail = new SendMail();

        SharedPreferences preferences = getSharedPreferences("INFO", MODE_PRIVATE);
        boolean isRemember = preferences.getBoolean("isRemember", false);
        if(isRemember){
            String user = preferences.getString("userLogin","");
            String pass = preferences.getString("passLogin","");
            edtUserLogin.setText(user);
            edtPassLogin.setText(pass);
            chkLuuDangNhap.setChecked(isRemember);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = txtTaiKhoanLogin.getEditText().getText().toString();
                String pass = txtPassLogin.getEditText().getText().toString();
                boolean check = nhanVienDao.checkDangNhap(taikhoan, pass);
                if(chkLuuDangNhap.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("INFO",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userLogin",taikhoan);
                    editor.putString("passLogin",pass);
                    editor.putBoolean("isRemember", chkLuuDangNhap.isChecked());
                    editor.apply();
                }
                else {
                    SharedPreferences preferences = getSharedPreferences("INFO",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                }
                if (check){
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtFogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fogot();
            }
        });
    }
    private void Fogot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.fogot_dialog,null);
        builder.setView(view);

        EditText txtTaiKhoanFogot;
        Button btnFogot, btnHuyFogot;
        txtTaiKhoanFogot = view.findViewById(R.id.edtTaiKhoanFogot);
        btnFogot = view.findViewById(R.id.btnFogot);
        btnHuyFogot = view.findViewById(R.id.btnHuyFogot);

        AlertDialog dialog = builder.create();

        btnFogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = txtTaiKhoanFogot.getText().toString();
                String email = nhanVienDao.getEmail(taikhoan);
                String pass = nhanVienDao.Fogot(email);
                boolean check = nhanVienDao.getTk(taikhoan);
                if(!check){
                    Toast.makeText(LoginActivity.this, "Không có tài khoản này", Toast.LENGTH_SHORT).show();
                }
                else {
                    sendMail.Send(LoginActivity.this, email, "Lấy lại mật khẩu", "Mật khẩu của tài khoản "+taikhoan+ " là: " + pass);
                    dialog.dismiss();
                }
            }
        });
        btnHuyFogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}