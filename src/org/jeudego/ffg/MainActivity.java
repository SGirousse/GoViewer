package org.jeudego.ffg;

import org.jeudego.listeners.AccessFFGButton;
import org.jeudego.listeners.SearchPlayerButton;
import org.jeudego.listeners.ShowProfileButton;
import org.jeudego.listeners.TournamentButton;
import org.jeudego.listeners.UpdatingButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * The main activity, offering option to Go on main
 * screens and use main features.
 * 
 * @author simeon
 *
 */
public class MainActivity extends Activity {
	
	//Version
	private final String _VERSION = "v0.4.2 - 15.12.2013";

	//Listeners
	private AccessFFGButton _access_ffg_button;
	private ShowProfileButton _show_profile_button;
	private SearchPlayerButton _search_player_button;
	private UpdatingButton _updating_button;
	private TournamentButton _tournament_button;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TRACE", "MainActivity *** public void onCreate(Bundle savedInstanceState) ");

        if( getRotation(this) == 2 ){
        	setContentView(R.layout.activity_main_landscape);
        }else{
        	setContentView(R.layout.activity_main);
        }
        
        //Set version
        TextView textview_version = (TextView) findViewById(R.id.textViewVersion);
        textview_version.setText(this._VERSION);
        SharedPreferences preferences = getSharedPreferences("IDENT_USER_FILE", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("User_surname", "Sim�on");
        editor.putString("User_name", "Girousse");
        editor.putString("User_licence", "1100109");
        editor.commit();
        
		//Get the player informations
		preferences = getSharedPreferences("UPDATE_FILE", Context.MODE_PRIVATE);
		String db_update = preferences.getString("db_update","");
        
		if(db_update.equals("")){
			db_update = "Jamais mis � jour";
		}
		
		TextView tv_update = (TextView) findViewById(R.id.textViewLastMAJDate);
		tv_update.setText(db_update);
		
        // --- Listeners --- //
        //Web site access
        _access_ffg_button = new AccessFFGButton(this);
        ImageButton access_ffg_button = (ImageButton) findViewById(R.id.buttonGoFFG);
        access_ffg_button.setOnClickListener(_access_ffg_button);
        
        //Show own profile
        _show_profile_button = new ShowProfileButton(this);
        ImageButton show_profile_button = (ImageButton) findViewById(R.id.buttonShowProfile);
        show_profile_button.setOnClickListener(_show_profile_button);
        
        //Search player
        _search_player_button = new SearchPlayerButton(this);
        ImageButton search_player_button = (ImageButton) findViewById(R.id.buttonSearch);
        search_player_button.setOnClickListener(_search_player_button);
        
        //Tournaments
        _tournament_button = new TournamentButton(this);
        ImageButton tournament_button = (ImageButton) findViewById(R.id.buttonTournamentSchedule);
        tournament_button.setOnClickListener(_tournament_button);
        
        //Update DB
        ImageButton update_button = (ImageButton) findViewById(R.id.buttonUpdating);
        TextView textview_last_update_date = (TextView) findViewById(R.id.textViewLastMAJDate);
        _updating_button = new UpdatingButton(this, update_button, textview_last_update_date);
        update_button.setOnClickListener(_updating_button);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
       Log.i("TRACE", "MainActivity *** public boolean onOptionsItemSelected(MenuItem item) ");
       //According to item id
       switch (item.getItemId()) 
       {
          case R.id.menu_settings:
                  //Launch the setup activity
                  Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                  startActivity(intent);
             return true;
       }
       return true;
    }
    
    public int getRotation(Context context){
    	final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
    	switch (rotation) {
	    case Surface.ROTATION_0:
	    	return 1;
	    case Surface.ROTATION_90:
	    	return 2;
	    case Surface.ROTATION_180:
	    	return -1;
	    default:
	    	return -2;
	   }
    }
}
