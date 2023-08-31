package com.example.semesterregistration;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.*;
import android.content.*;

import org.w3c.dom.Text;


public class RegistrationInfo extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_info);
        Button back = findViewById(R.id.back);
        Button exit = findViewById(R.id.exit);
        TextView std_no = findViewById(R.id.stdId_info);
        TextView std_name = findViewById(R.id.name_info);
        TextView std_program = findViewById(R.id.program_info);
        TextView std_sem = findViewById(R.id.semester_info);
        TextView std_acdYear = findViewById(R.id.acd_yearInfo);
        TextView std_year = findViewById(R.id.yearD_info);
        TextView std_module = findViewById(R.id.module_info);

        std_no.setText(getIntent().getStringExtra("student_no"));
        std_name.setText(getIntent().getStringExtra("std_name"));
        std_program.setText(getIntent().getStringExtra("program"));
        std_sem.setText(getIntent().getStringExtra("semester"));
        std_acdYear.setText(getIntent().getStringExtra("academic_year"));
        std_year.setText(getIntent().getStringExtra("year"));
        std_module.setText(getIntent().getStringExtra("module"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationInfo.this, MainActivity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }
}
