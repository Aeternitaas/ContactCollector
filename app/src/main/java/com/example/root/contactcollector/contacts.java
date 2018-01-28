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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        File file = new File( getFilesDir(), "contact-list.json" );
        contacts = new ArrayList<Info>();

        try {
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            StringBuilder sb = new StringBuilder();
            String ln;
            while ( (ln = br.readLine()) != null ) {
                sb.append(ln);
            }

            System.out.println(sb.toString());

            JSONObject localContacts = new JSONObject( sb.toString() );
            JSONArray nameArray = localContacts.names();
            for ( int i = 0; i < nameArray.length(); i++ ) {
                String name = (String) nameArray.get(i);
                System.out.println(name);
//                JSONObject nameProperties = new JSONObject( localContacts.get(name).toString() );
                JSONObject nameProperties = (JSONObject) localContacts.get(name);
                System.out.println( localContacts.get(name).toString() );
//                String sufix = (String) nameProperties.get( "sufix" );
                String prefix = (String) nameProperties.get( "prefix" );
                String number= (String) nameProperties.get( "number" );
                String email = (String) nameProperties.get( "email" );
                String business = (String) nameProperties.get( "business" );
                String website = (String) nameProperties.get( "website" );
                String title = (String) nameProperties.get( "title" );

                contacts.add( new Info( name, number, email, "", prefix, business, website, title ) );
            }
            br.close();
        } catch ( FileNotFoundException fnfe ) {
            System.out.println( "FileNotFound Exception" );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println( "IOException" );
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println( "JSON Exception" );
        }

        ContactAdapter contactAdapter = new ContactAdapter(this, contacts);

        System.out.println(findViewById(R.id.item));
        ListView listView = (ListView) findViewById(R.id.item);
        System.out.println("listView " +  listView);
        listView.setAdapter(contactAdapter);
    }
}
