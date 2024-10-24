package com.example.bmiplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity {

    Button saveButton;

    EditText etName, etAge, etWeight, etHeight;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.genderSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(InputActivity.this, item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Male");
        arrayList.add("Female");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        saveButton = findViewById(R.id.saveButton);

        etName = findViewById(R.id.nameField);
        etAge = findViewById(R.id.ageField);
        etWeight = findViewById(R.id.weightField);
        etHeight = findViewById(R.id.heightField);


    }

    public void launchHome(View view){
        isAllFieldsChecked = CheckAllFields();
        if (isAllFieldsChecked) {
            Intent i = new Intent(InputActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }

    private boolean CheckAllFields() {
        // Extracting and converting the input values correctly
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String weightStr = etWeight.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();

        int age;
        float weight, height;

        // Validate Name
        if (name.isEmpty()) {
            etName.setError("Name is required");
            etName.requestFocus();
            return false;
        } else if (name.length() > 30) {
            etName.setError("Name is too long");
            etName.requestFocus();
            return false;
        }

        // Validate Age
        if (ageStr.isEmpty()) {
            etAge.setError("Age is required");
            etAge.requestFocus();
            return false;
        }
        try {
            age = Integer.parseInt(ageStr);
            if (age < 1 || age > 120) {
                etAge.setError("Age must be between 1 and 120");
                etAge.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            etAge.setError("Invalid age format");
            etAge.requestFocus();
            return false;
        }

        // Validate Weight
        if (weightStr.isEmpty()) {
            etWeight.setError("Weight is required");
            etWeight.requestFocus();
            return false;
        }
        try {
            weight = Float.parseFloat(weightStr);
            if (weight < 1 || weight > 150) {
                etWeight.setError("Weight must be between 1 and 150 kg");
                etWeight.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            etWeight.setError("Invalid weight format");
            etWeight.requestFocus();
            return false;
        }

        // Validate Height
        if (heightStr.isEmpty()) {
            etHeight.setError("Height is required");
            etHeight.requestFocus();
            return false;
        }
        try {
            height = Float.parseFloat(heightStr);
            if (height < 50 || height > 250) {
                etHeight.setError("Height must be between 50 and 250 cm");
                etHeight.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            etHeight.setError("Invalid height format");
            etHeight.requestFocus();
            return false;
        }

        // All fields are valid
        return true;
    }

}