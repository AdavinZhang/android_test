package com.example.calendartest;

import com.example.calculatortest.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    FragmentManager manager;
    FragmentTransaction transaction;
    FragmentTransaction addTrans;
    EditFragment edit;
    DateView dateView;
    MenuItem add_item;
    MenuItem folder_item;
    MenuItem pri_item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dateView = new DateView();
		edit = new EditFragment();
		manager = this.getFragmentManager();
		transaction = manager.beginTransaction();
		transaction.replace(R.id.activity_main, dateView);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		add_item = menu.findItem(R.id.action_add);
		folder_item = menu.findItem(R.id.action_folder);
		pri_item = menu.findItem(R.id.action_priority);
		add_item.setOnMenuItemClickListener(new OnMenuItemClickListener(){
			public boolean onMenuItemClick(MenuItem item){
				addTrans = manager.beginTransaction();
				addTrans.add(R.id.activity_main , edit).setCustomAnimations(R.animator.slide_in, R.animator.slide_exit).commit();
				return true;
			}
		});
		return true;
	}

}
