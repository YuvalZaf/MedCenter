
abstract class Workers implements Runnable
{
	//protected boolean dayEnded;
	protected int workerId;
	protected static boolean dayEnded;
	
	public void run()
	{
		
		
		
		
	}
	
	public Workers(int id)
	{
		workerId = id;
		dayEnded = false;
	}
	
	public void notifyDayEnded()
	{
		dayEnded = true;
	}

	
	
	
}
