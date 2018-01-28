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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class contacts extends AppCompatActivity {

    private ArrayList<Info> contacts;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contacts = new ArrayList<Info>();
        contacts.add(new Info("Robin", "9176703032", "rxa8448@rit.edu", "", "","", "", ""));
        ArrayAdapter<Info> contactsAdapter = new ArrayAdapter<Info>(this, android.R.layout.simple_list_item_1, contacts);

        System.out.println(findViewById(R.id.item));
        ListView listView = (ListView) findViewById(R.id.item);
        System.out.println("listView " +  listView);
        listView.setAdapter(contactsAdapter);



    }
}
