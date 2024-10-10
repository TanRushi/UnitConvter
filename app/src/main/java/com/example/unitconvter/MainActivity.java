package com.example.unitconvter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText numberInput;
    private Spinner unitSpinner;
    private TextView resultText;
    private Button convertButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.number);
        unitSpinner = findViewById(R.id.unitSpinner);
        resultText = findViewById(R.id.text);
        convertButton = findViewById(R.id.button);

        String[] units = {"Kilograms to Pounds", "Pounds to Kilograms", "Centimeters to Meters", "Meters to Centimeters"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void performConversion() {
        String inputText = numberInput.getText().toString();
        if (inputText.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue = Double.parseDouble(inputText);
        String selectedUnit = unitSpinner.getSelectedItem().toString();
        double resultValue = 0;

        switch (selectedUnit) {
            case "Kilograms to Pounds":
                resultValue = inputValue * 2.20462;
                break;
            case "Pounds to Kilograms":
                resultValue = inputValue / 2.20462;
                break;
            case "Centimeters to Meters":
                resultValue = inputValue / 100;
                break;
            case "Meters to Centimeters":
                resultValue = inputValue * 100;
                break;
            default:
                Toast.makeText(MainActivity.this, "Invalid conversion type", Toast.LENGTH_SHORT).show();
                return;
        }
        resultText.setText(String.format("Result: %.2f", resultValue));
    }
}
