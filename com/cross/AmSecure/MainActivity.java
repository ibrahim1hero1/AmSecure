package com.cross.amsecure;

import java.util.Iterator;

import com.cross.amsecure.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

/*
    @author to  Ibrahim Hanna @2016
                ibrahim.seniore@gmail.com  
*/


public class MainActivity extends Activity {

	
	DBHelper myDb;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    public void onStartProceed(View view){
    	String siteName=((EditText)findViewById(R.id.editText1)).getText().toString().trim();
    	
    	
    	if(siteName.equals("") || siteName==null){
    		
    		PlaySound.playAlarm(getApplicationContext());
    		Toast.makeText(this,"please enter site name to let AmSecure proceed it", 
    				Toast.LENGTH_LONG).show();
    	}
    	else{
    	
        Intent intent=new Intent(this,ProceedActivity.class);
               intent.putExtra("siteName", siteName);
               startActivity(intent); 
    	}
    }
    
    
    public void openKeysDialog(View view){
    	openCursorDialog(myDb.getSites());
    }
    
    
    
    public void openCursorDialog(Cursor cursor){
    	AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
    	builderSingle.setIcon(R.drawable.ic_launcher);
    	builderSingle.setTitle("Select your KEY");

    	final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
    	        this,
    	        android.R.layout.select_dialog_singlechoice);
    
    	  if(cursor.moveToFirst()){
    		  do{
    			  arrayAdapter.add(cursor.getString(cursor.getColumnIndex("Name")));
    		  }while(cursor.moveToNext());
    	  }
    	    		
    	
    	
    	
    	builderSingle.setAdapter(arrayAdapter, null);
    	
    	
    	builderSingle.setNegativeButton(
    	        "cancel",
    	        new DialogInterface.OnClickListener() {
    	            @Override
    	            public void onClick(DialogInterface dialog, int which) {
    	                dialog.dismiss();
    	            }
    	        });

    	
    	builderSingle.show();
    	
    }
    
    
    
    
}
