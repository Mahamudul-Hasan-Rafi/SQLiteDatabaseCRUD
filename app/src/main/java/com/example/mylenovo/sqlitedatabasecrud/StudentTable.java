package com.example.mylenovo.sqlitedatabasecrud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StudentTable extends AppCompatActivity {

    int record_length=0;
    TableLayout tableLayout;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_table);

        if(Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

        tableLayout = findViewById(R.id.table);

        MySQLiteDatabaseAdapter mySQLiteDatabaseAdapter = new MySQLiteDatabaseAdapter(this);
        SQLiteDatabase db = mySQLiteDatabaseAdapter.mySQLiteDatabase.getWritableDatabase();

        String columns[]={mySQLiteDatabaseAdapter.mySQLiteDatabase.getNAME(), mySQLiteDatabaseAdapter.mySQLiteDatabase.getUNIVERSITY(), mySQLiteDatabaseAdapter.mySQLiteDatabase.getDEPARTMENT(), mySQLiteDatabaseAdapter.mySQLiteDatabase.getYEAR()
                , mySQLiteDatabaseAdapter.mySQLiteDatabase.getSEMESTER()};

        Cursor cursor = db.query(mySQLiteDatabaseAdapter.mySQLiteDatabase.getTableName(), columns, null, null, null, null, null);

        while(cursor.moveToNext()){
            TableRow tableRow = new TableRow(this);

            tableRow.setPadding(5,5,5,5);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT);
            tableRow.setLayoutParams(layoutParams);

            TextView textView_name = new TextView(this);
            textView_name.setPadding(2,3,3,2);
            textView_name.setGravity(Gravity.CENTER);
            textView_name.setText(cursor.getString(0));
            textView_name.setBackground(getResources().getDrawable(R.drawable.background_column));
            tableRow.addView(textView_name);

            TextView textView_university = new TextView(this);
            textView_university.setPadding(2,3,3,2);
            textView_university.setGravity(Gravity.CENTER);
            textView_university.setText(cursor.getString(1));
            textView_university.setBackground(getResources().getDrawable(R.drawable.background_column));
            tableRow.addView(textView_university);

            TextView textView_department = new TextView(this);
            textView_department.setPadding(2,3,3,2);
            textView_department.setGravity(Gravity.CENTER);
            textView_department.setText(cursor.getString(2));
            textView_department.setBackground(getResources().getDrawable(R.drawable.background_column));
            tableRow.addView(textView_department);

            TextView textView_year = new TextView(this);
            textView_year.setPadding(2,3,3,2);
            textView_year.setGravity(Gravity.CENTER);
            textView_year.setText(cursor.getString(3));
            textView_year.setBackground(getResources().getDrawable(R.drawable.background_column));
            tableRow.addView(textView_year);

            TextView textView_semester = new TextView(this);
            textView_semester.setPadding(2,3,3,2);
            textView_semester.setGravity(Gravity.CENTER);
            textView_semester.setText(cursor.getString(4));
            textView_semester.setBackground(getResources().getDrawable(R.drawable.background_column));
            tableRow.addView(textView_semester);

            tableLayout.addView(tableRow);

            record_length++;
        }

        System.out.println("Length:  "+record_length);
    }
}
