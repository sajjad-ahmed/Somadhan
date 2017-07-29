package com.droiddigger.somadhan;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Locale;

public class SearchResults extends AppCompatActivity {


    private ListView lv;
    private VendorListAdapter adapter;
    private String searchQuery;
    public static Vendor selectedVenor;
    public TextView showingResultFor;
    private AutoCompleteTextView autoCompleteTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialization();


        adapter = new VendorListAdapter(this, Splash.vendor_Arraylist);


        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(SearchResults.this, SearchDetails.class); //from class, next class.class
                selectedVenor = (Vendor) parent.getAdapter().getItem(position);
                startActivity(it);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        ArrayAdapter searchSuggestionAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Splash.searchStringArrayList);
        autoCompleteTextView2.setAdapter(searchSuggestionAdapter);

        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String _s = (String) adapterView.getAdapter().getItem(i);
                adapter.filter(_s);
                showingResultFor.setText("Search result for " + _s );
            }
        });

        autoCompleteTextView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String _s=  charSequence.toString();
                adapter.filter(_s);
                showingResultFor.setText("Search result for " + _s );
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                String _s = editable.toString();
//                System.out.println("--------la la--------" + _s);
//                adapter.filter(_s);
//                showingResultFor.setText("Search result for " + _s );
            }
        });

//        adapter.filter(searchQuery);
//        showingResultFor.setText("Search result for " + searchQuery );
        autoCompleteTextView2.setText(searchQuery);
autoCompleteTextView2.clearFocus();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void initialization() {
        lv = (ListView) findViewById(R.id.id_listView);
//        searchView = (SearchView) findViewById(R.id.searchView2);
        showingResultFor = (TextView) findViewById(R.id.txt_showing_result_for);
        autoCompleteTextView2 = (AutoCompleteTextView) findViewById(R.id.id_autoCompleteTextView2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            searchQuery = (String) bundle.get("key");
        }




    }

}
