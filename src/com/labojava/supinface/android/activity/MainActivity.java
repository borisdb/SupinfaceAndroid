package com.labojava.supinface.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.labojava.supinface.android.R;
import com.labojava.supinface.android.tools.Tools;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(!Tools.isReady(this)){
            startLoginActivity();
        }
    }
    
	private void startLoginActivity()
	{
		Intent intent =  new Intent(this, LoginActivity.class);
		startActivity(intent);
		this.finish();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_activity_menu, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout:
			new AlertDialog.Builder(this)
		    .setTitle(R.string.confirm_title)
		    .setMessage(R.string.logout_confirmation_message)
		    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {	        
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		        SharedPreferences.Editor prefsEditor = prefs.edit();
		    	prefsEditor.clear();
		    	prefsEditor.commit();
		    	Toast.makeText(getApplicationContext(),R.string.reset_done_msg, Toast.LENGTH_LONG+3).show();
		    	startLoginActivity();
		        }
		    })
		    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		    	//close the Dialog
		        }
		    }).show();
			break;

		default:
			break;
		}
		return true;
	}
}