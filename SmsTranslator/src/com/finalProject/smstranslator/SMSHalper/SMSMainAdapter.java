package com.finalProject.smstranslator.SMSHalper;
import com.finalProject.smstranslator.ContactHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finalProject.smstranslator.R;

public class SMSMainAdapter extends ArrayAdapter<SMSMainDetails> {

	private Context context;
	private ArrayList<SMSMainDetails> data;
	
	public SMSMainAdapter(Context context, ArrayList<SMSMainDetails> objects) {
		super(context, R.layout.main_sms_layout, objects);
		this.context = context;
		this.data = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater infalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = infalter.inflate(R.layout.main_sms_layout, parent, false);
		TextView user = (TextView) row.findViewById(R.id.tv_address);
		TextView count = (TextView) row.findViewById(R.id.tv_msg_count);
		TextView date = (TextView) row.findViewById(R.id.tv_last_msg_date);
		TextView fMsg = (TextView) row.findViewById(R.id.tv_first_msg);
		
		
		user.setText(getUserName(data.get(position).getAddress()));
		count.setText(data.get(position).getCount() + "");
		fMsg.setText(data.get(position).getFirstMsg());
		
		SimpleDateFormat format = new SimpleDateFormat("MMM d");
		date.setText(format.format(data.get(position).getDate()));
		
		
		return row;
	}

	private String getUserName(String addres) {
		ContactHelper ch = new ContactHelper(context);
		String name = ch.getContactName(addres);
		if(name == null)
			return addres;
		return name;
	}
}