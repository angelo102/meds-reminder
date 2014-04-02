package com.example.medsreminder;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	public void sendMessage(View view){
		
		//Intent intent = new Intent(this,ListViewActivity.class);
		//startActivity(intent);
		
		// get a Calendar object with current time
		 Calendar cal = Calendar.getInstance();
		 // add 5 minutes to the calendar object
		 cal.add(Calendar.SECOND, 5);
		 Intent intent = new Intent(view.getContext() , MedNotificationReceiver.class);
		 intent.setAction("com.example.medsreminder.MedNotificationReceiver");
		 intent.putExtra("alarm_message", "O'Doyle Rules!");
		 // In reality, you would want to have a static variable for the request code instead of 192837
		 PendingIntent sender = PendingIntent.getBroadcast(this, 192837, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 
		 // Get the AlarmManager service
		 android.app.AlarmManager am = (android.app.AlarmManager) getSystemService(ALARM_SERVICE);
		 am.set(android.app.AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
	}
	
}
