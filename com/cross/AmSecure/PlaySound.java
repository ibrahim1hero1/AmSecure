package com.cross.amsecure;

import android.R;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

/*
   @author to  Ibrahim Hanna @2016
               ibrahim.seniore@gmail.com  
*/


public class PlaySound {


	public static void playAlarm(Context context){
		try{
			Uri notification=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone ring=RingtoneManager.getRingtone(context, notification);
			ring.play();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
