package com.easystore.app;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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

@SuppressLint("NewApi")
public class MainActivity extends ListActivity/*Activity*/ {
	
	private GetDataFromDatabase mydata = null;
	private ArrayList<StoreValueListQuest> data = null;
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
	private Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		activity = this;
		add = (Button) findViewById(R.id.add);
		table = "items";
		mydata = new GetDataFromDatabase(this, table);
		// mydata = null;
		data = mydata.getValueAll(table);
		data = mydata.getValueAll(table);
		sum = (TextView)findViewById(R.id.sum);
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

		sum.setText(""+utrata+" Kè");
		
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, data);
	    setListAdapter(adapter);
		
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(MainActivity.this, AddItem.class);
            	startActivity(myIntent);
            	
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
		
		DecimalFormat mFormat= new DecimalFormat("00");
		mFormat.setRoundingMode(RoundingMode.DOWN);
		
		// set current date into textview
		date.setText(new StringBuilder()
			// Month is 0 based, just add 1
		.append("Dnes je: ").append(mFormat.format(Double.valueOf(day))).append("-").append(mFormat.format(Double.valueOf(month+1))).append("-")
			.append(year).append(" "));
			
 
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_deleteItem:
			Intent myIntent = new Intent(MainActivity.this, DeleteItem.class);
        	startActivity(myIntent);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		activity.openContextMenu(l);
	}

	


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		HelpPrinter help = new HelpPrinter();
		help.displayer("Cont menu", this);
	}




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

