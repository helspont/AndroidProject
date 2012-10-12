package com.easystore.app;


import java.util.ArrayList;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends Activity {
	
	
	private GetDataFromDatabase mydata = null;
	private ArrayList<StoreValueListQuest> data = null;
	private Button addButton;
	private EditText nameOfItem;
	private EditText valueOfItem;
	protected String table;
	private HelpPrinter help= new HelpPrinter();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		
		table = "items";
		mydata = new GetDataFromDatabase(this, table);
		data = mydata.getValueAll(table);
		addButton = (Button) findViewById(R.id .button1);
		nameOfItem = (EditText) findViewById(R.id. nameofitem);
		valueOfItem = (EditText) findViewById(R.id.valueofitem);
		
		addButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = nameOfItem.getText().toString();
				String value = valueOfItem.getText().toString();
				
				MyDBHelper dbAdapter = MyDBHelper
						.getDBAdapterInstance(AddItem.this);
				dbAdapter.openDataBase();

				ContentValues initialValues = new ContentValues();
				initialValues.put("name", name);
				initialValues.put("value", value);

				long n = dbAdapter.insertRecordsInDB("items", null,
						initialValues);
				help.displayer("new row inserted with id = " + n, AddItem.this);
				
				dbAdapter.close();
				
				Intent jump = new Intent(AddItem.this, MainActivity .class);
				startActivity(jump);
				finishActivity(RESULT_OK);
				finish();
				
			}
		});
	}

}
