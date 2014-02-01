package com.example.medsreminder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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
		
		Alarm al = new Alarm();
		al.setMedName("Panadol");
		al.serializeClass(view.getContext());
		
		Alarm al2 = new Alarm();
		
		al2 = al2.loadSerializedClass(view.getContext());
		
		Button bt = (Button)findViewById(R.id.button1);
		bt.setText(al2.getMedName());
	}

}
