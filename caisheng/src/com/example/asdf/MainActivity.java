package com.example.asdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView)findViewById(R.id.imageView1);
		BroadcastReceiver receiver=new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
					new AlertDialog.Builder(MainActivity.this).setTitle("what").show();
				}
				
			}
		};
		IntentFilter filter=new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(receiver, filter);
		
		
	}
	static class ViewHolderss{
		Button bt;
		TextView tv;
	}
	
	BaseAdapter add=new BaseAdapter() {
		
		
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			TextView tv;
			Button bt;
			if(arg1==null){
				arg1=(View)getLayoutInflater().inflate(R.layout.xx, null);
				tv=(TextView)arg1.findViewById(R.id.textView1);
				bt=(Button)arg1.findViewById(R.id.button1);
				
			}else{
				
			}
			return null;
		}
		
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
	};
	
	public void setalarm(View v){
	/*	AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
		Intent in=new Intent();
		in.setClassName("com.caisheng.anydo", "com.caisheng.anydo.TimeActivity");
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pending=PendingIntent.getActivity(this, 0, in, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+1000, pending);*/
		startActivity(new Intent(this,TimeActivity.class));
	}

	
	public void left(View v){
		Toast.makeText(this, "left"+iv.getLayoutParams().height, 0).show();
		View view=getLayoutInflater().inflate(R.layout.activity_main, null);
		view.setDrawingCacheEnabled(true);
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();

		
		try {
			Bitmap bitmap=view.getDrawingCache();
			FileOutputStream out=new FileOutputStream(Environment.getExternalStorageDirectory().getPath() +"/aaaa.png");
			bitmap.compress(CompressFormat.PNG, 100, out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void contact(View v){
		startActivity(new Intent(this,TestActivity.class));
		
	}
	
	public void read(View v){
		
	}
	
	public void right(View v){	
		iv.measure(0, 0);
		
		Toast.makeText(this, "right"+iv.getMeasuredHeight(), 0).show();
		Intent in=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:10086"));
		startActivity(in);
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
		/*
		Intent in=new Intent(Intent.ACTION_CALL,Uri.parse("tel:213:"));
		Intent in2=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:w2342"));
		Intent in3=new Intent(Intent.ACTION_VIEW,Uri.parse("http://nokiaguy.com"));
		Intent in4=new Intent()*/
		
	}
	
	public class Boot extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			Intent in=new Intent(MainActivity.this,MainActivity.class);
			in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			arg0.startActivity(in);
			
		}
		
	}
	
	
}
