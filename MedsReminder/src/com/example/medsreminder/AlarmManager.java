package com.example.medsreminder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class AlarmManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public final static String SERIALIZED_FILENAME ="alarms.bin";
	private List<Alarm> alarms;
	
	public AlarmManager(){
		alarms = new ArrayList<Alarm>();
	}

	public List<Alarm> getAlarms() {
		return alarms;
	}

	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}
	
	public void AddAlarm(Alarm alarm){
		alarms.add(alarm);
	}
	
	public Alarm GetAlarm(int position){
		return alarms.get(position);
	}
	
	public void DeleteAlarm(int position){
		alarms.remove(position);
	}
	
	public int AlarmCount(){
		return alarms.size();
	}
	
	public String[] GetAlarmNames(){
		
		String [] values = new String [alarms.size()];
		for(int i =0;i < alarms.size();i++){
			values[i] = alarms.get(i).getMedName();
		}
		
		return values;
		
	}
	
	public void serializeClass(Context c){
		
		try {
			
			FileOutputStream fos = c.openFileOutput(SERIALIZED_FILENAME,Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this);
			
			oos.flush();
			oos.close();
			
			fos.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public AlarmManager loadSerializedClass(Context c){
		
		AlarmManager alarmManager = new AlarmManager();
		
		try {
			FileInputStream fis = c.openFileInput(SERIALIZED_FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			alarmManager = (AlarmManager)ois.readObject();
			
			ois.close();
			
			fis.close();
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alarmManager;
	}
	
	

}
