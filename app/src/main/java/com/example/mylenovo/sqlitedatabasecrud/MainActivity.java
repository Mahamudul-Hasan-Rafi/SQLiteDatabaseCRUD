package com.example.mylenovo.sqlitedatabasecrud;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    Button button;
    TextInputLayout studentname, university;
    RadioGroup radioGroup_year, radioGroup_semester;
    RadioButton years;
    RadioButton semsters;
    TextView showDetails;

    String studentName, versity, year, semester, dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

        button=findViewById(R.id.submit_info);
        showDetails = findViewById(R.id.details);

        String[] depts = getResources().getStringArray(R.array.departments);
        autoCompleteTextView = findViewById(R.id.autoCompleteText);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_items,
                R.id.text_item ,depts);
        autoCompleteTextView.setAdapter(adapter);

        studentname = findViewById(R.id.name);
        university = findViewById(R.id.university);

        radioGroup_year = findViewById(R.id.year);
        radioGroup_semester = findViewById(R.id.semester);

        button.setOnClickListener(listener);
        showDetails.setOnClickListener(listener);
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.submit_info){
                int id1 = radioGroup_year.getCheckedRadioButtonId();
                years=findViewById(id1);
                year=years.getText().toString();

                int id2 = radioGroup_semester.getCheckedRadioButtonId();
                semsters=findViewById(id2);
                semester = semsters.getText().toString();

                studentName = studentname.getEditText().getText().toString().trim();
                versity = university.getEditText().getText().toString().trim();
                dept = autoCompleteTextView.getText().toString();

                MySQLiteDatabaseAdapter mySQLiteDatabaseAdapter = new MySQLiteDatabaseAdapter(MainActivity.this);
                long id=mySQLiteDatabaseAdapter.insertRow(studentName, versity, dept, year, semester);

                if(id>0)
                    Toast.makeText(MainActivity.this, "Inserted Successfully ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insertion Failed!!", Toast.LENGTH_SHORT).show();
            }
            else{
                //startActivity(new Intent(getApplicationContext(), StudentTable.class));
                /*MySQLiteDatabaseAdapter mySQLiteDatabaseAdapter = new MySQLiteDatabaseAdapter(MainActivity.this);
                String details = mySQLiteDatabaseAdapter.showDetails();

                Toast.makeText(MainActivity.this, details, Toast.LENGTH_SHORT).show();*/
                startActivity(new Intent(getApplicationContext(), StudentTable.class));
            }
        }
    };

}
