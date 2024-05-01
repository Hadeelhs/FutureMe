package com.firstapp.futureme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_up_Condidate extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, fullNameEditText;
    private DatabaseHelper dbHelper;
    private Button selectImageButton; // Button to select image
    private Uri imageUri; // Uri to hold the selected image URI

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_condidate);

        dbHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        fullNameEditText = findViewById(R.id.fullname);
        selectImageButton = findViewById(R.id.select_image_button);

        Button signUpButton = findViewById(R.id.button3);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String fullName = fullNameEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                    Toast.makeText(Sign_up_Condidate.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = insertUser(email, password, fullName);
                    if (inserted) {
                        Toast.makeText(Sign_up_Condidate.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        startSearchActivity();
                    } else {
                        Toast.makeText(Sign_up_Condidate.this, "Failed to sign up", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set click listener for select image button
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });
    }

    private boolean insertUser(String email, String password, String fullName) {
        if (imageUri == null) {
            Toast.makeText(this, "Please select a profile picture", Toast.LENGTH_SHORT).show();
            return false;
        }
        return dbHelper.insertUser(email, password, fullName, imageUri.toString(),"candidate");
    }

    private void startSearchActivity() {
        Intent intent = new Intent(Sign_up_Condidate.this, Search.class);
        startActivity(intent);
        finish(); // Finish the current activity so the user can't navigate back to the sign-up screen
    }

    // Method to open image picker
    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    // Method to handle image selection result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
        }
    }
}
