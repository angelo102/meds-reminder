package com.example.medsreminder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
		String s = ((EditText)findViewById(R.id.editTextName)).getText().toString();
		al.setMedName(s);
		
		al.serializeClass(view.getContext());
	}

	public void cancelMethod(View view){
		
	}
}
