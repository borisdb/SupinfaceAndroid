/**
 * 
 */
package com.labojava.supinface.android.activity;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
				if(login(_login,_password))
				{
					Toast.makeText(getApplicationContext(), "Authentication Successful", Toast.LENGTH_LONG).show();
					switchToMainActivity();
				}
			}
		});
	}
	
	
	private boolean login(String login, String password)
	{
		String content=null;
		HttpResponse response = StudentRequest.login(login, password);
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
					Toast toast =Toast.makeText(getApplicationContext(), R.string.bad_login_password, Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, -40);
					toast.show();
				}
			}
		else
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_NOT_FOUND)
		{
			Log.e("Supinface","Login : 404, Not found");
		}
		return false;
	}
	
	private void switchToMainActivity()
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	
}
