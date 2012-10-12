package com.easystore.app;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;




import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	
	private GetDataFromDatabase mydata = null;
	private ArrayList<StoreValueListQuest> data = null;

	protected Button add;
	protected String[] item,value,temp;
	protected TextView date;
	protected TextView sum;
	protected String table;
	protected String where;
	protected DatePicker dpResult;
	private int year;
	private int month;
	private int day;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		add = (Button) findViewById(R.id.add);
		table = "items";
		mydata = new GetDataFromDatabase(this, table);
		// mydata = null;
		data = mydata.getValueAll(table);
		
		item = new String[data.size()];
		value = new String[data.size()];
		temp = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {

			item[i] = data.get(i).getName();
			value[i] = data.get(i).getValue();
			temp[i] = item[i]+"     "+value[i];
		}
		
		
	setListAdapter(new ArrayAdapter<String>(this, R.layout.row,R.id.label,temp));

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, AddItem.class);
            	startActivity(myIntent);
            	finishActivity(RESULT_OK);
    			finish();
			}
		});
		date = (TextView) findViewById(R.id.date);
		sum = (TextView) findViewById(R.id.sum);
		setCurrentDateOnView();

	}

	public void setCurrentDateOnView() {
 
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into textview
		date.setText(new StringBuilder()
			// Month is 0 based, just add 1
		.append(day).append("-").append(month + 1).append("-")
			.append(year).append(" "));
 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
