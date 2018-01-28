package com.example.root.contactcollector;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Editinfo extends AppCompatActivity {

    Info temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        File file = new File(getFilesDir(), "user-info.ser");
        try (FileInputStream streamIn = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(streamIn);

        ) {
            temp = (Info) objectInputStream.readObject();

        } catch (Exception ex) {
            temp = new Info();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);

        final EditText nameedit = (EditText) findViewById(R.id.name);
        nameedit.setText(temp.getName());

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setName(nameedit.getText().toString());

                try {
                    String filename = "user-info.ser";
                    File file = new File(getFilesDir(), filename);
                    FileOutputStream fout = new FileOutputStream(file, false);
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeUnshared(temp);
                }
                catch (Exception e){
                    System.err.println("Error" + e);
                }

            }
        });
    }

}
