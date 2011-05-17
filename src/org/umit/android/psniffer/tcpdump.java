package org.umit.android.psniffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class tcpdump extends Activity {
/** Called when the activity is first created. */
	Process process;
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	Process p;
	try 
	{	p = Runtime.getRuntime().exec("su");
		try 
		{	p.waitFor();
			if (p.exitValue() != 255) 
			{
				Log.v("TCPDUMP", "ROOT");
			}
			else 
			{
				Log.v("TCPDUMP", "NOT ROOT");
			}
		} 
		catch (InterruptedException e) 
		{
			Log.v("TCPDUMP", "NOT ROOT");
		}
	}
	
	catch (IOException e) 
	{ 
		e.printStackTrace(); 
	}
		
	Button start = (Button)findViewById(R.id.start);
	start.setOnClickListener(startSniffing);

	Button stop = (Button)findViewById(R.id.stop);
	stop.setOnClickListener(stopSniffing);
}

private OnClickListener startSniffing = new OnClickListener() 
{
	@Override
	public void onClick(View v)
	{			
		try {
			process = Runtime.getRuntime().exec("tcpdump");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			PrintingThread t = new PrintingThread(reader);
			t.run();
			reader.close();
		}
		catch (IOException e) 
		{
			throw new RuntimeException(e);
		}
	}
};

private OnClickListener stopSniffing = new OnClickListener() {
	public void onClick(View v){
		KillProcessThread t = new KillProcessThread(process);
		t.run();
	}
};
}