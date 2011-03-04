package com.labojava.supinface.android.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.labojava.supinface.android.preferences.UserPreferences;

public class Tools {
	
	/**
	 * 
	 * @author boris
	 *
	 * @param ctx
	 * @return true si un login et un password sont enregistrés dans les paramètres
	 */
	public static boolean isReady(Context ctx)
	{
		SharedPreferences preferences;
		preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		
		//Si la préférence Login n'existe pas  retourne "n/a"
		if (preferences.getString(UserPreferences.LOGIN, "n/a").equals("n/a")
			|| preferences.getString(UserPreferences.PASSWORD, "n/a").equals("n/a")) {
		    return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 
	 * @author boris
	 *
	 * @param is InputStream a convertir
	 * @return l'inputStream convertie en String
	 */
	public static String convertStreamToString(InputStream is)
	    {
			/*
	         * To convert the InputStream to String we use the BufferedReader.readLine()
	         * method. We iterate until the BufferedReader return null which means
	         * there's no more data to read. Each line will appended to a StringBuilder
	         * and returned as String.
	         */
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb = new StringBuilder();
	 
	        String line = null;
	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return sb.toString();
	    }
	
	public static boolean isAuth()
	{
		return false;
	}
	
	/*
	*@return boolean return true if the application can access the internet
	*/
	public static boolean haveInternet(Context ctx){
		NetworkInfo info=(NetworkInfo)((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		if(info==null || !info.isConnected()){
			return false;
		}
		if(info.isRoaming()){
			//here is the roaming option you can change it if you want to disable internet while roaming, just return false
			return true;
		}
		return true;
	}
	// see http://androidsnippets.com/have-internet
		
}
