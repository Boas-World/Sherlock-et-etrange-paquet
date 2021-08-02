package com.Layton.paquet.etrange;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.*;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.renderscript.Double2;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.Layton.paquet.etrange.R;
import com.Layton.paquet.etrange.R;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
	
	File main_path=new File("/storage/emulated/0/Android/data/com.Layton.paquet.etrange/files/");
	File save=new File("/storage/emulated/0/Android/data/com.Layton.paquet.etrange/files/Save/");
	
	Intent act=new Intent();
	
	boolean succes, readable;
	
	String tosave, read2;
	String read[];
	
	Double énigme, picarat, niveau;

	ImageView title, start, quit, trans;
	
	LinearLayout palier;
	
	AnimationDrawable anim_start, anim_quit;
	
	ObjectAnimator anim=new ObjectAnimator();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        
    }
    private void init(){
      
      init_path();
      init_view();
      init_image();
      init_animation();
      init_event();
      
      
      read2=read(save.toString()+"/Save.txt");
      if(isNullorEmpty(read2)){
      	read=read2.split(":");
      	
      	énigme=Double.parseDouble(read[1]);
      	picarat=Double.parseDouble(read[2]);
      	niveau=Double.parseDouble(read[3]);
      }
      else{
      	init_val();
      }
      Save();
      
    }
    private void init_path(){
      if(!save.isDirectory()){
        succes=save.mkdirs();
      }
      if(succes){
        MakeText("Dossiers créés");
      }
      else{
        MakeText("Dossiers non créés");
      }
      
    }
    private void init_view(){
      
      title=(ImageView)findViewById(R.id.title);
      start=(ImageView)findViewById(R.id.start);
      quit=(ImageView)findViewById(R.id.quit);
      palier=(LinearLayout)findViewById(R.id.palier);
      
      palier.setAlpha(1);
      
    }
    private void init_val(){
      
      énigme=0.00;
      picarat=0.00;
      niveau=0.00;
      act.setClass(this, dialogue1.class);
      
    }
    private void init_animation(){
      anim_start=(AnimationDrawable) start.getBackground();
      anim_quit=(AnimationDrawable) quit.getBackground();
      
      anim.setTarget(palier);
      anim.setPropertyName("Alpha");
      anim.setFloatValues((float)(1),(float)(0));
      anim.setDuration((int)(1000));
      
      
    }
    private void init_image(){
      
      title.setImageResource(R.drawable.title);
      start.setBackgroundResource(R.drawable.animation);
      quit.setBackgroundResource(R.drawable.anim_quit);
      palier.setBackgroundResource(R.drawable.palier);

    }
    private void init_event(){
      
     start.setOnClickListener(new View.OnClickListener() {
      	@Override
      	public void onClick(View view) {
        	anim_start.start();
        	quit.setEnabled(false);
        	anim.start();
        	GotoLevel();
      	}
  	});
  	quit.setOnClickListener(new View.OnClickListener() {
      	@Override
      	public void onClick(View view) {
        	anim_quit.start();
        	finish();
      	}
  	});
      
    }
    
    private void Save(){
      
      tosave=énigme.toString()+":"+picarat+":"+niveau.toString();
      
      MakeText("Sauvegarde");
      if(!write(tosave,save.toString()+"/Save.txt")){
        MakeText("Échec");
      }
      else{
        MakeText("Données Sauvegardées");
      }
      
      
    }
    
    private void GotoLevel(){
      
      if(niveau==0.00){
        
        niveau=0.10;
        Save();
        
        startActivity(act);
      }
      if(niveau==0.01){
        
      }
      
    }
    
    private void MakeText(String txt){
      
      Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
      
    }
    private boolean write(String data, String dir){
      
      try{
      	FileOutputStream fos=new FileOutputStream(dir);
      	fos.write(data.getBytes());
      	fos.flush();
      	fos.close();
      	return true;
      }
      catch(Exception e){
        return false;
      }
      
    }
    private String read(String path){
    	
    	try{
    		String message;
    		StringBuffer out=new StringBuffer();
    		FileInputStream reader=new FileInputStream(path);
    		InputStreamReader str=new InputStreamReader(reader);
    		BufferedReader bufferedReader=new BufferedReader(str);
    		while ((message=bufferedReader.readLine())!=null){
    			out.append(message);
    		}
    		bufferedReader.close();
    		return out.toString();
    	}
    	catch(Exception e){
    		
    		return "";
    		
    	}
    }
    private boolean isNullorEmpty(String str){
      
      if(str==null ||str==""){
        return true;
      }
      else {
        return false;
      }
      
    }

}
