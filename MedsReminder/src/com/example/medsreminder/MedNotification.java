package com.example.medsreminder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class MedNotification extends Activity {
	
	
	private PhotoManager photoManager;
	private static final int IMAGE_VIEW_WIDTH = 382*2;//254;//382;//254*2;
	private static final int IMAGE_VIEW_HEIGHT = 144*2;//96;//144;//96*2;
	
	ImageView imageViewMedicine;
	TextView textViewMedName;
	TextView textViewMedDose;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_med_notification);
		
		//Set Medicine Image
		imageViewMedicine = (ImageView)findViewById(R.id.imgMed);
		photoManager = new PhotoManager();
		String photoPath = getIntent().getStringExtra("photo_path");
		photoManager.setPhotoPath(photoPath);
		imageViewMedicine.setImageBitmap(photoManager.GetBitmap(IMAGE_VIEW_WIDTH, IMAGE_VIEW_HEIGHT));
		
		//Set Medicine Name
		textViewMedName = (TextView)findViewById(R.id.txtMedName);
		String medName = getIntent().getStringExtra("med_name");
		textViewMedName.setText("Medicine Name: " + medName);
		
		//Set Medicine Dose
		textViewMedDose = (TextView)findViewById(R.id.txtMedDose);
		String medDose = getIntent().getStringExtra("med_dose");
		textViewMedDose.setText("Medicine Dose: " + medDose);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.med_notification, menu);
		return true;
	}

}
