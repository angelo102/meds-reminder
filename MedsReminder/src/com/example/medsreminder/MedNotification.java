package com.example.medsreminder;

import java.util.Calendar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MedNotification extends Activity {
	
	
	private PhotoManager photoManager;
	private static final int IMAGE_VIEW_WIDTH = 382*2;//254;//382;//254*2;
	private static final int IMAGE_VIEW_HEIGHT = 144*2;//96;//144;//96*2;
	
	ImageView imageViewMedicine;
	TextView textViewMedName;
	TextView textViewMedDose;
	
	MediaPlayer mediaPlayer;
	String medName;
	String medDose;
	String photoPath;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_med_notification);
		
		//Set Medicine Image
		imageViewMedicine = (ImageView)findViewById(R.id.imgMed);
		photoManager = new PhotoManager();
		photoPath = getIntent().getStringExtra("photo_path");
		photoManager.setPhotoPath(photoPath);
		imageViewMedicine.setImageBitmap(photoManager.GetBitmap(IMAGE_VIEW_WIDTH, IMAGE_VIEW_HEIGHT));
		
		//Set Medicine Name
		textViewMedName = (TextView)findViewById(R.id.txtMedName);
		medName = getIntent().getStringExtra("med_name");
		textViewMedName.setText("Medicine Name: " + medName);
		
		//Set Medicine Dose
		textViewMedDose = (TextView)findViewById(R.id.txtMedDose);
		medDose = getIntent().getStringExtra("med_dose");
		textViewMedDose.setText("Medicine Dose: " + medDose);
		
		
		//start playing sound of alarm
		mediaPlayer = MediaPlayer.create(this, R.raw.sirennoise);
		mediaPlayer.setLooping(true);
		
		mediaPlayer.start(); // no need to call prepare(); create() does that for you
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.med_notification, menu);
		return true;
	}
	
	public void stopPlay(View view){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
			//mediaPlayer.stop();
		}
		else if(!mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			Toast.makeText(this, "Alarm Released", Toast.LENGTH_LONG).show();
		}
		
	}
	
	public void startPlay(View view){
		if(!mediaPlayer.isPlaying())
			mediaPlayer.start();
	}
	
	public void snoozePlay(View view){
		//Set alarm time
		Calendar cal = Calendar.getInstance();
		//cal.setTimeInMillis(System.currentTimeMillis());
		//cal.set(Calendar.HOUR_OF_DAY, al.getInitialTime().getHour());
		//cal.set(Calendar.MINUTE, al.getInitialTime().getMinutes());
		//cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.SECOND, 15);
		
		Intent intent = new Intent(view.getContext() , MedNotificationReceiver.class);
		intent.setAction("com.example.medsreminder.MedNotificationReceiver");
		intent.putExtra("alarm_message", "O'Doyle Rules!");
		intent.putExtra("photo_path", photoPath);
		intent.putExtra("med_name", medName);
		intent.putExtra("med_dose", medDose);
		 
		 // In reality, you would want to have a static variable for the request code instead of 192837
		 PendingIntent sender = PendingIntent.getBroadcast(view.getContext(), 3000/*SCHEDULE_ALARM_ID + i*/ , intent, PendingIntent.FLAG_UPDATE_CURRENT);
		 
		 // Get the AlarmManager service
		 android.app.AlarmManager am = (android.app.AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
		 am.set(android.app.AlarmManager.RTC_WAKEUP,  cal.getTimeInMillis(), sender);
	}

}
