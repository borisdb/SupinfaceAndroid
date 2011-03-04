/**
 * 
 */
package com.labojava.supinface.android.activity;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.labojava.supinface.android.R;
import com.labojava.supinface.android.io.StudentRequest;
import com.labojava.supinface.android.preferences.UserPreferences;
import com.labojava.supinface.android.tools.Tools;

/**
 * @author boris
 *
 */
public class LoginActivity extends Activity{

	private LoginTask mLoginTask;
	
	private EditText mLoginEditText;
	private EditText mPasswordEditText;
	private Button mLoginButton;
	
	private String mLoginString;
	private String mPasswordString;
	
	private SharedPreferences prefs;
	private SharedPreferences.Editor prefsEditor;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		mLoginEditText= (EditText) findViewById(R.id.LoginEditText);
		mPasswordEditText = (EditText) findViewById(R.id.PasswordEditText);
		mLoginButton = (Button) findViewById(R.id.LoginButton);
	
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefsEditor = prefs.edit();
		
		mLoginString= prefs.getString(UserPreferences.LOGIN, "n/a");
		mPasswordString= prefs.getString(UserPreferences.PASSWORD, "n/a");
		
		if(!mLoginString.equals("n/a")&&!mPasswordString.equals("n/a"))
		{
			mLoginEditText.setText(mLoginString);
			mPasswordEditText.setText(mPasswordString);
		}
		
		
		mLoginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String _login=mLoginEditText.getText().toString();
				String _password=mPasswordEditText.getText().toString();
				
				if(mLoginTask == null || !mLoginTask.getStatus().equals(AsyncTask.Status.RUNNING))
				{
					mLoginTask = new LoginTask();
					mLoginTask.execute(_login,_password);
				}
				else{
					Toast.makeText(getApplicationContext(),R.string.login_process_in_progress, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	private void switchToMainActivity()
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
	public void finishLoginActivity()
	{
		this.finish();
	}
	
	public class LoginTask extends AsyncTask<String , String, Boolean>{

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Boolean doInBackground(String... params) {
			String login = params[0];
			String password = params[1];
			String content=null;
			if(Tools.haveInternet(getApplicationContext())==false)
			{
				publishProgress("Pas de connexion internet");
				return false;
			}
			else
			{
				HttpResponse response;
				try {
					response = StudentRequest.login(login, password);
				} catch (IOException e1) {
					publishProgress(e1.getMessage());
					return false;
				}
				HttpEntity entity= response.getEntity();
			
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
				{
					try {
						content = Tools.convertStreamToString(entity.getContent());
						prefsEditor.putString(UserPreferences.TOKEN, content);
						prefsEditor.putString(UserPreferences.LOGIN, login);
						prefsEditor.putString(UserPreferences.PASSWORD, password);
						prefsEditor.commit();
						return true;
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					if(response.getStatusLine().getStatusCode()==HttpStatus.SC_UNAUTHORIZED)
					{
						try {
							content = Tools.convertStreamToString(entity.getContent());
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if(content.equals("bad_login_password"))
						{
							publishProgress(getString(R.string.bad_login_password));
						}
						else
						{
							publishProgress("Unauthorized");
						}
					}
				else
				if(response.getStatusLine().getStatusCode()==HttpStatus.SC_NOT_FOUND)
				{
					publishProgress("Server : Not found");
					Log.e("Supinface","Login : 404, Not found");
				}
				return false;
			}
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */
		@Override
		protected void onProgressUpdate(String... values) {
			Toast toast =Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, -40);
			toast.show();
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Boolean result) {
			if(result==true)
			{
				Toast.makeText(getApplicationContext(), R.string.authentication_success, Toast.LENGTH_LONG).show();
				switchToMainActivity();
			}
		}
	}
}	
