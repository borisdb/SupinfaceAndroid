package com.labojava.supinface.android.activity;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.labojava.supinface.android.R;
import com.labojava.supinface.android.R.layout;
import com.labojava.supinface.android.io.StudentRequest;
import com.labojava.supinface.android.tools.Tools;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// Moi aussi  je fais des ...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getStudentList();
    }
    
    public void getStudentList()
    {
    	HttpResponse response = StudentRequest.getStudentList();
    	if(response!=null)
    	{
    		HttpEntity entity = response.getEntity();
    		try {
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				{
					String msg= Tools.convertStreamToString(entity.getContent());
					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
				}
				
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    }
}