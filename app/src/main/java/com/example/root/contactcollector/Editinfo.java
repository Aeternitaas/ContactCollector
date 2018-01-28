package com.example.root.contactcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
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
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent changeActivity = new Intent(this, Setup.class);
        this.startActivity(changeActivity);
    }


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

        final EditText nameEdit = (EditText) findViewById(R.id.name);
        final EditText emailEdit = (EditText) findViewById(R.id.email);
        final EditText websiteEdit = (EditText) findViewById(R.id.website);
        final EditText phoneNumberEdit = (EditText) findViewById(R.id.phone_number);
        nameEdit.setText(temp.getName());
        emailEdit.setText(temp.getEmail());
        websiteEdit.setText(temp.getWebsite());
        phoneNumberEdit.setText(temp.getNumber());

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setName(nameEdit.getText().toString());
                temp.setEmail(emailEdit.getText().toString());
                temp.setNumber(phoneNumberEdit.getText().toString());
                temp.setWebsite(websiteEdit.getText().toString());

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
