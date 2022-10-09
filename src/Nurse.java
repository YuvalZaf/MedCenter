public class Nurse extends Workers 
{
	private Unbounded_Queue <Patient> NurseQueue;
	private Bounded_Queue <Patient> juniorQueue;
	private Unbounded_QueueByPriority <Patient> priorityQueue;
	
	
	

	public void run()
	{
		while(!dayEnded)
		{
			Patient cur = NurseQueue.extract();
			if(cur == null)//  if queue is empty
				break;
			int sleepTime = 1 + (int)(Math.random()*2);
			try {
				Thread.sleep(sleepTime*1000);}
			catch(InterruptedException e) {};
			measures(cur);
			chooseDoc(cur);
		}
	}
	
	public Nurse(int id ,Unbounded_Queue <Patient> q,Bounded_Queue <Patient> juniorQueue,
			Unbounded_QueueByPriority <Patient> priorityQueue)//Constructor
	{
		super(id);
		this.NurseQueue = q;
		this.juniorQueue = juniorQueue;
		this.priorityQueue = priorityQueue;
	}
	
	public int measures(Patient p)// check the patient condition
	{
		int cond = 0;
		int blood = BloodPresure();
		double heat = BodyHeat();
		int bmi = BMI(p);
		cond = checkBloodPressure(blood) + checkBodyHeat(heat) + bmi;
		if(checkCondition(cond))
			cond = changeCondition(cond);
		p.setNote(blood,heat,cond, this.workerId);
		return cond;	
	}
	
	
	public boolean checkCondition(int cond)
	{
		if(cond > 10 || cond <= 6)
			return true;
		else
			return false;
	}
	
	
	public int changeCondition(int cond)
	{
		if (cond>10)
			cond=10;
		else if(cond <= 6)
		{
			if(Math.random() <= 0.1)
			{
				cond = -1;
			}
		}
		return cond;
	}
	

	public int checkBloodPressure(int b)
	{
		if(b<=140 && b>=90)
			return 4;
		else
			return 2;	
	}
	
	public int checkBodyHeat(double h)
	{
		if(h<=39 && h>=38)
			return 0;
		else
			return 3;
	}
	public int BMI(Patient p)
	{
		int body;
		body = 4*(p.getWeight()/p.getHeight());
		return body;
	}
	
 	public int BloodPresure()// check the patient blood pressure
	{
		int presure, max, min;
		double a = Math.random();
		if(a<=0.8)
		{
			max = 140;
			min = 90;
		}
		else if(a>=0.8 && a<=0.9)
		{
			max = 90;
			min = 0;
		}
		else
		{
			max = 200;
			min = 140;
		}
		presure = (int)((Math.random()*(max - min+1))+ min);
		return presure;
	}
	
	public double BodyHeat() // check the patient body heat
	{
		double heat;
		double random = Math.random();
		int max,min;
		if(random <= 0.7)
		{
			max = 39;
			min = 38;
		}
		else if(random >= 0.7 && random <=0.9)
		{
			max = 40;
			min = 39;
		}
		else
		{
			max = 38;
			min = 0;
		}
		heat = (Math.random()*(max - min+1))+ min;
		return heat;
	}
	
	public void chooseDoc(Patient p)
	{
		if(p.getNote().getCondition()>6)
		{
			priorityQueue.insert(p);
		}
		else
		{
			juniorQueue.insert(p);
		}
	}
	
	
	
	
	
	
	
}
