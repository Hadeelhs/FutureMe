package com.firstapp.futureme;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Import your DatabaseHelper class here
import com.firstapp.futureme.DatabaseHelper;

public class LoginCondidate extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_condidate);

        dbHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);

        Button loginButton = findViewById(R.id.loginButton);
        Button sign = findViewById(R.id.button4);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (isCredentialsValid(email, password)) {
                    startSearchActivity();
                } else {
                    Toast.makeText(LoginCondidate.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(LoginCondidate.this, Search.class);
        startActivity(intent);
    }

    private void startSignActivity() {
        Intent intent = new Intent(LoginCondidate.this, Sign_up_Condidate.class);
        startActivity(intent);
    }
}
