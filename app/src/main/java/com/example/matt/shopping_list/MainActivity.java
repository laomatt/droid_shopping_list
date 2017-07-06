package com.example.matt.shopping_list;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillItemList();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fillItemList(){
        Cursor res = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();

        TextView list = (TextView) findViewById(R.id.item_list);
        if (res.getCount() != 0) {
            while (res.moveToNext()) {
                if (res.getString(2) == "1") {
                    buffer.append("- " + res.getString(1) + " -- done \n");
                } else {
                    buffer.append("- " + res.getString(1) + " -- not done <button>sdfa</button> \n");
                }
            }

            list.setText(buffer);
        } else {
            list.setText("nothing to show");
            
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.input_text);
        String message = editText.getText().toString();
        editText.setText("");
        // make the insert
        boolean success = myDb.insertData(message);

        TextView err_mess = (TextView) findViewById(R.id.status_message);

        intent.putExtra(EXTRA_MESSAGE, message);

        if (success) {
            Toast.makeText(MainActivity.this,"inserted",Toast.LENGTH_LONG).show();
            fillItemList();
        } else {
            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
            err_mess.setText("There was an error");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
