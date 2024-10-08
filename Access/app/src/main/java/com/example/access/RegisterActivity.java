package com.example.access;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtUsername, edtPassword, edtConfirm;
    Button btnSignIn, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtEmail = findViewById(R.id.editText);
        edtUsername = findViewById(R.id.editText2);
        edtPassword = findViewById(R.id.editText3);
        edtConfirm = findViewById(R.id.editText4);
        btnSignIn = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra các trường trống
                if (edtEmail.getText().toString().isEmpty()) {
                    edtEmail.setError("Email cannot be null");
                    return;
                }
                if (edtUsername.getText().toString().isEmpty()) {
                    edtUsername.setError("Username cannot be null");
                    return;
                }
                if (edtPassword.getText().toString().isEmpty()) {
                    edtPassword.setError("Password required");
                    return;
                }
                if (edtConfirm.getText().toString().isEmpty()) {
                    edtConfirm.setError("Confirm password required");
                    return;
                }

                // Kiểm tra địa chỉ email hợp lệ
                if (!isValid(edtEmail.getText().toString())) {
                    edtEmail.setError("Invalid Email Address");
                    return;
                }

                // Kiểm tra mật khẩu có chứa ít nhất một chữ số
                if (!containsDigit(edtPassword.getText().toString())) {
                    edtPassword.setError("Password must contain at least one digit");
                    return;
                }

                // Kiểm tra mật khẩu và xác nhận mật khẩu
                if (edtPassword.getText().toString().equals(edtConfirm.getText().toString())) {

                    saveUserCredentials(edtUsername.getText().toString(), edtPassword.getText().toString());

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("username", edtUsername.getText().toString());
                    intent.putExtra("password", edtPassword.getText().toString());
                    setResult(101, intent);
                    finish();
                } else {
                    edtPassword.setError("Password and confirm password do not match");
                    edtConfirm.setText("");
                }
            }
        });
    }

    // Lưu thông tin đăng ký vào SharedPreferences
    private void saveUserCredentials(String username, String password) {
        // Khi người dùng đăng ký thành công
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", edtUsername.getText().toString());
        editor.putString("password", edtPassword.getText().toString());
        editor.putString("email", edtEmail.getText().toString()); // Lưu email

        editor.apply(); // Lưu lại các thay đổi

    }

    // Phương thức kiểm tra định dạng email
    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return email != null && pat.matcher(email).matches();
    }

    // Phương thức kiểm tra mật khẩu có chứa ít nhất một chữ số
    public static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }
}
