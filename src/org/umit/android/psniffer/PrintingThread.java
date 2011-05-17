package org.umit.android.psniffer;

import java.io.BufferedReader;
import java.io.IOException;

import android.util.Log;

public class PrintingThread implements Runnable {

	BufferedReader reader;
	
	@Override
	public void run() {
		int read;
		char[] buffer = new char[1024];
		StringBuffer output = new StringBuffer();
		try{
		while ((read = reader.read(buffer)) > 0) 
		{
			output.append(buffer, 0, read);
			Log.v("DEBUGGING", output.toString());
			output = new StringBuffer();
		}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public PrintingThread(BufferedReader reader)
	{
		this.reader = reader;
	}

}
