package org.jeudego.ffg;

import java.util.ArrayList;
import java.util.List;

import org.jeudego.adapters.PlayerQuickViewAdapter;
import org.jeudego.database.PlayerDAO;
import org.jeudego.listeners.GoSearchPlayerButton;
import org.jeudego.listeners.PlayerItem;
import org.jeudego.pojo.Player;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * From that activity, you can search, select and access to a player profile (which will be showed in another activity)
 * 
 * @author simeon
 *
 */
public class PlayerListActivity extends Activity {
	
	//Listeners
	private GoSearchPlayerButton _go_search_player_button;
	//Adapters
	private PlayerQuickViewAdapter _player_quick_view_adapter;
	//Player list
	private PlayerDAO _player_dao;
	private List<Player> _list_of_players;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TRACE", "PlayerListActivity *** public void onCreate(Bundle savedInstanceState) ");
        setContentView(R.layout.activity_list_player);
        
        //Player list initialization
        _player_dao = new PlayerDAO(this);
        _player_dao.open(false);
        
        _list_of_players = new ArrayList<Player>();
        _list_of_players = _player_dao.getAllPlayers();
        
        _player_dao.close();

        // --- Adapters --- //
        //Each player to show
        _player_quick_view_adapter = new PlayerQuickViewAdapter(this, R.layout.item_player, _list_of_players);
        //Add it to listView
        ListView lv = (ListView) findViewById(R.id.listViewPlayers);
        lv.setAdapter(_player_quick_view_adapter);
        
        // --- Listeners --- //
        //Search player
        EditText name_edit_text = (EditText) findViewById(R.id.editTextPlayerName);
        EditText surname_edit_text = (EditText) findViewById(R.id.editTextPlayerSurname);
        _go_search_player_button = new GoSearchPlayerButton(this, name_edit_text, surname_edit_text, _list_of_players, _player_quick_view_adapter);
        Button go_search_player_button = (Button) findViewById(R.id.buttonGoSearchPlayer);
        go_search_player_button.setOnClickListener(_go_search_player_button);
        //Item clicked
        lv.setOnItemClickListener(new PlayerItem(this, _list_of_players));
     
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
