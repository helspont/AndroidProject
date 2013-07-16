package com.easystore.app;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;


public class DeleteItem extends ListActivity{
	private GetDataFromDatabase mydata = null;
	private ArrayList<StoreValueListQuest> data = null;
	protected Button add;
	protected String[] item,value,temp;
	protected int [] values;
	private Button  delete;
	protected TextView date;
	protected TextView sum;
	protected String table;
	protected String where;
	protected DatePicker dpResult;
	private ListView userList;
	private ContentValues checkedList = null;
	private int count;
	private int year;
	private int month;
	private int day;
	private ListView list,list1;
	private ArrayAdapter<String> listAdapter,listAdapter2 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deleteitem);
	/*	
		table = getIntent().getStringExtra("table");
		mydata = new GetDataFromDatabase(this, table);
		add = (Button) findViewById(R.id.addButton);
		delete = (Button) findViewById(R.id.deleteButton);
		logout = (Button) findViewById(R.id.logoutButton);
		help = new HelpPrinter();
		checkedList = new ContentValues();
		data = mydata.getValueAll(table);
		Log.i("SIZE OF DATA", "" + data.size());
		item = new String[data.size()];
		count = data.size();
		chStrings = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			item[i] = data.get(i).getUsername();
			Log.i("data"+i, data.get(i).getUsername());
		}
		
		userList = getListView();
		userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

*/
		delete = (Button) findViewById(R.id.deleteButton);
		table = "items";
		mydata = new GetDataFromDatabase(this, table);
		// mydata = null;
		checkedList = new ContentValues();
		data = mydata.getValueAll(table);
		data = mydata.getValueAll(table);
		sum = (TextView)findViewById(R.id.sum);
		//list= (ListView)findViewById(R.id.list);
		//list1= (ListView)findViewById(R.id.list1);
		count = data.size();
		item = new String[data.size()];
		value = new String[data.size()];
		temp = new String[data.size()];
		values = new int[data.size()];
		for (int i = 0; i < data.size(); i++) {

			item[i] = data.get(i).getName();
			value[i] = data.get(i).getValue();
			values[i] = Integer.parseInt(value[i]);
			
		}
		userList = getListView();
		userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, item));

	delete.setOnClickListener( new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			MyDBHelper dbAdapter = MyDBHelper
					.getDBAdapterInstance(DeleteItem.this);
			dbAdapter.openDataBase();
			for (int i = 0; i < count; i++) {
				if(!checkedList.get("value"+i).toString().contains("nic")){
					Log.e("DELETE"+i, "DELETE"+i);
					Log.i("value "+i, checkedList.getAsString("value"+i));
					//chStrings[i]=checkedList.getAsString("value"+i);
					dbAdapter.deleteRecordInDB(table, "name = ?" ,new String[] { checkedList.getAsString("value"+i)} );
					
				}
				
			}
			//dbAdapter.deleteRecordInDB(table, "user = ?" ,String[] {} );
			dbAdapter.close();
			
				Intent Loginjump = new Intent(DeleteItem.this, MainActivity.class);
				Loginjump.putExtra("table", "login");
				startActivity(Loginjump);
				finishActivity(RESULT_OK);
				finish();
			
			
		}
	});
	}	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		super.onListItemClick(l, v, position, id);
		SparseBooleanArray sparseBooleanArray = userList.getCheckedItemPositions();
		 for(int i = 0; i < count; i++)
	        {

	            if(sparseBooleanArray.get(i) == true) 
	            {
	            	
	            	checkedList.put("value"+i, userList.getItemAtPosition(i).toString());
	            	
	            }
	            else  if(sparseBooleanArray.get(i) == false) 
	            {
	            	checkedList.put("value"+i,"nic");
	            }


	        }
	        
		 for(int j = 0; j<count;j++){
	        	try {
	        		if(!(checkedList.getAsString("value"+j).equals("nic"))){
	        		Log.i("checked value "+j, checkedList.getAsString("value"+j));
	        		}
				} catch (Exception e) {
					Log.e("error","error");
				}
	        	
		 }
		 Log.e("======","==========");
	}
}
