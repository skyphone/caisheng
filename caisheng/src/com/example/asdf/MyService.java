package com.example.asdf;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	public void mymethod(){
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myBind;
	}

	class MyBind extends Binder{
		MyService getMyService(){
			return MyService.this;
		}
	}
	
	MyBind myBind=new MyBind();
	

}
