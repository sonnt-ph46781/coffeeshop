package com.example.coffeeshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeeshop.DAO.NhanVienDao;
import com.example.coffeeshop.fragment.BanFragment;
import com.example.coffeeshop.fragment.DoiMatKhauFragment;
import com.example.coffeeshop.fragment.LoaiMonFragment;
import com.example.coffeeshop.fragment.MonChayNhatFragment;
import com.example.coffeeshop.fragment.NhanvienFragment;
import com.example.coffeeshop.fragment.ThoatFragment;
import com.example.coffeeshop.fragment.ThongKeFragment;
import com.example.coffeeshop.fragment.ThucDonFragment;
import com.example.coffeeshop.fragment.TrangChuFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

   private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    NavigationView navigationView;
    private final int PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        frameLayout = findViewById(R.id.frameLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        requestPermission();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TrangChuFragment()).commit();
        actionBar.setTitle("Trang chủ");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                if(item.getItemId() == R.id.mQLLoaiMon){
                    fragment = new LoaiMonFragment();
                } else if (item.getItemId() == R.id.mTRANGCHU) {
                    fragment = new TrangChuFragment();
                } else if (item.getItemId() == R.id.mQLTHONGKE) {
                    fragment = new ThongKeFragment();
                }
                else if (item.getItemId() == R.id.mQLBAN) {
                    fragment = new BanFragment();
                }
                else if (item.getItemId() == R.id.mQLNHANVIEN) {
                    fragment = new NhanvienFragment();
                }
                else if (item.getItemId() == R.id.mDOIMATKHAU) {
                    fragment = new DoiMatKhauFragment();
                }
                else if(item.getItemId() == R.id.mMonChayNhat){
                    fragment = new MonChayNhatFragment();
                }
                else {
                    fragment = new ThoatFragment();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
                actionBar.setTitle(item.getTitle());
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Cap quyen thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Cap quyen thanh cong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}