package com.motoisomountinstaller;

import java.io.DataOutputStream;
import java.io.IOException;
import com.motoisomountinstaller.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.os.Build;
import android.os.Environment;

public class MainActivity extends Activity {
	public static String filename;
	Button button;
	String Phone = Build.DEVICE;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		addListenerOnBtnFileChooser();
		addListenerOnBtnbackupCDROMPatition();
		addListenerOnBtnFlashNewCDROMPatition();
	}

	
	public void addListenerOnBtnFileChooser() { //This is the Install Button.

		button = (Button) findViewById(R.id.btnFileChooser);

		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) { // On Button Click Do:
				Intent intent = new Intent(MainActivity.this, FileChooser.class);
				MainActivity.this.startActivity(intent);
				
			}
			
			
			
		});

}
	
	public void addListenerOnBtnbackupCDROMPatition() {
	
		button = (Button) findViewById(R.id.btnBackupCDROMPatition);

		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) { // On Button Click Do:
				
			
				try{
					Process p = Runtime.getRuntime().exec("su"); //Asking Root Permission
					DataOutputStream os = new DataOutputStream(p.getOutputStream());
					String process = null;
					
					if(Phone.contentEquals("spyder")){ //motorola razr
						process = "dd if=/dev/block/mmcblk1p16 of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
					}
					else if(Phone.contentEquals("cdma_spyder")){ //droid razr 
						process = "dd if=/dev/block/mmcblk1p16 of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
					}
					else if(Phone.contentEquals("targa")) //droid bionic
				    {
						process = "dd if=/dev/block/mmcblk1p17 of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
				    } 
					else if(Phone.contentEquals("maserati")) //droid 4
				    {
						process = "dd if=/dev/block/mmcblk1p16 of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";	
				    } 
					else if(Phone.contentEquals("vanquish")) //droid razr hd & razr hd
				    {
						process = "dd if=/dev/block/platform/msm_sdcc.1/by-name/cdrom of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
				    } 
					else if(Phone.contentEquals("dinara")) //moto atrix hd
				    {
						process = "dd if=/dev/block/platform/msm_sdcc.1/by-name/cdrom of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
				    } 
					else if(Phone.contentEquals("scorpion_mini")) //droid razr m & razr m
				    {
						process = "dd if=/dev/block/platform/msm_sdcc.1/by-name/cdrom of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
				    } 
					else if(Phone.contentEquals("asanti_c")) //Moto Photon Q
				    {
						process = "dd if=/dev/block/platform/msm_sdcc.1/by-name/cdrom of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";
				    } 
					else if(Phone.contentEquals("smi")) //moto razr i
				    {
						process = "dd if=/dev/block/mccblk0p15 of="+Environment.getExternalStorageDirectory().getPath()+"/"+"backupCDROM.iso";	
				    } 
					os.writeBytes(process + "\n"); 
					Toast.makeText(MainActivity.this, "Backing up CDROM Partition...Please Wait.", Toast.LENGTH_LONG).show();
					Toast.makeText(MainActivity.this, "Backup started. Wait one minute then you can flash a new CDROM.", Toast.LENGTH_LONG).show();
					Toast.makeText(MainActivity.this, "You will find the backup in "+ Environment.getExternalStorageDirectory().getPath()+"/backupCDROM.iso", Toast.LENGTH_LONG).show();
					os.flush();

				} catch (IOException e) { 
					 
				}finally{ 
					
				}
		
					}

		});
	}
	
	public void addListenerOnBtnFlashNewCDROMPatition() {
		
		button = (Button) findViewById(R.id.btnFlashNewCDROMPatition);

		button.setOnClickListener(new OnClickListener(){
			

			@Override
			public void onClick(View arg0) { // On Button Click Do:
				try{
					Process p = Runtime.getRuntime().exec("su"); //Asking Root Permission
					DataOutputStream os = new DataOutputStream(p.getOutputStream());
					String process = null;
					if(Phone.contentEquals("spyder")){ //motorola razr
						process = "dd if="+MainActivity.filename+" of="+"/dev/block/mmcblk1p16";
					}
					else if(Phone.contentEquals("cdma_spyder")){ //droid razr 
						process = "dd if="+MainActivity.filename+" of="+"/dev/block/mmcblk1p16";
					}
					else if(Phone.contentEquals("targa")) //droid bionic
				    {
						process = "dd if="+MainActivity.filename+" of="+"/dev/block/mmcblk1p17";
				    } 
					else if(Phone.contentEquals("maserati")) //droid 4
				    {
						process = "dd if="+MainActivity.filename+" of="+"/dev/block/mmcblk1p16";
				    } 
					else if(Phone.contentEquals("vanquish")) //droid razr hd & razr hd
				    {	
						process = "dd if="+MainActivity.filename+" of=/dev/block/platform/msm_sdcc.1/by-name/cdrom";
				    } 
					else if(Phone.contentEquals("dinara")) //moto atrix hd
				    {
						process = "dd if="+MainActivity.filename+" of=/dev/block/platform/msm_sdcc.1/by-name/cdrom";
				    } 
					else if(Phone.contentEquals("scorpion_mini")) //droid razr m & razr m
				    {
						process = "dd if="+MainActivity.filename+" of=/dev/block/platform/msm_sdcc.1/by-name/cdrom";
				    } 
					else if(Phone.contentEquals("asanti_c")) //Moto Photon Q
				    {
						process = "dd if="+MainActivity.filename+" of=/dev/block/platform/msm_sdcc.1/by-name/cdrom";
				    } 
					else if(Phone.contentEquals("smi")) //moto razr i
				    {
						process = "dd if="+MainActivity.filename+" of="+"/dev/block/mccblk0p15";	
				    } 
					os.writeBytes(process + "\n"); 
					Toast.makeText(MainActivity.this, "Flashing New CDROM Partition...Please Wait.", Toast.LENGTH_LONG).show();
					Toast.makeText(MainActivity.this, "Flashing started. Wait one minute then reboot.", Toast.LENGTH_LONG).show();
					os.flush();
				} catch (IOException e) { 
					 
				}finally{ 
					
				}
		
					}

		});
	}
}	