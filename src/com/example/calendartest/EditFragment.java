package com.example.calendartest;

import com.example.calculatortest.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

public class EditFragment extends Fragment {
   
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstancestate){
		View v = inflater.inflate(R.layout.fragment_edit, null);
		return v;
	}
}
