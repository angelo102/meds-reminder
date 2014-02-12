package com.example.medsreminder;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//Test ListView
		//ListView lv = (ListView)findViewById(R.id.medlistView);
		String [] values = new String [] {"list1","List2","List3"};

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
		setContentView(R.layout.lvlayout);
		
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view, menu);
		return true;
	}
*/
	
	@Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    String item = (String) getListAdapter().getItem(position);
	    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  }
	
	public void sendMessage(View view){
		
		Intent intent = new Intent(this,MedDetailActivity.class);
		//intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
		}
}
