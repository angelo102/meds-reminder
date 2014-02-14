package com.example.medsreminder;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MedDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_med_detail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.med_detail, menu);
		return true;
	}
	
	public void saveMethod(View view){
		
		Alarm al = new Alarm();
		
		//Retrieve name from UI
		String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
		al.setMedName(name);
		
		String desc = ((EditText)findViewById(R.id.editTextDesc)).getText().toString();
		al.setMedDesciption(desc);
		
		int dose = Integer.parseInt(((EditText)findViewById(R.id.editTextDose)).getText().toString());
		al.setDose(dose);
		
		DatePicker dp = (DatePicker)findViewById(R.id.datePicker);
		int year = dp.getYear();
		int month = dp.getMonth();
		int day = dp.getDayOfMonth();
		Date date = new Date(year,month,day);
		al.setInitialDate(date);
		
		TimePicker tp = (TimePicker)findViewById(R.id.timePicker);
		
		Time time = new Time();
		time.setHour(tp.getCurrentHour());
		time.setMinutes(tp.getCurrentHour());

		al.setInitialTime(time);
		
		//al.serializeClass(view.getContext());
		AlarmManager am = new AlarmManager();
		am = am.loadSerializedClass(view.getContext());
		am.AddAlarm(al);
		am.serializeClass(view.getContext());
				
		Toast.makeText(this, "Alarm Saved", Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(this,ListViewActivity.class);
		//intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void cancelMethod(View view){
		
		
	}
}
