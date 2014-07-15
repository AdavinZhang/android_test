package com.example.calendartest;

import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.ListView;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.widget.Scroller;  

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.calculatortest.R;

import android.util.Log;

public class TaskAdapter extends BaseAdapter  {
	Context context;
	List<TaskView> list;
	float startX;
	float stopX;
	float distance;
	final int minDistance =2;
	LayoutParams params;
	String task;
	String time;
	String priority;
	float moveVelocity;
	float minVelocity = 1f;
	Scroller mScroller;
	int listResult = 0;
	GestureDetector detector;
	
	public TaskAdapter(Context context,List<TaskView> list){
		this.context = context;
		this.list = list;
		
	}
	
	public int getCount(){
		return list.size();
	}
	
	public long getItemId(int position){
		return position;
	}
	
	public Object getItem(int position){
		return list.get(position);
	}
	
	public View getView(int position,View convertView,ViewGroup parent){
		TaskHolder mHolder;	
		HashMap<Integer,View> map = new HashMap<Integer,View>();
		if(map.get(position) == null){
			mHolder = new TaskHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.card_task, null);
			mHolder.taskText = (TextView)convertView.findViewById(R.id.tasktext);
			mHolder.timeText = (TextView)convertView.findViewById(R.id.timetext);
			mHolder.priorityText = (TextView) convertView.findViewById(R.id.prioritytext);
			map.put(position, convertView);
			convertView.setTag(mHolder);
		}
		else{
			convertView = map.get(position);
			mHolder = (TaskHolder)convertView.getTag();
		}
		TaskView taskView = list.get(position);
		mHolder.taskText.setText(taskView.getTask());
		mHolder.timeText.setText(taskView.getTime());
		mHolder.priorityText.setText(taskView.getPriority());
		return convertView;
	}
	
	public void moveAnimate(View v,float dis){
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", dis,0);
		animator.setDuration(400);
		animator.start();
	}

	
	
}

class TaskHolder{
	TextView taskText;
	TextView timeText;
	TextView priorityText;
}
