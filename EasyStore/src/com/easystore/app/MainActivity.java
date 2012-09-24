package com.easystore.app;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends Activity {

	protected Button add;
	protected String[] item;
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
