package com.example.calendartest;

public class TaskView {
	String task;
	String time;
	String priority;
	int id;
  public TaskView(String task,String time,String priority,int id){
	  this.task = task;
	  this.time = time;
	  this.priority = priority;
	  this.id = id;
  }
  
  public void setTask(String t){
	  task = t;
  }
  
  public void setTime(String t){
      time = t;
  }
  
  public void setPriority(String p){
	  priority = p;
  }
  
  public String getTask(){
	  return task;
  }
  
  public String getTime(){
	  return time;
  }
  
  public String getPriority(){
	  return priority;
  }
  
  public int getId(){
	  return id;
  }
  
  public void setId(int id){
	  this.id = id;
  }
}