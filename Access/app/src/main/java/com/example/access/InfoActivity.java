package com.example.access;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    EditText edtName, edtUsername, edtEmail, edtPassword;
    RadioGroup rdbGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Initialize views
        edtName = findViewById(R.id.editText4);
        edtUsername = findViewById(R.id.editText2);
        edtEmail = findViewById(R.id.editText); // Added Email EditText
        edtPassword = findViewById(R.id.editText3); // Added Password EditText
        rdbGender = findViewById(R.id.radioGroup);

        // Set title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Account Info");
        }

        Intent intent = getIntent();
        edtUsername.setText(intent.getStringExtra("Username"));
        edtEmail.setText(intent.getStringExtra("Email")); // Hiển thị email
    }
}
