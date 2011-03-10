package com.labojava.supinface.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("Nono","lancement on va controller ce que fait JSON");
        RestClient aRestClient = new RestClient();
        aRestClient.connect("http://10.23.17.118:9000/event/mobile/showthis/2");
        
        TextView aTextView = (TextView) findViewById(R.id.txtVJSON);  
        
        String myValue= new String();
       
        	myValue+="ID :"+ aRestClient.table.get("id");
        	myValue+="\n Title :"+ aRestClient.table.get("title");
        	myValue+="\n Date Start :"+ aRestClient.table.get("start");
        	myValue+="\n Description :"+ aRestClient.table.get("description");
        	myValue+="\n Date End :"+ aRestClient.table.get("end");
        	myValue+="\n Create By:"+ aRestClient.table.get("creator");
        aTextView.setText(myValue);        
    }
}
