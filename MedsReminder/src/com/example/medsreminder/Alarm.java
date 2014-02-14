package com.example.medsreminder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import android.content.Context;


public class Alarm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public final static String SERIALIZED_FILENAME ="alarms.bin";
	
	
	private int[] days = new int[7];
	private String medName = "";
	private String medDesciption = "";
	private Date initialDate;
	private Time initialTime;
	private int hourInterval;
	private int minutesInterval;
	private int dose;
	
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
	
	public String getMedDesciption() {
		return medDesciption;
	}

	public void setMedDesciption(String medDesciption) {
		this.medDesciption = medDesciption;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Time getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(Time initialTime) {
		this.initialTime = initialTime;
	}

	public int getHourInterval() {
		return hourInterval;
	}

	public void setHourInterval(int hourInterval) {
		this.hourInterval = hourInterval;
	}

	public int getMinutesInterval() {
		return minutesInterval;
	}

	public void setMinutesInterval(int minutesInterval) {
		this.minutesInterval = minutesInterval;
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

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	
}



