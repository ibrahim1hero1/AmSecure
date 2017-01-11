package com.cross.amsecure;

import com.cross.amsecure.R;
import com.cross.amsecure.R.id;
import com.cross.amsecure.R.layout;
import com.cross.amsecure.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

  /*
   @author to  Ibrahim Abdsaid Hanna @2016
               ibrahim.seniore@gmail.com  
  */


public class ProceedActivity extends Activity {

	DBHelper myDb;
	String siteName;
	String sitePassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proceed);
		
		myDb= new DBHelper(this);
		
	    siteName=getIntent().getExtras().getString("siteName");
		final TextView siteName_header_txt=(TextView)findViewById(R.id.textView1);
		siteName_header_txt.setText("AmSecure you "+siteName);
		
		
		final CheckBox unwritablePassword_CheckBox=(CheckBox)findViewById(R.id.checkBox1);
		final CheckBox writablePassword_CheckBox=(CheckBox)findViewById(R.id.checkBox2);
		final EditText sitePassword_EditText=(EditText)findViewById(R.id.editText1);
		sitePassword=sitePassword_EditText.getText().toString().trim();
		
		unwritablePassword_CheckBox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				writablePassword_CheckBox.setChecked(false);
				sitePassword_EditText.setText(null);
				sitePassword_EditText.setEnabled(false);
			}
		});
		
		
		writablePassword_CheckBox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				unwritablePassword_CheckBox.setChecked(false);
				sitePassword_EditText.setEnabled(true);
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.proceed, menu);
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
	
	
	
	public void onClickProceedNow(View view){
		if(!myDb.insertSite(siteName, sitePassword)){
			Toast.makeText(this, siteName+" not registered dublicate value", Toast.LENGTH_LONG).show();
		}
		else{
		Toast.makeText(this, siteName+" is registered okay", Toast.LENGTH_LONG).show();
		}
		
	}
	
	
}
