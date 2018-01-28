package com.example.root.contactcollector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONObject;
import org.json.JSONException;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scanner extends AppCompatActivity {

    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        // Start barcode scanning intent
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();

        formatTxt = (TextView)findViewById(R.id.scan_format);
        formatTxt.setText( "Addition successful!" );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        // we have a result
        if ( scanningResult != null ) {
            // user information passed via QR
            String userInfo = scanningResult.getContents();
            String json = null;

            try {
                File file = new File( getFilesDir(), "contact-list.json" );

                // reads in QR code's information
                JSONObject qrReader = new JSONObject( userInfo );
                JSONObject qrUser = (JSONObject) qrReader.get( "user");

                // parses the information in the QR code from the JSON file
                String name = (String) qrUser.get( "name" );
                String prefix = (String) qrUser.get( "prefix" );
                String number = (String) qrUser.get( "number" );
                String email = (String) qrUser.get( "email" );
                String business = (String) qrUser.get( "business" );
                String website = (String) qrUser.get( "website" );
                String title = (String) qrUser.get( "title" );

                if ( file.exists() ) {
                    System.out.println( "Exists" );
                    InputStream is = new FileInputStream( file );
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);

                    // local database of users
                    json = new String( buffer, "UTF-8" );
                    is.close();

                    // reads in the local JSON file
                    JSONObject localContacts = new JSONObject( json );
                    JSONObject localUser = new JSONObject();

                    // populates the user with a name: jsonObject relation.
                    localUser.put( "prefix", prefix );
                    localUser.put( "number", number );
                    localUser.put( "email", email );
                    localUser.put( "business", business );
                    localUser.put( "website", website );
                    localUser.put( "title", title );

                    // populates the contacts database with the user
                    localContacts.put( name, localUser );
                }
                else {
                    System.out.println( "Doesn't Exist" );
                    FileWriter os = new FileWriter( file );

                    // reads in the local JSON file
                    JSONObject localContacts = new JSONObject();
                    JSONObject localUser = new JSONObject();

                    // populates the user with a name: jsonObject relation.
                    localUser.put( "prefix", prefix );
                    localUser.put( "number", number );
                    localUser.put( "email", email );
                    localUser.put( "business", business );
                    localUser.put( "website", website );
                    localUser.put( "title", title );

                    localContacts.put( name, localUser );
                    os.write( localContacts.toString() );
                    os.close();
                }

            } catch ( IOException ioe ) {
                System.out.println( "Write error." );
            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println( "JSON Error." );
            }
        }
        else {
            Toast toast = Toast.makeText( getApplicationContext(),
                    "No scan data received.", Toast.LENGTH_SHORT );
            toast.show();
        }
    }
}
