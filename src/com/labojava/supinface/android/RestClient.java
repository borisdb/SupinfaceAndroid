package com.labojava.supinface.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RestClient {

	public static Hashtable<String,String> table = new Hashtable<String,String>();
	
	private static String convertStreamToString(InputStream is) {
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
				sb.append(line + "\n");
				Log.i("Nono",line);
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


	/* This is a test function which will connects to a given
	 * rest service and prints it's response to Android Log with
	 * labels "Praeda".
	 */
	public static void connect(String url)
	{

		HttpClient httpclient = new DefaultHttpClient();


		// Prepare a request object
		HttpGet httpget = new HttpGet(url); 

		// Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			// Examine the response status
			
			Log.i("Nono","Process Status: "+response.getStatusLine().toString());
			
			// Get hold of the response entity
			HttpEntity entity = response.getEntity();
			// If the response does not enclose an entity, there is no need
			// to worry about connection release

			if (entity != null) {

				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				String result= convertStreamToString(instream);				
				Log.i("Nono",result);

				// A Simple JSONObject Creation
				JSONObject json=new JSONObject(result);
				Log.i("Nono","<jsonobject>\n"+json.toString()+"\n</jsonobject>");

				// A Simple JSONObject Parsing
				JSONArray nameArray=json.names();
				JSONArray valArray=json.toJSONArray(nameArray);
				for(int i=0;i<valArray.length();i++)
				{
					Log.i("Nono","<jsonname"+i+">\n"+nameArray.getString(i)+"\n</jsonname"+i+">\n"
							+"<jsonvalue"+i+">\n"+valArray.getString(i)+"\n</jsonvalue"+i+">");
					table.put(nameArray.getString(i),valArray.getString(i));
				}

				// A Simple JSONObject Value Pushing
				json.put("sample key", "sample value");
				Log.i("Nono","<jsonobject>\n"+json.toString()+"\n</jsonobject>");

				// Closing the input stream will trigger connection release
				instream.close();
			}


		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.i("Nono","Error: 1(Bad protocol)"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			Log.i("Nono","Error: 2(IO)"+e.getMessage());
			
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.i("Nono","Error: 3(JSON)"+e.getMessage());
			e.printStackTrace();
		}
	}

}
