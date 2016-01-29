package com.example.admin.persistentdata;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {

        Log.d("FT App", "Main on Saving instance state...");
        //Bundle b = new Bundle() ;


        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //saveIntoSP() ;
        Log.d("FT App", "Main on Stoping...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveIntoSP() ;
        Log.d("FT App", "Main on Destroying...");
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        loadFromSP() ;

        Log.d("FT App", "Main on Restarting...");
    }

    @Override
    protected void onResume() {
        super.onResume();
/*
        if ( bundle != null ) {
            Log.d("FT App", "\t\t" + bundle.toString());
            Log.d("FT App", "\t\t" + bundle.size());
        }

        EditText et =(EditText) findViewById( R.id.textM);
        et.setText( bundle == null ? et.getText() : bundle.getString("x") );

*/

        loadFromSP () ;

        Log.d("FT App", "Main on Resuming...");
    }


    private void loadFromSP (){
        EditText et =(EditText) findViewById( R.id.tx2);

        /*SharedPreferences settings = getSharedPreferences("PrefencesFile", MODE_PRIVATE);

        et.setText(settings.getString("name", "<>")) ;
        */

        int r = 0 ;

        String res = "" ;
        try {

            FileInputStream fis  = openFileInput( "file.txt") ;

            while (  ( r = fis.read() ) != -1 ) {
                res = res + (char) r ;
            }

            fis.close();

        } catch ( Exception ex ) {
            ex.printStackTrace();
            res = ex.getMessage() ;
        }

        et.setText( res ) ;
    }

    private void saveIntoSP () {

        EditText et =(EditText) findViewById( R.id.tx2);

        /* SharedPreferences settings = getSharedPreferences("PrefencesFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit() ;

        editor.putString( "name", et.getText().toString() );
        editor.commit() ;
        */

        try {
            FileOutputStream fos = openFileOutput("file.txt", MODE_PRIVATE);

            fos.write( et.getText().toString().getBytes() );

            fos.close();

        } catch ( Exception ex  ) {

            ex.printStackTrace();
        }
    }
}
