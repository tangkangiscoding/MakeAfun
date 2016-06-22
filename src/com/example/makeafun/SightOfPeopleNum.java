package com.example.makeafun;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.Window;
import android.widget.ImageView;

public class SightOfPeopleNum extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sight);
		Intent i = getIntent();
		Bundle d = i.getExtras();
		ImageView image = (ImageView)findViewById(R.id.sight);
		String code = d.getString("Code");
		if(code.equals("2")){
			image.setImageDrawable(getResources().getDrawable(R.drawable.peoplenumofsight));
		}else if(code.equals("3")){
			image.setImageDrawable(getResources().getDrawable(R.drawable.parknum));
		}else if(code.equals("4")){
			image.setImageDrawable(getResources().getDrawable(R.drawable.subwaypeoplenum));
		}
	}
}
