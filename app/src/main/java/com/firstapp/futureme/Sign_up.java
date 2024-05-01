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

public class Sign_up extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText companyName;
    private EditText email;
    private EditText password;
    private EditText phoneNumber;
    private Button selectImageButton; // Button to select image
    private Uri imageUri; // Uri to hold the selected image URI
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        companyName = findViewById(R.id.editTextText);
        email = findViewById(R.id.editTextTextEmailAddress2);
        phoneNumber = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextTextPassword2);
        selectImageButton = findViewById(R.id.select_image_button);

        Button sign = findViewById(R.id.button3);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHRActivity();
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

    private void startHRActivity() {
        if (companyName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "You need to fill all the inputs", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper = new DatabaseHelper(this);
            // Pass image URI to database helper method
            databaseHelper.insertUser(email.getText().toString(), password.getText().toString(), companyName.getText().toString(), imageUri.toString(), "hr");
        }
        Intent intent = new Intent(Sign_up.this, HR.class);
        startActivity(intent);
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
