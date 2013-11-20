package org.jeudego.listeners;

import org.jeudego.ffg.MainActivity;
import org.jeudego.ffg.PlayerListActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class SearchPlayerButton implements OnClickListener{
	
	private MainActivity _main_activity;
	
	public SearchPlayerButton(MainActivity main_activity){
		this._main_activity = main_activity;
	}

	@Override
	public void onClick(View v) {
		
		Intent i = new Intent(this._main_activity.getBaseContext(), PlayerListActivity.class);
		
		this._main_activity.startActivity(i);
		
	}

}