package com.motoisomountinstaller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Bundle;
import android.os.Environment;
import android.app.ListActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;


public class FileChooser extends ListActivity {
    /** Called when the activity is first created. */
	private File currentDir;
	private FileArrayAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentDir = new File(Environment.getExternalStorageDirectory().getPath()+"/");
        fill(currentDir);
        
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory")){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}
		else
		{
			onFileClick(o);
		}
	}
    
    private void onFileClick(Option o)
    {
    	Toast.makeText(this, "File Chosen: "+o.getName(), Toast.LENGTH_SHORT).show(); //o.getName() is the exact filename, and its in the currentDirectory.
    	MainActivity.filename = Environment.getExternalStorageDirectory().getPath()+"/"+o.getName();
    	Intent intent = new Intent(FileChooser.this, MainActivity.class);
		FileChooser.this.startActivity(intent);
    }
    
    
    private void fill(File f)
    {
        File[]dirs = f.listFiles();
         this.setTitle("Current Dir: "+f.getName());
         List<Option>dir = new ArrayList<Option>();
         List<Option>fls = new ArrayList<Option>();
         try{
             for(File ff: dirs)
             {
                if(ff.isDirectory())
                    dir.add(new Option(ff.getName(),"Folder",ff.getAbsolutePath()));
                else
                {
                    fls.add(new Option(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath()));
                }
             }
         }catch(Exception e)
         {
             
         }
         Collections.sort(dir);
         Collections.sort(fls);
         dir.addAll(fls);
         if(!f.getName().equalsIgnoreCase("sdcard"))
             dir.add(0,new Option("..","Parent Directory",f.getParent()));
     adapter = new FileArrayAdapter(FileChooser.this,R.layout.file_view,dir);
   	 this.setListAdapter(adapter);
    }


    
}