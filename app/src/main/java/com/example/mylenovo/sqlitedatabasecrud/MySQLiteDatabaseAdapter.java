package com.example.mylenovo.sqlitedatabasecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteDatabaseAdapter {

    MySQLiteDatabase mySQLiteDatabase;
    public MySQLiteDatabaseAdapter(Context context) {
        mySQLiteDatabase=new MySQLiteDatabase(context);
    }

    public long insertRow(String name, String university, String dept, String year, String semester){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = mySQLiteDatabase.getWritableDatabase();

        contentValues.put(mySQLiteDatabase.NAME, name);
        contentValues.put(mySQLiteDatabase.UNIVERSITY, university);
        contentValues.put(mySQLiteDatabase.DEPARTMENT, dept);
        contentValues.put(mySQLiteDatabase.YEAR, year);
        contentValues.put(mySQLiteDatabase.SEMESTER, semester);

        long id=sqLiteDatabase.insert(mySQLiteDatabase.TABLE_NAME, null, contentValues);

        return id;
    }

    public String showDetails(){
        StringBuffer students = new StringBuffer();

        SQLiteDatabase db = mySQLiteDatabase.getWritableDatabase();
        String columns[]={mySQLiteDatabase.NAME, mySQLiteDatabase.UNIVERSITY, mySQLiteDatabase.DEPARTMENT, mySQLiteDatabase.YEAR
                         , mySQLiteDatabase.SEMESTER};
        Cursor cursor = db.query(mySQLiteDatabase.TABLE_NAME, columns, null, null, null, null, null);

        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String versity = cursor.getString(1);
            String department = cursor.getString(2);
            String year = cursor.getString(3);
            String semester = cursor.getString(4);

            students.append(name+"   "+versity+"   "+department+"   "+year+"   "+semester+"\n");
        }

        return students.toString();
    }
    class MySQLiteDatabase extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "StudentInfo";
        private static final String TABLE_NAME = "Student";
        private static final String ID = "ID";
        private static final String NAME = "Name";
        private static final String UNIVERSITY = "University";
        private static final String DEPARTMENT = "Department";
        private static final String YEAR = "Year";
        private static final String SEMESTER = "Semester";
        private static final int DATABASE_VERSION = 1;
        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+
                " ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                NAME+" VARCHAR(50) NOT NULL,"+
                UNIVERSITY+" VARCHAR(50) NOT NULL,"+
                DEPARTMENT+"  VARCHAR(50) NOT NULL,"+
                YEAR+"  VARCHAR(50) NOT NULL,"+
                SEMESTER+"  VARCHAR(50) NOT NULL);";

        public String getTableName() {
            return TABLE_NAME;
        }

        public String getID() {
            return ID;
        }

        public String getNAME() {
            return NAME;
        }

        public String getUNIVERSITY() {
            return UNIVERSITY;
        }

        public String getDEPARTMENT() {
            return DEPARTMENT;
        }

        public String getYEAR() {
            return YEAR;
        }

        public String getSEMESTER() {
            return SEMESTER;
        }

        public MySQLiteDatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
