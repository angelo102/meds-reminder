package com.example.medsreminder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.content.Context;

public class Alarm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public final static String SERIALIZED_FILENAME ="alarms.bin";
	
	
	private int[] days = new int[7];
	private String medName = "";
	
	//private static final 

	public int[] getDays() {
		return days;
	}

	public void setDays(int[] days) {
		this.days = days;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
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
	
	public Alarm loadSerializedClass(Context c){
		
		Alarm alarm = new Alarm();
		
		try {
			FileInputStream fis = c.openFileInput(SERIALIZED_FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			alarm = (Alarm)ois.readObject();
			
			ois.close();
			
			fis.close();
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alarm;
		
	}
}

