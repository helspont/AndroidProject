package com.easystore.app;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity/*Activity*/ {
	
	private GetDataFromDatabase mydata = null;
	private ArrayList<StoreValueListQuest> data = null;
	TextView utrataTextView;
	protected Button add;
	protected String[] item,value,temp;
	protected int [] values;
	protected TextView date;
	protected TextView sum;
	protected String table;
	protected String where;
	protected DatePicker dpResult;
	private int year;
	private int month;
	private int day;
	private ListView list,list1;
	private ArrayAdapter<String> listAdapter,listAdapter2 ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		add = (Button) findViewById(R.id.add);
		table = "items";
		mydata = new GetDataFromDatabase(this, table);
		// mydata = null;
		data = mydata.getValueAll(table);
		data = mydata.getValueAll(table);
		utrataTextView = (TextView)findViewById(R.id.sum);
		//list= (ListView)findViewById(R.id.list);
		//list1= (ListView)findViewById(R.id.list1);
		
		item = new String[data.size()];
		value = new String[data.size()];
		temp = new String[data.size()];
		values = new int[data.size()];
		for (int i = 0; i < data.size(); i++) {

			item[i] = data.get(i).getName();
			value[i] = data.get(i).getValue();
			values[i] = Integer.parseInt(value[i]);
			
		}
		
		long utrata = 0;
		
		for (int i = 0; i < data.size(); i++) {
			
			utrata += values[i];
			
		}

		utrataTextView.setText(""+utrata+" Kè");
		
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, data);
	    setListAdapter(adapter);
		
		//ArrayList<String> itemtList = new ArrayList<String>();
		//ArrayList<String> valutList = new ArrayList<String>();
	    //itemtList.addAll( Arrays.asList(item) );
	    //valutList.addAll(Arrays.asList(value));
	      
	    // Create ArrayAdapter using the planet list.  
	    //listAdapter = new ArrayAdapter<String>(this, R.layout.row,R.id.label, itemtList);
	   // listAdapter2 = new ArrayAdapter<String>(this, R.layout.row,R.id.label, valutList);
		
		//CustomAdapter adapter = new CustomAdapter();
		// setListAdapter(customAdapter);
		//list2 = (ListView) findViewById(android.R.id.list);
		//ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.id.label,R.id.label1);
	
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.row,R.id.label,item));
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.row,R.id.label1,value));
	
		//setListAdapter(new ArrayAdapter<String>(this,R.layout.simple?list,R.id.label,temp));
	
		//list.setAdapter(listAdapter);
		//list1.setAdapter(listAdapter2);

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
		.append("Dnes je: ").append(day).append("-").append(month + 1).append("-")
			.append(year).append(" "));
 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	class CustomAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			mydata = new GetDataFromDatabase(getApplicationContext(),table);
			// mydata = null;
			table = "items";
			data = mydata.getValueAll(table);
			for (int i = 0; i < data.size(); i++) {

				item[i] = data.get(i).getName();
				value[i] = data.get(i).getValue();
				//temp[i] = item[i]+"     "+value[i];
			}
			TextView textView = (TextView)findViewById(R.id.label);
			textView.setText(item[position]);
			
			textView =(TextView) findViewById(R.id.label1);
			textView.setText(value[position]);
			//TextView textView1 = (TextView)findViewById(R.id.label1);
			//textView1.setText(data.get(position).getValue());
			return null;
		}
		
	}*/
	
	private class MySimpleArrayAdapter extends ArrayAdapter<StoreValueListQuest> {
		  private final Context context;
		  private final ArrayList<StoreValueListQuest> values;

		  public MySimpleArrayAdapter(Context context, ArrayList<StoreValueListQuest> values) {
		    super(context, R.layout.row, values);
		    this.context = context;
		    this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.row, parent, false);
		    TextView textView1 = (TextView) rowView.findViewById(R.id.itemTextView);
		    TextView textView2 = (TextView) rowView.findViewById(R.id.costTextView);
		    StoreValueListQuest message = values.get(position);

          if (message != null)
          {
              textView1.setText(message.getName());
              textView2.setText(message.getValue());
          }

		    return rowView;
		  }
		} 

	
}

