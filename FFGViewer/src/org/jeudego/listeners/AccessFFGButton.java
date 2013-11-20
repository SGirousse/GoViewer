package org.jeudego.listeners;

import org.jeudego.ffg.MainActivity;
import org.jeudego.ffg.SplashScreenFFGActivity;
import org.jeudego.ffg.ViewWebsiteActivity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AccessFFGButton implements OnClickListener{

	private MainActivity _main_activity;
	
	public AccessFFGButton(MainActivity main_activity){
		this._main_activity = main_activity;
	}
	
	@Override
	public void onClick(View v) {
		Log.i("TRACE", "public void onClick(View v)");
		
		//We're gone to access FFG web site via a browser
		Intent i = new Intent(this._main_activity.getBaseContext(), SplashScreenFFGActivity.class);

		this._main_activity.startActivity(i);
	}

}