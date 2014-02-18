package com.example.medsreminder;

import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MedDetailActivity extends Activity {
	
	private AlarmManager alarmManager;
	
	EditText editTextName;
	EditText editTextDesc;
	EditText editTextDose;
	DatePicker datePicker;
	TimePicker timePicker;
	CheckBox chkBoxMon;
	CheckBox chkBoxTue;
	CheckBox chkBoxWed;
	CheckBox chkBoxThu;
	CheckBox chkBoxFri;
	CheckBox chkBoxSat;
	CheckBox chkBoxSun;
	EditText editTextHourInterval;
	EditText editTextMinuteInterval;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_med_detail);
		
		this.loadMedicine();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.med_detail, menu);
		return true;
	}
	
	public void saveMethod(View view){

		try{
			this.GetControls();

			if(this.ValidateFields()){

				alarmManager = new AlarmManager();
				alarmManager = alarmManager.loadSerializedClass(getApplicationContext());

				Alarm al = new Alarm();

				al.setMedName(editTextName.getText().toString());
				al.setMedDesciption(editTextDesc.getText().toString());

				int dose = Integer.parseInt(editTextDose.getText().toString());
				al.setDose(dose);

				int year = datePicker.getYear();
				int month = datePicker.getMonth();
				int day = datePicker.getDayOfMonth();
				al.setInitialDate(new Date(year,month,day));

				Time time = new Time();
				time.setHour(timePicker.getCurrentHour());
				time.setMinutes(timePicker.getCurrentMinute());
				al.setInitialTime(time);

				al.setMonRepeat(chkBoxMon.isChecked());
				al.setTueRepeat(chkBoxTue.isChecked());
				al.setWedRepeat(chkBoxWed.isChecked());
				al.setThuRepeat(chkBoxThu.isChecked());
				al.setFriRepeat(chkBoxFri.isChecked());
				al.setSatRepeat(chkBoxSat.isChecked());
				al.setSunRepeat(chkBoxSun.isChecked());

				int hourInterval = Integer.parseInt(editTextHourInterval.getText().toString());
				al.setHourInterval(hourInterval);

				int minuteInterval = Integer.parseInt(editTextMinuteInterval.getText().toString());
				al.setMinutesInterval(minuteInterval);

				//Save depending if is a new alarm or an existing alarm
				String alarmId = getIntent().getStringExtra(ListViewActivity.EXTRA_ALARM_ID);
				
				if(alarmId.equals("NEW ALARM"))
					alarmManager.AddAlarm(al);
				else
					alarmManager.getAlarms().set(Integer.parseInt(alarmId),al);  

				alarmManager.serializeClass(view.getContext());

				Toast.makeText(this, "Alarm Saved", Toast.LENGTH_LONG).show();

				Intent intent = new Intent(this,ListViewActivity.class);
				//intent.putExtra(EXTRA_MESSAGE, message);
				startActivity(intent);
			}
		}
		catch(Exception e){

		}
	
	}

	public void cancelMethod(View view){
		
		//Return to ListView Activity
		Intent intent = new Intent(this,ListViewActivity.class);
		startActivity(intent);
	}
	
	private void loadMedicine(){
		try{
			this.GetControls();
			String alarmId = getIntent().getStringExtra(ListViewActivity.EXTRA_ALARM_ID);

			//Prepare Activity Default values for new alarm
			if(alarmId.equals("NEW ALARM")){
				Toast.makeText(this, "New Alarm", Toast.LENGTH_LONG).show();
			}
			else{
				//Load values of selected alarm, alarmId will have an index
				alarmManager = new AlarmManager();
				alarmManager = alarmManager.loadSerializedClass(getApplicationContext());

				Alarm alarm = alarmManager.GetAlarm(Integer.parseInt(alarmId)); 

				editTextName.setText(alarm.getMedName());
				editTextDesc.setText(alarm.getMedDesciption());
				editTextDose.setText(String.valueOf(alarm.getDose()));
				
				int year = alarm.getInitialDate().getYear();
				int month = alarm.getInitialDate().getMonth();
				int day  = alarm.getInitialDate().getDate();
				datePicker.init(year, month, day , null);
				
				timePicker.setCurrentHour(alarm.getInitialTime().getHour());
				timePicker.setCurrentMinute(alarm.getInitialTime().getMinutes());
				chkBoxMon.setChecked(alarm.isMonRepeat());
				chkBoxTue.setChecked(alarm.isTueRepeat());
				chkBoxWed.setChecked(alarm.isWedRepeat());
				chkBoxThu.setChecked(alarm.isThuRepeat());
				chkBoxFri.setChecked(alarm.isFriRepeat());
				chkBoxSat.setChecked(alarm.isSatRepeat());
				chkBoxSun.setChecked(alarm.isSunRepeat());
				editTextHourInterval.setText(String.valueOf(alarm.getHourInterval()));
				editTextMinuteInterval.setText(String.valueOf(alarm.getMinutesInterval()));

				Toast.makeText(this, "Existing Alarm", Toast.LENGTH_LONG).show();
			}
		}
		catch(Exception e){
			AlertDialog alert=new AlertDialog.Builder(MedDetailActivity.this).create();                                                                 
			alert.setMessage("Error: " + e.getMessage());                                                                                           
			alert.show();      

		}

	}
	
	private void GetControls(){
		//Get controls from UI
		editTextName = (EditText)findViewById(R.id.editTextName);
		editTextDesc = (EditText)findViewById(R.id.editTextDesc);
		editTextDose = (EditText)findViewById(R.id.editTextDose);
		datePicker = (DatePicker)findViewById(R.id.datePicker);
		timePicker = (TimePicker)findViewById(R.id.timePicker);
		chkBoxMon = (CheckBox)findViewById(R.id.chkBoxMon);
		chkBoxTue = (CheckBox)findViewById(R.id.chkBoxTue);
		chkBoxWed = (CheckBox)findViewById(R.id.chkBoxWed);
		chkBoxThu = (CheckBox)findViewById(R.id.chkBoxThu);
		chkBoxFri = (CheckBox)findViewById(R.id.chkBoxFri);
		chkBoxSat = (CheckBox)findViewById(R.id.chkBoxSat);
		chkBoxSun = (CheckBox)findViewById(R.id.chkBoxSun);
		editTextHourInterval = (EditText)findViewById(R.id.editTextHourInterval);
		editTextMinuteInterval = (EditText)findViewById(R.id.editTextMinunteInterval);
	}
	
	private boolean ValidateFields(){   
		
		String name;
		String desc;
		int dose, hrInterval, minInterval;
		try{
			name = ((EditText)findViewById(R.id.editTextName)).getText().toString();                                                                       
			desc = ((EditText)findViewById(R.id.editTextDesc)).getText().toString();                                                                       
			dose = Integer.parseInt(((EditText)findViewById(R.id.editTextDose)).getText().toString());
			
			//Check empty values or 0 values
			if(name.trim().equals("") || desc.trim().equals("") || dose==0 ){
				AlertDialog alert=new AlertDialog.Builder(MedDetailActivity.this).create();                                                                 
				alert.setMessage("Values cannot be empty or 0");                                                                                           
				alert.show();      
				return false;
			}
			
		}
		catch(Exception e){
			AlertDialog alert=new AlertDialog.Builder(MedDetailActivity.this).create();                                                                 
			alert.setMessage("Non-numeric value for dose or interval");                                                                                           
			alert.show();      
			return false;
		}
		
		return true;
	}
}
