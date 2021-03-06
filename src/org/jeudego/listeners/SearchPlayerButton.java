package org.jeudego.listeners;

import org.jeudego.ffg.MainActivity;
import org.jeudego.ffg.PlayerListActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 * @author simeon
 *
 */
public class SearchPlayerButton implements OnClickListener{
	
	private MainActivity _main_activity;
	
	public SearchPlayerButton(MainActivity main_activity){
		this._main_activity = main_activity;
	}

	public void onClick(View v) {
		Log.i("TRACE", "SearchPlayerButton *** public void onClick(View v) ");
		
		Intent i = new Intent(_main_activity.getBaseContext(), PlayerListActivity.class);
		
		_main_activity.startActivity(i);
	}

}
