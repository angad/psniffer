package org.umit.android.psniffer;

public class KillProcessThread implements Runnable {

	Process p;
	@Override
	public void run() {
		p.destroy();
	}

	public KillProcessThread(Process p)
	{
		this.p = p;
	}
}
