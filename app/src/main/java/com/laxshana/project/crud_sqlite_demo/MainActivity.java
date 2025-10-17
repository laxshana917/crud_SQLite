package com.laxshana.project.crud_sqlite_demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    EditText et1,et2,et3;
    Button btn1,btn2,btn3,btn4;
    String rollNo;
    String name;
    String dept;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //finding there ids
        tv1=findViewById(R.id.tv1);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);


        DBHelper obj=new DBHelper(this);
        db=obj.getWritableDatabase();
        db=obj.getReadableDatabase();

    }

    public void onInsert(View view)
    {
        rollNo = et1.getText().toString();
        name=et2.getText().toString();
        dept=et3.getText().toString();


        if(rollNo.equals("") || name.equals("") || dept.equals("")){
            Toast.makeText(this, "Please enter values", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            ContentValues values = new ContentValues();
            values.put("rollno", rollNo);
            values.put("name", name);
            values.put("dept", dept);


            db.insert("student", null, values);
            Toast.makeText(this, "inserted lax,,,,,,", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRead(View view) {
        StringBuffer buffer=new StringBuffer();
        Cursor c=db.rawQuery("select * from student",null);

        while(c.moveToNext()){
            buffer.append("\n"+c.getString(0));
            buffer.append("\n"+c.getString(1));
            buffer.append("\n"+c.getString(2));
        }
        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onUpdate(View view) {
        rollNo = et1.getText().toString();
        name=et2.getText().toString();
        dept=et3.getText().toString();


        if(rollNo.equals("") || name.equals("") || dept.equals("")){
            Toast.makeText(this, "Please enter values", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            ContentValues values = new ContentValues();
            values.put("rollno", rollNo);
            values.put("name", name);
            values.put("dept", dept);


            db.update("student",values,"rollno="+rollNo,null);
            Toast.makeText(this, "updated lax,,,,,,", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDelete(View view) {
        rollNo = et1.getText().toString();
        name=et2.getText().toString();
        dept=et3.getText().toString();


        if(rollNo.equals("") ){
            Toast.makeText(this, "Please enter rollNo", Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            db.delete("student","rollno="+rollNo,null);
            Toast.makeText(this, "deleted rollNo detailes ="+rollNo, Toast.LENGTH_SHORT).show();
        }
    }
}