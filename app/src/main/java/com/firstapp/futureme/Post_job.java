package com.firstapp.futureme;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Post_job extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job); // Set content view first

        Spinner specializationSpinner = findViewById(R.id.specializationSpinner);
        specializationSpinner.setVisibility(View.VISIBLE);

        // Manually define the specialization options
        List<String> specializations = new ArrayList<>();
        specializations.add("Web Development");
        specializations.add("Mobile Development");
        specializations.add("Cyber Security");
        specializations.add("Design");

        // Create an ArrayAdapter and set it to the Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, specializations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializationSpinner.setAdapter(adapter);

        // Finding the button by its ID
        Button postButton = findViewById(R.id.button3);

        // Setting click listener to the button
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here you would have the code to actually post the job
                // If posting is successful, show a toast message
                if (jobPostedSuccessfully()) {
                    showToast("Job posted successfully");
                } else {
                    showToast("Failed to post job");
                }
            }
        });
    }

    // Method to simulate posting of job
    private boolean jobPostedSuccessfully() {
        // Write your logic here to post the job
        // Return true if posting is successful, false otherwise
        return true; // Change this based on your actual implementation
    }

    // Method to show toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
