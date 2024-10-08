package com.example.access;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo các view
        edtUsername = findViewById(R.id.editText);
        edtPassword = findViewById(R.id.editText1);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Xử lý sự kiện nút Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                // Kiểm tra nếu username hoặc password trống
                if (username.isEmpty() || password.isEmpty()) {
                    // Chuyển đến RegisterActivity nếu trống
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                } else {
                    // Kiểm tra thông tin đăng nhập từ SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                    String storedUsername = sharedPreferences.getString("username", null);
                    String storedPassword = sharedPreferences.getString("password", null);
                    String storedEmail = sharedPreferences.getString("email", null); // Lấy email
                    String storedName = sharedPreferences.getString("name", null); // Lấy tên

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        // Chuyển đến InfoActivity nếu đăng nhập thành công
                        Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
                        intent.putExtra("Username", username); // Truyền dữ liệu username sang InfoActivity
                        intent.putExtra("Email", storedEmail); // Truyền email sang InfoActivity
                        intent.putExtra("Name", storedName); // Truyền tên sang InfoActivity
                        startActivity(intent);
                    } else {
                        // Thông báo đăng nhập thất bại
                        Toast.makeText(LoginActivity.this, "Mật khẩu / Tài khoản không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Xử lý sự kiện nút Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            edtUsername.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));
        }
    }
}
