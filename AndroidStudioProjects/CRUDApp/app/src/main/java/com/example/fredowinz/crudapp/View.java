package com.example.fredowinz.crudapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

class MainActivity extends AppCompatActivity {

    EditText firstname, lastname;
    TextView textView;
    DB_Controller controller;

    public void btn_click(View view) {
        switch (view.getId()){
            case R.id.btnAdd:
                add();
                break;

            case R.id.btnDelete:
                delete();
                break;

            case R.id.btnEdit:
                edit();
                break;

            case R.id.btnList:
                controller.list_all_students(textView);
                break;
        }
    }
    private void add(){
        try{
            controller.insert_student(firstname.getText().toString(), lastname.getText().toString());

        }catch (SQLException e){
            Toast.makeText(MainActivity.this, "Already Exist", Toast.LENGTH_SHORT).show();
        }
        controller.list_all_students(textView);
    }

    private void delete(){
        controller.delete_student(firstname.getText().toString());
        controller.list_all_students(textView);
    }

    private void edit(){
        controller.delete_student(firstname.getText().toString());
        controller.list_all_students(textView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText)findViewById(R.id.txFname);
        lastname = (EditText)findViewById(R.id.txLname);
        textView = (TextView)findViewById(R.id.textView);

        controller = new DB_Controller(this,"",null,1);

        Button btnList = (Button) findViewById(R.id.btnList);

        btnList.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                goToActivityList();

            }

        });
    }

    private void goToActivityList() {

        Intent intent = new Intent(this, ActivityList.class);

        startActivity(intent);

    }
}

class ActivityList extends AppCompatActivity {

    DB_Controller controller;
    TextView thisList;

    public void btn_click2(View view) {
        switch (view.getId()) {
            case R.id.btnDisplay:
                controller.list_all_students2(thisList);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        thisList = (TextView)findViewById(R.id.thisList);


    }
}
