package com.firstapp.futureme;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = findViewById(R.id.button);
        Button sign = findViewById(R.id.button2);

        dbHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.EmailAddress);
        passwordEditText = findViewById(R.id.Password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (isCredentialsValid(email, password)) {
                    startSearchActivity();
                } else {
                    Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignActivity();
            }
        });
    }
    private boolean isCredentialsValid(String email, String password) {
        // Query the database to check if email and password match any user record
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT * FROM " + DatabaseHelper.TABLE_USERS +
                        " WHERE " + DatabaseHelper.COLUMN_EMAIL + " = ? AND " +
                        DatabaseHelper.COLUMN_PASSWORD + " = ?",
                new String[]{email, password});

        // Check if the cursor contains any rows
        boolean isValid = cursor.getCount() > 0;

        // Close the cursor and the database
        cursor.close();
        dbHelper.close();

        return isValid;
    }
    private void startSearchActivity() {
        Intent intent = new Intent(Login.this, HR.class);
        startActivity(intent);
    }
    private void startSignActivity() {
        Intent intent = new Intent(Login.this, Sign_up.class);
        startActivity(intent);
    }


    }

