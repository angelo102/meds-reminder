package com.example.medsreminder;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CustomMedArrayAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] values;

	public CustomMedArrayAdapter(Context context, String[] values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		
		textView.setText(values[position]);
		
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
		//Button deleteButton = (Button)rowView.findViewById(R.id.deleteMedButton);
		
		// Change the icon for Windows and iPhone
		/*
		String s = values[position];
		if (s.startsWith("Windows7") || s.startsWith("iPhone")
				|| s.startsWith("Solaris")) {
			imageView.setImageResource(R.drawable.no);
		} else {
			imageView.setImageResource(R.drawable.ok);
		}
		
		*/

		//testing
		//convertView.setOnClickListener(new OnItemClickListener(position));
		
		return rowView;
	}
	
	
	
	
}

