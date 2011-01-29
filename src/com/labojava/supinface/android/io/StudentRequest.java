package com.labojava.supinface.android.io;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class StudentRequest {

	public static final String URL="http://192.168.0.11:9000/";
	
	public static HttpResponse getStudentList()
	{
	
		HttpGet httpget= new HttpGet(URL+"studentlist");
		HttpClient httpClient=new DefaultHttpClient();
		HttpResponse response=null;
		try {
			response = httpClient.execute(httpget);
			return response;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
