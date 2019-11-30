package com.example.exp4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    DbHelper myDb;
    EditText et_name, et_rollno, et_marks, et_id;
    Button btn_submit, btn_display, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DbHelper(this);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_marks = findViewById(R.id.et_marks);
        et_rollno = findViewById(R.id.et_rollno);
        btn_submit = findViewById(R.id.btn_submit);
        btn_delete = findViewById(R.id.btn_delete);
        btn_display = findViewById(R.id.btn_display);
    }

    public void submit(View view){
        boolean isInserted = myDb.insertData(et_name.getText().toString(), et_rollno.getText().toString(), et_marks.getText().toString());
        if(isInserted = true)
            Toast.makeText(MainActivity.this, "Data Inserted successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data Not Inserted",Toast.LENGTH_LONG).show();
    }

    public void display(View view){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            //  Show Message
            showMessage("Error","Nothing Found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Id :").append(res.getString(0)).append("\n");
            buffer.append("Name :").append(res.getString(1)).append("\n");
            buffer.append("Roll_no:").append(res.getString(2)).append("\n");
            buffer.append("Marks :").append(res.getString(3)).append("\n\n");
        }
        //  Show All Data
        showMessage("data", buffer.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void delete(View view){
        Integer deletedRowes = myDb.deleteData(et_id.getText().toString());
        if(deletedRowes > 0)
            Toast.makeText(MainActivity.this, "Data Deleted successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data Not Deleted",Toast.LENGTH_LONG).show();
    }
}