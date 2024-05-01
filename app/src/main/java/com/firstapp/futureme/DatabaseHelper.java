package com.firstapp.futureme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;


public class DatabaseHelper extends SQLiteOpenHelper {
    // Define database name, version, table name, and column names here
    private static final String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users"; // Corrected table name constant
    private static final String TABLE_JOBS = "jobs";

    // USERS TABLE
    private static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_ROLE = "role";
    public static final String COLUMN_IMAGE_URI = "image_uri";


    // JOBS TABLE
    private static final String COLUMN_JOB_ID = "job_id";

    private static final String COLUMN_JOB_SPECIALIZATION = "specialization";
    private static final String COLUMN_JOB_DESCRIPTION = "job_description";
    private static final String COLUMN_JOB_SALARY = "salary";
    private static final String COLUMN_JOB_LOCATION = "location";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Implement onCreate method to create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_FULL_NAME + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_IMAGE_URI + " TEXT)"; // Add column for image URI
        db.execSQL(createUserTableQuery);


        // Create jobs table
        String createJobsTableQuery = "CREATE TABLE " + TABLE_JOBS + " (" +
                COLUMN_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JOB_SPECIALIZATION + " TEXT, " +
                COLUMN_JOB_DESCRIPTION + " TEXT, " +
                COLUMN_JOB_SALARY + " REAL, " +
                COLUMN_JOB_LOCATION + " TEXT)";
        db.execSQL(createJobsTableQuery);
    }

    // Implement onUpgrade method for version management
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old users table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Drop the old jobs table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOBS);

        // Create new tables with updated schema
        onCreate(db);
    }

    // Inside DatabaseHelper class

    public boolean insertUser(String email, String password, String fullName, String role, String imageUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_FULL_NAME, fullName);
        values.put(COLUMN_ROLE, role);
        values.put(COLUMN_IMAGE_URI, imageUri); // Add image URI to ContentValues
        // Insert the user data into the database
        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        // Check if the insertion was successful
        return result != -1;
    }


    // Method to search for job listings based on a search term
    // Method to search for job listings based on a search term
    public String[][] searchJobs(String searchTerm, String specialization) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query;
        String[] selectionArgs;

        // Ensure COLUMN_COMPANY_NAME constant is defined in your DatabaseHelper class
        String COLUMN_COMPANY_NAME = "company_name";

        if (searchTerm != null && !searchTerm.isEmpty()) {
            // If searchTerm is provided, search by company name
            query = "SELECT * FROM " + TABLE_JOBS +
                    " WHERE " + COLUMN_COMPANY_NAME + " LIKE ?";
            // Use %searchTerm% to match any job with company name containing the searchTerm
            selectionArgs = new String[]{"%" + searchTerm + "%"};
        } else if (specialization != null && !specialization.isEmpty()) {
            // If specialization is provided, search by specialization
            query = "SELECT * FROM " + TABLE_JOBS +
                    " WHERE " + COLUMN_JOB_SPECIALIZATION + " = ?";
            // Use the provided specialization
            // Ensure COLUMN_JOB_SPECIALIZATION constant is defined in your DatabaseHelper class
            selectionArgs = new String[]{specialization};
        } else {
            // No search criteria provided
            return new String[0][0]; // Return an empty array
        }

        Cursor cursor = db.rawQuery(query, selectionArgs);


        // Determine the number of rows returned by the query
        int numRows = cursor.getCount();
        int numColumns = cursor.getColumnCount();

        // Initialize the result array with the appropriate size
        String[][] searchResults = new String[numRows][numColumns];

        // Iterate over the cursor and populate the result array
        int rowIndex = 0;
        if (cursor.moveToFirst()) {
            do {
                for (int columnIndex = 0; columnIndex < numColumns; columnIndex++) {
                    // Extract job listing details from cursor and store in the result array
                    searchResults[rowIndex][columnIndex] = cursor.getString(columnIndex);
                }
                rowIndex++;
            } while (cursor.moveToNext());
        }

        // Close cursor and database connection
        cursor.close();
        db.close();

        // Return the 2D array of job listings
        return searchResults;
    }


}
