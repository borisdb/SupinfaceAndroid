package com.labojava.supinface.android.io;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class StudentRequest {

	//10.0.2.2 = localhost pour l'emulateur
	//public static final String URL="http://10.0.2.2:9000/";
	//public static final String URL="http://10.23.18.189:9000/";
	public static final String URL="http://192.168.0.11:9000/";

	/**
	 * Requete le server pour logguer un utilisateur
	 * @param login Le login
	 * @param password le password
	 * @return la reponse HttpResponse
	 * @throws IOException 
	 */
	public static HttpResponse login(String login,String password) throws IOException
	{
		try {
			login=URLEncoder.encode(login,"utf-8");
			password=URLEncoder.encode(password,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String _url=URL+"auth/login/"+login+"/"+password;
		HttpGet httpget= new HttpGet(_url);
		HttpClient httpClient=new DefaultHttpClient();
		//httpClient.getParams().setParameter("http.socket.timeout", new Integer(10000));
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
			return response;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}
