package com.example.medsreminder;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity {
	
	public final static String EXTRA_ALARM_ID ="com.example.medsreminder.ALARM_ID";
	AlarmManager alarmManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Get saved alarms
		alarmManager = new AlarmManager();
		alarmManager = alarmManager.loadSerializedClass(getApplicationContext());
		String [] values = alarmManager.GetAlarmNames();
		
		CustomMedArrayAdapter adapter = new CustomMedArrayAdapter(this, values);
	    setListAdapter(adapter);
		
		setContentView(R.layout.lvlayout);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}

	
	@Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    
		String item = (String) getListAdapter().getItem(position);
	    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	    
	    //Open MedDetail Activity with selected Alarm
	    Intent intent = new Intent(this,MedDetailActivity.class);
	    intent.putExtra(EXTRA_ALARM_ID, String.valueOf(position));
	    startActivity(intent);
	    
	  }
	
	public void addNewAlarm(View view){

		//Open MedDetail Activity to Add a new alarm
		Intent intent = new Intent(this,MedDetailActivity.class);
		intent.putExtra(EXTRA_ALARM_ID, "NEW ALARM");
		startActivity(intent);

	}
	
	public void deleteAlarm(View view){
		
        //Get Button Clicked and Row Index
        RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
        Button btnChild = (Button)vwParentRow.getChildAt(1);
        int rowIndex = Integer.parseInt(btnChild.getTag().toString());
       
        //Delete the alarm
        alarmManager.DeleteAlarm(rowIndex);
        alarmManager.serializeClass(view.getContext());
        //Reload Activity
        finish();
        startActivity(getIntent());
             
    }
}
