package com.example.asdf;

import java.util.Date;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimeActivity extends Activity {
	private MyService mService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time);
		Intent in=new Intent(this,MyService.class);
		bindService(in, conn, BIND_AUTO_CREATE);
		
	}

	ServiceConnection conn=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mService=((MyService.MyBind)service).getMyService();
			
		}
	};
	

}
