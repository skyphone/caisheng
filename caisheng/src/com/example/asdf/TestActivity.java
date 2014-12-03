package com.example.asdf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		SharedPreferences share=getSharedPreferences("abc", MODE_PRIVATE);
		Editor ed=share.edit();
		ed.putString("flying", "love");
		ed.commit();
	}
	
	public void noti(View v){
		String path=Environment.getDataDirectory().getAbsolutePath()
				+"/data/"+getPackageName()+"/shared_prefs/abc.xml";
		try {
			FileInputStream fis=new FileInputStream(path);
			InputStreamReader read=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(read);
			Toast.makeText(this, br.readLine(), 0).show();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void json(View v){
		/*String jsons="[{'my','love','is','where'},{'and','your','love'}]";
		JSONArray array=new JSONArray(jsons);
		JSONObject object=new JSONObject(array.getString(index))*/
		try {
			String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/bb.png";
			InputStream in=getResources().openRawResource(R.raw.t);
			FileOutputStream out=new FileOutputStream(path);
			int count=0;
			byte[] data=new byte[1024];
			while((count=in.read(data))!=-1){
				out.write(data, 0, count);
			}
			out.close();
			in.close();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void md(View v){
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			md5.update("what".getBytes());
			new AlertDialog.Builder(this).setTitle(Base64.encodeToString(md5.digest(), Base64.DEFAULT)).show();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sms(View v){
		String city[]={"guangd","shen","sf"};
		new AlertDialog.Builder(this).setPositiveButton("pos", null).setNegativeButton("nega", null).setTitle("waht")
		.setNeutralButton("center", null).setSingleChoiceItems(city,0, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),which+"", 0).show();
			}
		}).show();
		
		
	}
	
	public void contact(View v){
		LinearLayout lin=(LinearLayout)findViewById(R.id.LinearLayout1);
		Cursor cur=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts._ID+" desc");
		SimpleCursorAdapter ad=new SimpleCursorAdapter(getApplication(), android.R.layout.simple_list_item_1,
				cur, new String[]{ContactsContract.Contacts.DISPLAY_NAME}, new int[]{android.R.id.text2});
		ListView listview=new ListView(this);
		listview.setAdapter(ad);
		lin.addView(listview);
		
	}
	
}
