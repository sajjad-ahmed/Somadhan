package com.droiddigger.somadhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializer(); //-----------initializing frontend variables
        getSupportActionBar().hide();
    }

    public void initializer() {
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.id_autoCompleteTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Splash.searchStringArrayList); //todo

        autoCompleteTextView.setAdapter(adapter);

    }


    //-----------------------App Exit Code Starts: Double click to exit--------------------------//
    int backcount=0;
    public void onBackPressed() {
        backcount++;
        if (backcount == 1)
            Toast.makeText(this, "Please press BACK again to exit.", Toast.LENGTH_SHORT).show();
        if (backcount == 2) {
            this.finish();
            if (Splash.splashActivityInstance != null)
                Splash.splashActivityInstance.finish();
            finish();
        android.os.Process.killProcess(android.os.Process.myPid()); //kill the entire project
        }
    }
    //--------------------------------App Exit Code Ends---------------------------------//


    //-----------------------Search button clicked Code Starts--------------------------//
    public void on_search_clicked(View view) {
        Intent intent = new Intent(this, SearchResults.class);
        String key=autoCompleteTextView.getText().toString();
        intent.putExtra("key",key); //todo
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    //-----------------------Search button clicked Code Endss--------------------------//


    //---------------------------contact by Email--------------------------------------//
    public void on_contactUS_clicked(View view) {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");

        Email.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"contact@droiddigger.com"});  //developer 's email


        Email.putExtra(Intent.EXTRA_SUBJECT,
                "Somadhan Query: "); // Email 's Subject
        Email.putExtra(Intent.EXTRA_TEXT, "Hello,\n I have a query about ..." + "");  //Email 's Greeting text

        startActivity(Intent.createChooser(Email, "Send Feedback: "));
    }
    //---------------------------contact by Email--------------------------------------//
}