package com.example.calendartest;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import com.example.calculatortest.R;

public class CalendarAdapter extends BaseAdapter {
	String week[] = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	List<Integer> dayList;
	Context context;
	HashMap<Integer,View> hashmap;
	public CalendarAdapter(Context context,List<Integer> dayList ){
		this.context = context; 
		this.dayList = dayList;
	}
	
	public int getCount(){
		return dayList.size();
	}
	
	public long getItemId(int position){
		return dayList.get(position);
	}
	
	public Object getItem(int position){
		return dayList.get(position);
	}
	
	public View getView(int position, View convertView,ViewGroup parent){
		Holder mHolder;
		hashmap = new HashMap<Integer,View>();
		if(hashmap.get(position)==null){
			mHolder = new Holder();
			convertView  = LayoutInflater.from(context).inflate(R.layout.day_view, null);
			mHolder.text = (TextView)convertView.findViewById(R.id.day);
			convertView.setTag(mHolder);
			hashmap.put(position, convertView);
		}
		else{
			mHolder = (Holder)convertView.getTag();
		}
		if(position<7){
			mHolder.text.setText(week[position]);
		}
		else if(dayList.get(position)!=0){
			mHolder.text.setText(dayList.get(position).toString());
		}
		else{
			mHolder.text.setText(null);
		}
		return convertView;
	}

}

class Holder{
	TextView text;
}
