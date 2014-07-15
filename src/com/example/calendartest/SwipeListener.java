package com.example.calendartest;


import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Button;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.List;
import java.util.ArrayList;


import android.util.Log;



public class SwipeListener implements OnTouchListener {
	static ListView taskList;
	static TaskAdapter adapter;
	static List<TaskView> list;
	float startX;
	float startY;
	float stopX;
	float stopY;
	float distance;
	long delay = 400;
	int minDistance = 400;
	int listResult = 0;
	int id;
	int count=0;
	View view;
	TaskView ves;
	Context context;
	Activity activity;
	Handler handler;
	GestureDetector detector;
	Editor editor;
	DataBaseAdapter Dadapter;
	Cursor allCursor;
	
	public boolean onTouch(View v ,MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			startY = event.getY();	
			startX = event.getX();
			if(startX>100){
			int visiId = taskList.getFirstVisiblePosition();
			id = taskList.pointToPosition((int)startX, (int)startY);
			view = taskList.getChildAt(id-visiId);
			return true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			stopX = event.getX();
			stopY = event.getY();
			distance = stopX - startX;
			float disturb  = stopY - startY;
			if(Math.abs(disturb)<20){
			if(Math.abs(distance)>2){
			view.setTranslationX((int)distance);
			Log.e("null1","null1");
			break;
			 }
			break;
			}
			return false;
		case MotionEvent.ACTION_UP:
			if(Math.abs(distance)>minDistance){
				if(distance>=0){
					deleteView(view);					
					taskList.setFocusableInTouchMode(false);
					handler = new Handler();
					handler.postDelayed((new Runnable(){
						public void run(){				
						list.remove(id);
						adapter.notifyDataSetChanged();
						taskList.invalidate();
						taskList.setFocusableInTouchMode(true);
						}
					}),delay);
					for(int i = id+1;i<=taskList.getLastVisiblePosition();i++){
						float dis = - view.getHeight();
						int visiId = taskList.getFirstVisiblePosition();
						moveAnimate(taskList.getChildAt(i-visiId),dis);
					}
				}
				else if(distance<0){
					archieveView(view);
					listResult = 1;
					for(int i = id+1;i<=taskList.getLastVisiblePosition();i++){
						float dis = - view.getHeight();
						int visiId = taskList.getFirstVisiblePosition();
						moveAnimate(taskList.getChildAt(i-visiId),dis);
					}
					handler = new Handler();
					handler.postDelayed((new Runnable(){
						public void run(){
						list.remove(id);
						adapter.notifyDataSetChanged();
						}
					}),delay);
				 }
				return true;
			}
			else{
				translateAnimate(view,0);
				return true;
			}
		}
		return false;
}
	public void deleteView(View v){
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 1f,0f);
		animator.setDuration(400);
        ObjectAnimator trananimator = ObjectAnimator.ofFloat(v, "translationX", 1000);
        trananimator.setDuration(400);
        set.play(animator).with(trananimator);
        set.setDuration(400);
        set.start();
        
        
	}
	//完成Task效果及信息存储
	public void archieveView(View v){
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 1f,0f);
		animator.setDuration(200);
        ObjectAnimator trananimator = ObjectAnimator.ofFloat(v, "translationX", -1000);
        trananimator.setDuration(400);
        set.play(animator).with(trananimator);
        set.setDuration(400);
        set.start();
	}
	//用于统一滑动效果的最后状态
	public void translateAnimate(View v,float dis){
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationX", dis);
		animator.setDuration(200);
		animator.start();
	}
	
	public void moveAnimate(View v,float dis){
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", dis);
		animator.setDuration(400);
		animator.start();
	}
	
	public void alphaAnimate(View v,float value,float result){
		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", value,result);
		animator.setDuration(200);
		animator.start();
	}
}

