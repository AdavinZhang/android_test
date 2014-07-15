package com.example.calendartest;

import android.widget.GridView;
import android.widget.TextView;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View.OnTouchListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.graphics.drawable.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.calculatortest.R;

public class DateView extends Fragment implements OnItemClickListener   {
	Context context;
	GridView content;
	ListView event;
	TextView text;
	TaskAdapter taskAdapter;
	CalendarAdapter adapter;
	List<TaskView> taskList;
	List<Integer> list ;
	CalendarSet set;
	Calendar calendar;
	GestureDetector detector;
	SwipeListener listener;
	Drawable drawable;
	int j = 1;
	int pos ;
	float startX;
	float stopX;
	String week[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
    public void onCreate(Bundle savedInstanceState){
	    super.onCreate(savedInstanceState);
    }
   
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle saveInstanceState){
    	View v = inflater.inflate(R.layout.calendar_view, null);
    	calendar = Calendar.getInstance();
    	calendar.setFirstDayOfWeek(Calendar.SUNDAY);
    	set = new CalendarSet(calendar);
    	context = v.getContext();
    	content = (GridView)v.findViewById(R.id.content);
    	text = (TextView)v.findViewById(R.id.text);
    	event = (ListView)v.findViewById(R.id.event);
    	list = new ArrayList<Integer>();
    	
    	detector = new GestureDetector(context,new OnGestureListener(){
    		public boolean onDoubleTap(MotionEvent event){
    			return false;
    		}
    		
    		public boolean onDoubleTapEvent(MotionEvent event){
    			return false;
    		}
    		
    		public boolean onDown(MotionEvent event){
    			return false;
    		}
    		
    		public void onLongPress(MotionEvent event){}
    		
    		public boolean onScroll(MotionEvent e1,MotionEvent e2,float distanceX,float distanceY){
    			return false;
    		}
    		
    		public void onShowPress(MotionEvent event){}
    		
    		public boolean onSingleTapConfirmed(MotionEvent event){
    			return false;
    		}
    		
    		public boolean onSingleTapUp(MotionEvent event){
    			return false;
    		}
    		
    		public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
    			if(Math.abs(velocityX)>20){
    				if(e2.getRawX()-e1.getRawX()<0){
    					int month = calendar.get(Calendar.MONTH);
    					if(month!=11){
    					calendar.set(Calendar.MONTH, month+1);
    					j =1;
    					refreshList(list);
    					adapter.notifyDataSetChanged();
    				 }
    					else{
    						int year = calendar.get(Calendar.YEAR);
    						calendar.set(Calendar.YEAR, year+1);
    						calendar.set(Calendar.MONTH, 0);
    						j =1;
        					refreshList(list);
        					adapter.notifyDataSetChanged();
    					}
    				}
    				else{
    					int month = calendar.get(Calendar.MONTH);
    					if(month!=0){
    					calendar.set(Calendar.MONTH, month-1);
    					j =1;
    					refreshList(list);
    					adapter.notifyDataSetChanged();
    				  }
    					else{
    						int year = calendar.get(Calendar.YEAR);
    						calendar.set(Calendar.YEAR, year-1);
    						calendar.set(Calendar.MONTH, 11);
    						j =1;
        					refreshList(list);
        					adapter.notifyDataSetChanged();
    					}
    				}
    				return true;
    			}
    			return false;
    		}
    	});
    	
    	content.setOnTouchListener(new OnTouchListener(){
    		public boolean onTouch(View v,MotionEvent event){
    			return detector.onTouchEvent(event);
    		}
    	});
    	
    	refreshList(list);
    	adapter = new CalendarAdapter(context,list);
    	content.setSelector(R.drawable.day_selector);
    	content.setOnItemClickListener(this);
    	//content.setDrawSelectorOnTop(true);
    	content.setAdapter(adapter);
    	
    	taskList = new ArrayList<TaskView>();
    	TaskView view_01 = new TaskView("复习毛概","学术","紧急",1);
    	TaskView view_02 = new TaskView("写完demo","安卓","一般",1);
    	TaskView view_03 = new TaskView("小组讨论","学术","一般",1);
    	taskList.add(view_01);
    	taskList.add(view_02);
    	taskList.add(view_03);
    	taskAdapter = new TaskAdapter(context,taskList);
    	event.setAdapter(taskAdapter);
    	listener = new SwipeListener();
    	listener.taskList = event;
    	listener.list = taskList;
    	listener.adapter = taskAdapter;
    	event.setOnTouchListener(listener);
    	return v;
    }
    
    public void refreshList(List<Integer> list){
    	int month =  calendar.get(Calendar.MONTH);
    	text.setText(week[month]);
    	set = new CalendarSet(calendar);
    	int firstDay = set.getMinimumDay();
    	int lastDay = set.getMaxiumDay();
    	int dayOfWeek = set.getFirstDay();
    	list.clear();
    	for(int i = 0; i<6+dayOfWeek+lastDay;i++){
    		if(i<6+dayOfWeek){
    			list.add(0);
    		}
    		else{
    			list.add(j);
    			j++;
    		}
    	} 	
    }
    
    public void onItemClick(AdapterView<?> parent,View v,int position,long id){
    	if(pos!=0){
    		content.getChildAt(pos).setBackgroundResource(0);
    	}
    	content.getChildAt(position).setBackgroundResource(R.drawable.selected_shape);
    	pos = position;
    }
    
    //public void onNothingSelected(AdapterView<?> parent){}
    

    
    
}
