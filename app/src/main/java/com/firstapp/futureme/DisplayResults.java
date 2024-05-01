package com.firstapp.futureme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import android.widget.TextView;



public class DisplayResults extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        dbHelper = new DatabaseHelper(this);
        // Retrieve search criteria from intent
        Intent intent = getIntent();
        String searchTerm = intent.getStringExtra("searchTerm");
        String specialization = intent.getStringExtra("specialization");

        // Call searchJobs function to retrieve list of available jobs
        String[][] searchResults = dbHelper.searchJobs(searchTerm, specialization);

        // Populate UI with search results
        populateUI(searchResults);
    }

    private void populateUI(String[][] searchResults) {
        // Check if searchResults is empty
        if (searchResults == null || searchResults.length == 0) {
            // Display message indicating no results found
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Example: Populate a TextView with job titles
        TextView jobTitlesTextView = findViewById(R.id.jobTitlesTextView);
        StringBuilder jobTitlesBuilder = new StringBuilder();

        for (String[] job : searchResults) {
            // Assuming the first column contains the job title
            String jobTitle = job[0];
            jobTitlesBuilder.append(jobTitle).append("\n");
            // Add other job details as needed
        }

        jobTitlesTextView.setText(jobTitlesBuilder.toString());
    }
}

