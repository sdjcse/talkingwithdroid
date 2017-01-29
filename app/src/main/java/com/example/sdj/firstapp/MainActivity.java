package com.example.sdj.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button mButton = (Button) findViewById(R.id.buttonInput);


        mButton.setOnClickListener(

                new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText nameBox = (EditText) findViewById(R.id.editText2);
                EditText ageBox = (EditText) findViewById(R.id.editText3);
                EditText idBox = (EditText) findViewById(R.id.editText4);

                final String name = nameBox.getText().toString();
                final String age = ageBox.getText().toString();
                final String id = idBox.getText().toString();
                if (name.equals("")  || id.equals("")  || age.equals("") )
                {
                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                try
                {
                    int ageNum = Integer.valueOf(age);
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(MainActivity.this,"Age should be a number!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent myIntent = new Intent(MainActivity.this,BeatDisplay.class);
                myIntent.putExtra("name",name);
                myIntent.putExtra("age",age);
                myIntent.putExtra("id",id);
                startActivity(myIntent);
            }
        }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
