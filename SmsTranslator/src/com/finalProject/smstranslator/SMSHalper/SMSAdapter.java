package com.finalProject.smstranslator.SMSHalper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finalProject.smstranslator.R;

// TODO: Auto-generated Javadoc
/**
 * The Class SMSAdapter.
 */
public class SMSAdapter extends ArrayAdapter<SMSDetails> {

	/** The context. */
	private Context context;
	
	/** The data. */
	private ArrayList<SMSDetails> data;
	
	/**
	 * Instantiates a new SMS adapter.
	 *
	 * @param context the context
	 * @param objects the objects
	 */
	public SMSAdapter(Context context, ArrayList<SMSDetails> objects) {
		super(context, R.layout.conversation_item_layout, objects);
		this.context = context;
		this.data = objects;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater infalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = infalter.inflate(R.layout.conversation_item_layout, parent, false);
		
		TextView body = (TextView) row.findViewById(R.id.tv_body);
		TextView date = (TextView) row.findViewById(R.id.tv_data);
		View r = row.findViewById(R.id.row_data);
		
		SimpleDateFormat format = new SimpleDateFormat("MMM d HH:mm");
		date.setText(format.format(data.get(position).getDate()));
		body.setText(data.get(position).getBody());
		
		if(data.get(position).getType() == SMSProviderContruct.MESSAGE_TYPE_INBOX){
			r.setBackground(context.getResources().getDrawable(R.drawable.l_bubble));
			body.setGravity(Gravity.LEFT);
		}
		else{
			r.setBackground(context.getResources().getDrawable(R.drawable.r_bubble));
			body.setGravity(Gravity.RIGHT);
		}
		return row;
	}
}