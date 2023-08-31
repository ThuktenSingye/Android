package com.example.semesterregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Initializaing spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2= (Spinner) findViewById(R.id.spinner2);

        EditText std_num = findViewById(R.id.stdNo_input);
        EditText std_name = findViewById(R.id.name_input);
        Spinner std_program = (Spinner) findViewById(R.id.spinner);
        EditText stdAcd_year = findViewById(R.id.academicYear_input);
        EditText current_year = findViewById(R.id.year_input);
        EditText std_module = findViewById(R.id.module_input);
        Spinner std_sem = (Spinner) findViewById(R.id.spinner2);

        Button submit = findViewById(R.id.submit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.programme, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

// intent code
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                collect user input
                String stdNo = std_num.getText().toString();
                String name = std_name.getText().toString();
                String program = std_program.getSelectedItem().toString();
                String acd_year = stdAcd_year.getText().toString();
                String year= current_year.getText().toString();
                String semester = std_sem.getSelectedItem().toString();
                String module = std_module.getText().toString();
                Toast.makeText(MainActivity.this, "Submitted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, RegistrationInfo.class);

                intent.putExtra("student_no", stdNo);
                intent.putExtra("std_name", name);
                intent.putExtra("academic_year",acd_year);
                intent.putExtra("program", program);
                intent.putExtra("year", year);
                intent.putExtra("semester", semester);
                intent.putExtra("module", module);

                startActivity(intent);
            }
        });


    }
}